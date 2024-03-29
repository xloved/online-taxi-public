package com.hgx.serviceorder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.constant.IdentityConstantEnum;
import com.hgx.internalcomm.constant.OrdersConstants;
import com.hgx.internalcomm.dto.Car;
import com.hgx.internalcomm.dto.OrderInfo;
import com.hgx.internalcomm.dto.PriceRule;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import com.hgx.internalcomm.request.PriceRuleIsNewRequest;
import com.hgx.internalcomm.request.PushRequest;
import com.hgx.internalcomm.response.OrderDriverResponse;
import com.hgx.internalcomm.response.TerminalResponse;
import com.hgx.internalcomm.response.TrsearchResponse;
import com.hgx.internalcomm.utils.RedisPrefixUtils;
import com.hgx.serviceorder.mapper.OrderInfoMapper;
import com.hgx.serviceorder.remote.ServiceDriverUserClient;
import com.hgx.serviceorder.remote.ServiceMapClient;
import com.hgx.serviceorder.remote.ServicePriceClient;
import com.hgx.serviceorder.remote.ServiceSsePushClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  订单服务
 * </p>
 *
 * @author huogaoxu
 * @since 2023-06-12
 */
@Service
@Slf4j
public class OrderInfoService  {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private ServicePriceClient servicePriceClient;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ServiceDriverUserClient serviceDriverUserClient;
    @Resource
    private ServiceMapClient serviceMapClient;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    ServiceSsePushClient serviceSsePushClient;

    /**
     * 创建订单
     * @param ordersRequest
     * @return
     */
    public ResponseResult addOrder(OrdersRequest ordersRequest)  {

        //  判断当前城市是否有可用的司机
        ResponseResult<Boolean> availableDriver = serviceDriverUserClient.isAvailableDriver(ordersRequest.getAddress());
        log.info("当前城市是否有司机结果：" + availableDriver.getData());
        if(!availableDriver.getData()){
            return ResponseResult.fail(CommonStatusEnum.CITY_DRIVER_EMPTY.getCode(),
                    CommonStatusEnum.CITY_DRIVER_EMPTY.getValue());
        }

        // 判断当前价格是否为最新价格
//        ResponseResult<Boolean> newVersion = servicePriceClient.isNewVersion(ordersRequest.getFareType(),
//                ordersRequest.getFareVersion());
        PriceRuleIsNewRequest priceRuleIsNewRequest = new PriceRuleIsNewRequest();
        priceRuleIsNewRequest.setFareType(ordersRequest.getFareType());
        priceRuleIsNewRequest.setFareVersion(ordersRequest.getFareVersion());
        ResponseResult<Boolean> aNew = servicePriceClient.isNewVersion(priceRuleIsNewRequest);
        if(!(aNew.getData())){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),
                    CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }

        // 判断下单设备是否是黑名单
        if (isBlackDevice((ordersRequest))) {
            return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACK.getCode(),
                    CommonStatusEnum.DEVICE_IS_BLACK.getValue());
        }

        // 判断下单的城市和计价规则是否存在
        if (!isPriceRuleExists(ordersRequest)) {
            return ResponseResult.fail(CommonStatusEnum.CITY_SERVICE_NOT_SERVICE.getCode(),
                    CommonStatusEnum.CITY_SERVICE_NOT_SERVICE.getValue());
        }


        // 判断乘客是否有正在进行的订单
        if(isOrderPassengerGoingon(ordersRequest.getPassengerId()) > 0){
            return ResponseResult.fail(CommonStatusEnum.ORDER_GOING_ON.getCode(),
                    CommonStatusEnum.ORDER_GOING_ON.getValue());
        }
            // 创建订单
            OrderInfo orderInfo = new OrderInfo();
            LocalDateTime now = LocalDateTime.now();
            /**
             * copyProperties(Object source, Object target)
             * source – 源bean
             * target – 目标bean
             */
            BeanUtils.copyProperties(ordersRequest,orderInfo);
            orderInfo.setOrderStatus(OrdersConstants.ORDER_START);
            orderInfo.setGmtCreate(now);
            orderInfo.setGmtModified(now);
            orderInfoMapper.insert(orderInfo);
            // 派单
//            dispatchRealTimeOrder(orderInfo);

        // 定时任务的处理
        for (int i =0;i<6;i++) {
            // 派单 dispatchRealTimeOrder
            int result = dispatchRealTimeOrder(orderInfo);
            if (result == 1) {
                break;
            }
            // 等待20s
//            try {
//                Thread.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            if (i == 5) {
                // 订单无效
                orderInfo.setOrderStatus(OrdersConstants.ORDER_INVALID);
                orderInfoMapper.updateById(orderInfo);
            } else {
                // 等待20s
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
            return ResponseResult.success("");
        }


    /**
     * 实时订单派单逻辑
     * @param orderInfo
     */
    public int  dispatchRealTimeOrder(OrderInfo orderInfo){
        log.info("循环一次");
        int result = 0;

        // 搜索的经度和纬度
        String depLatitude = orderInfo.getDepLatitude();
        String depLongitude = orderInfo.getDepLongitude();
        // 搜索的范围
        String center = depLatitude+"," +depLongitude;
        List<Integer> list = new ArrayList<>();
        // 搜索的公里数
        list.add(2000);
        list.add(4000);
        list.add(5000);
        // 搜索结果

        ResponseResult<List<TerminalResponse>> listResponseResult = null;
        // 距离半径
        radius:
        for (int i = 0; i < list.size(); i++) {
            Integer radius = list.get(i);
            listResponseResult = serviceMapClient.aroundsearch(center, radius);
            log.info("在半径为" + radius + "的范围内，寻找车辆,结果：" + JSONArray.fromObject(listResponseResult.getData()).toString());

            // 获得终端
            // 解析终端
//            JSONArray re  sult = JSONArray.fromObject(listResponseResult.getData());
//            for (int j = 0; j < result.size(); j++) {
//                JSONObject jsonObject = result.getJSONObject(j);
//                String carIdString = jsonObject.getString("carId");
//                Long carId = Long.parseLong(carIdString);
//                long longitude = jsonObject.getLong("longitude");
//                long latitude = jsonObject.getLong("latitude");
            // 为了测试是否从地图上获取到司机
//            List<TerminalResponse> data = new ArrayList<>();
            List<TerminalResponse> data = listResponseResult.getData();
            for (int j=0;j<data.size();j++){
                TerminalResponse terminalResponse = data.get(j);
                Long carId = terminalResponse.getCarId();

                String longitude = terminalResponse.getLongitude();
                String latitude = terminalResponse.getLatitude();
                // 查询是否有多于的可派单司机
                ResponseResult<OrderDriverResponse> availableDriver = serviceDriverUserClient.getAvailableDriver(carId);
                if(availableDriver.getCode() == CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode()){
                    log.info("没有车辆ID："+carId+",对于的司机");
                    continue;
                }else {
                    log.info("车辆ID："+carId+"找到了正在出车的司机");

                    OrderDriverResponse orderDriverResponse = availableDriver.getData();
                    Long driverId = orderDriverResponse.getDriverId();
                    String driverPhone = orderDriverResponse.getDriverPhone();
                    String licenseId = orderDriverResponse.getLicenseId();
                    String vehicleNo = orderDriverResponse.getVehicleNo();

                    String vehicleTypeFromCar = orderDriverResponse.getVehicleType();

                    // 判断车辆的车型是否符合？
                    String vehicleType = orderInfo.getVehicleType();
                    if (!vehicleType.trim().equals(vehicleTypeFromCar.trim())){
                        System.out.println("车型不符合");
                        continue ;
                    }

                    // 添加锁
                    String lockKey = (driverId+"").intern();
                    RLock lock = redissonClient.getLock(lockKey);
                    lock.lock();
//                    synchronized ((driverId+"").intern()){
//                        // 判断司机 是否有进行中的订单
//                        if (isDriverOrderGoingon(driverId) > 0){
//                            continue ;
//                        }
//                        // 订单直接匹配司机
//                        // 查询当前车辆信息
//                        QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
//                        carQueryWrapper.eq("id",carId);
//
//                        // 设置订单中和司机车辆相关的信息
//                        orderInfo.setDriverId(driverId);
//                        orderInfo.setDriverPhone(driverPhone);
//                        orderInfo.setCarId(carId);
//                        // 从地图中来
//                        orderInfo.setReceiveOrderCarLongitude(longitude);
//                        orderInfo.setReceiveOrderCarLatitude(latitude);


                    // 判断司机 是否有进行中的订单
                    if (isDriverOrderGoingon(driverId) > 0){
                        lock.unlock();
                        continue ;
                    }
                    // 订单直接匹配司机
                    // 查询当前车辆信息
                    QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
                    carQueryWrapper.eq("id",carId);


                    // 设置订单中和司机车辆相关的信息
                    orderInfo.setDriverId(driverId);
                    orderInfo.setDriverPhone(driverPhone);
                    orderInfo.setCarId(carId);
                    // 从地图中来
                    orderInfo.setReceiveOrderCarLongitude(longitude);
                    orderInfo.setReceiveOrderCarLatitude(latitude);

                    orderInfo.setReceiveOrderTime(LocalDateTime.now());
                    orderInfo.setLicenseId(licenseId);
                    orderInfo.setVehicleNo(vehicleNo);
                    orderInfo.setOrderStatus(OrdersConstants.DRIVER_RECEIVE_ORDER);

                    orderInfoMapper.updateById(orderInfo);

                    // 通知司机
                    JSONObject driverContent = new JSONObject();
                    driverContent.put("orderId",orderInfo.getId());
                    driverContent.put("passengerId",orderInfo.getPassengerId());
                    driverContent.put("passengerPhone",orderInfo.getPassengerPhone());
                    driverContent.put("departure",orderInfo.getDeparture());
                    driverContent.put("depLongitude",orderInfo.getDepLongitude());
                    driverContent.put("depLatitude",orderInfo.getDepLatitude());

                    driverContent.put("destination",orderInfo.getDestination());
                    driverContent.put("destLongitude",orderInfo.getDestLongitude());
                    driverContent.put("destLatitude",orderInfo.getDestLatitude());

                    PushRequest pushRequest = new PushRequest();
                    pushRequest.setUserId(driverId);
                    pushRequest.setIdentity(IdentityConstantEnum.IDENTITY_DRIVER);
                    pushRequest.setContent(driverContent.toString());

//                    serviceSsePushClient.push(driverId, IdentityConstantEnum.IDENTITY_DRIVER,driverContent.toString());
                    serviceSsePushClient.push(pushRequest);

                    // 通知乘客
                    JSONObject passengerContent = new  JSONObject();
                    passengerContent.put("orderId",orderInfo.getId());
                    passengerContent.put("driverId",orderInfo.getDriverId());
                    passengerContent.put("driverPhone",orderInfo.getDriverPhone());
                    passengerContent.put("vehicleNo",orderInfo.getVehicleNo());
                    // 车辆信息，调用车辆服务
                    ResponseResult<Car> carById = serviceDriverUserClient.getCarById(carId);
                    Car carRemote = carById.getData();

                    passengerContent.put("brand", carRemote.getBrand());
                    passengerContent.put("model",carRemote.getModel());
                    passengerContent.put("vehicleColor",carRemote.getVehicleColor());

                    passengerContent.put("receiveOrderCarLongitude",orderInfo.getReceiveOrderCarLongitude());
                    passengerContent.put("receiveOrderCarLatitude",orderInfo.getReceiveOrderCarLatitude());

//                    serviceSsePushClient.push(orderInfo.getPassengerId(), IdentityConstantEnum.IDENTITY_PASSENGER,passengerContent.toString());
                    PushRequest pushRequest1 = new PushRequest();
                    pushRequest1.setUserId(orderInfo.getPassengerId());
                    pushRequest1.setIdentity(IdentityConstantEnum.IDENTITY_PASSENGER);
                    pushRequest1.setContent(passengerContent.toString());

                    serviceSsePushClient.push(pushRequest1);

                    result = 1;
                    lock.unlock();
                    // 退出，不在进行 司机的查找
                    break radius;
                  }
                }
                // 根据解析出来的终端，查询车辆信息
                // 找到符合的车辆，进行派单
                // 如果派单成功退出循环

            }
            return result;
        }
//    }

        // 未优化前的搜索车辆代码
//        int radius = 2000;
//        ResponseResult<List<TerminalResponse>>  listResponseResult = serviceMapClient.aroundsearch(center,radius);
//        List<TerminalResponse> data = listResponseResult.getData();
//        if(data.size() == 0){
//            radius = 4000;
//            listResponseResult = serviceMapClient.aroundsearch(center,radius);
//            if (listResponseResult.getData().size() == 0) {
//                radius = 5000;
//                listResponseResult = serviceMapClient.aroundsearch(center,radius);
//                if(listResponseResult.getData().size() == 0){
//                    log.info("在半径为"+radius+"的范围内，寻找车辆,结果：");
//
//                }
//            }
//        }


    /**
     * 判断乘客是否有 业务中的订单
     * @param passengerId
     * @return
     */
    private int isOrderPassengerGoingon(Long passengerId) {
        // 判断有正在进行的订单不许下单
        QueryWrapper<OrderInfo> queryWrap = new QueryWrapper<>();
        queryWrap.eq("passenger_id",passengerId)
                .and(wrapper -> wrapper.eq("order_status", OrdersConstants.ORDER_START)
                        .or().eq("order_status", OrdersConstants.DRIVER_RECEIVE_ORDER)
                        .or().eq("order_status", OrdersConstants.DRIVER_TO_PICK_UP_PASSENGER)
                        .or().eq("order_status", OrdersConstants.DRIVER_ARRIVED_DEPARTURE)
                        .or().eq("order_status", OrdersConstants.PICK_UP_PASSENGER)
                        .or().eq("order_status", OrdersConstants.PASSENGER_GETOFF)
                        .or().eq("order_status", OrdersConstants.TO_START_PAY));
        return orderInfoMapper.selectCount(queryWrap);

    }

    /**
     * 判断是否有 业务中的订单
     * @param driverId
     * @return
     */
    private int isDriverOrderGoingon(Long driverId) {
        // 判断有正在进行的订单不允许下单
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverId);
        queryWrapper.and(wrapper -> wrapper
                .eq("order_status", OrdersConstants.DRIVER_RECEIVE_ORDER)
                .or().eq("order_status", OrdersConstants.DRIVER_TO_PICK_UP_PASSENGER)
                .or().eq("order_status", OrdersConstants.DRIVER_ARRIVED_DEPARTURE)
                .or().eq("order_status", OrdersConstants.PICK_UP_PASSENGER)

        );
        Integer validOrderNumber = orderInfoMapper.selectCount(queryWrapper);
        log.info("司机Id：" + driverId + ",正在进行的订单的数量：" + validOrderNumber);

        return validOrderNumber;
    }

    // 判断是否有黑名单
    private boolean isBlackDevice(OrdersRequest ordersRequest) {
        // 设置key
        String deviceCodePrefix = RedisPrefixUtils.blackDeviceCodePrefix + ordersRequest.getDeviceCode();
        Boolean hasKey = stringRedisTemplate.hasKey(deviceCodePrefix);
        if (Boolean.TRUE.equals(hasKey)) {
            String deveicKeys = stringRedisTemplate.opsForValue().get(deviceCodePrefix);
            assert deveicKeys != null;
            int parseInt = Integer.parseInt(deveicKeys);
            if(parseInt >= 2){
                return true;
            } else {
                stringRedisTemplate.opsForValue().increment(deviceCodePrefix);
            }
        }else {
            stringRedisTemplate.opsForValue().setIfAbsent(deviceCodePrefix,"1",1L, TimeUnit.HOURS);
        }
        return false;
    }

    // 判断下单的城市和计价规则是否存在
    private boolean isPriceRuleExists(OrdersRequest ordersRequest){
        String fareType = ordersRequest.getFareType();
        int index = fareType.indexOf("$");
        String cityCode = fareType.substring(0, index);
        String vehicleType = fareType.substring(index + 1);

        PriceRule priceRule = new PriceRule();
        priceRule.setCityCode(cityCode);
        priceRule.setVehicleType(vehicleType);
        ResponseResult<Boolean> result = servicePriceClient.ifExits(priceRule);

        return result.getData();
    }


    /**
     * 去接乘客
     * @param orderRequest
     * @return
     */
    public ResponseResult toPickUpPassenger(OrdersRequest orderRequest){
        Long orderId = orderRequest.getOrderId();
        LocalDateTime toPickUpPassengerTime = orderRequest.getToPickUpPassengerTime();
        String toPickUpPassengerLongitude = orderRequest.getToPickUpPassengerLongitude();
        String toPickUpPassengerLatitude = orderRequest.getToPickUpPassengerLatitude();
        String toPickUpPassengerAddress = orderRequest.getToPickUpPassengerAddress();
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",orderId);
        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);

        orderInfo.setToPickUpPassengerAddress(toPickUpPassengerAddress);
        orderInfo.setToPickUpPassengerLatitude(toPickUpPassengerLatitude);
        orderInfo.setToPickUpPassengerLongitude(toPickUpPassengerLongitude);
        orderInfo.setToPickUpPassengerTime(LocalDateTime.now());
        orderInfo.setOrderStatus(OrdersConstants.DRIVER_TO_PICK_UP_PASSENGER);

        orderInfoMapper.updateById(orderInfo);

        return ResponseResult.success();
    }

    /**
     * 司机到达乘客上车点
     * @param orderRequest
     * @return
     */
    public ResponseResult arrivedDeparture(OrdersRequest orderRequest){
        Long orderId = orderRequest.getOrderId();

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",orderId);

        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);
        orderInfo.setOrderStatus(OrdersConstants.DRIVER_ARRIVED_DEPARTURE);

        orderInfo.setDriverArrivedDepartureTime(LocalDateTime.now());
        orderInfoMapper.updateById(orderInfo);
        return ResponseResult.success();
    }

    /**
     * 司机接到乘客
     * @param orderRequest
     * @return
     */
    public ResponseResult pickUpPassenger(@RequestBody OrdersRequest orderRequest){
        Long orderId = orderRequest.getOrderId();

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",orderId);
        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);

        orderInfo.setPickUpPassengerLongitude(orderRequest.getPickUpPassengerLongitude());
        orderInfo.setPickUpPassengerLatitude(orderRequest.getPickUpPassengerLatitude());
        orderInfo.setPickUpPassengerTime(LocalDateTime.now());
        orderInfo.setOrderStatus(OrdersConstants.PICK_UP_PASSENGER);

        orderInfoMapper.updateById(orderInfo);
        return ResponseResult.success();
    }

    /**
     * 乘客下车到达目的地，行程终止
     * @param orderRequest
     * @return
     */
    public ResponseResult passengerGetoff(@RequestBody OrdersRequest orderRequest){
        Long orderId = orderRequest.getOrderId();

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",orderId);
        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);

        orderInfo.setPassengerGetoffTime(LocalDateTime.now());
        orderInfo.setPassengerGetoffLongitude(orderRequest.getPassengerGetoffLongitude());
        orderInfo.setPassengerGetoffLatitude(orderRequest.getPassengerGetoffLatitude());

        orderInfo.setOrderStatus(OrdersConstants.PASSENGER_GETOFF);
        // 订单行驶的路程和时间,调用 service-map
        ResponseResult<Car> carById = serviceDriverUserClient.getCarById(orderInfo.getCarId());
        Long starttime = orderInfo.getPickUpPassengerTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long endtime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("开始时间："+starttime);
        System.out.println("结束时间："+endtime);

        // 1668078028000l,测试的时候不要跨天
        ResponseResult<TrsearchResponse> trsearch = serviceMapClient.trsearch(carById.getData().getTid(), starttime,endtime);
        TrsearchResponse data = trsearch.getData();
        Long driveMile = data.getDriveMile();
        Long driveTime = data.getDriveTime();

        orderInfo.setDriveMile(driveMile);
        orderInfo.setDriveTime(driveTime);

        // 获取价格
        String address = orderInfo.getAddress();
        String vehicleType = orderInfo.getVehicleType();
        ResponseResult<Double> doubleResponseResult = servicePriceClient.calculatePrice(driveMile.intValue(), driveTime.intValue(), address, vehicleType);
        Double price = doubleResponseResult.getData();
        orderInfo.setPrice(price);

        orderInfoMapper.updateById(orderInfo);
        return ResponseResult.success();
    }

    /**
     * 支付
     * @param orderRequest
     * @return
     */
    public ResponseResult pay(OrdersRequest orderRequest){

        Long orderId = orderRequest.getOrderId();
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);

        orderInfo.setOrderStatus(OrdersConstants.SUCCESS_PAY);
        orderInfoMapper.updateById(orderInfo);
        return ResponseResult.success();
    }

    /**
     * 订单取消
     * @param orderId 订单Id
     * @param identity  身份：1：乘客，2：司机
     * @return
     */
    public ResponseResult cancel(Long orderId, String identity){
        // 查询订单当前状态
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        Integer orderStatus = orderInfo.getOrderStatus();

        LocalDateTime cancelTime = LocalDateTime.now();
        Integer cancelOperator = null;
        Integer cancelTypeCode = null;

        // 正常取消
        int cancelType = 1;

        // 更新订单的取消状态
        // 如果是乘客取消
        if (identity.trim().equals(IdentityConstantEnum.IDENTITY_PASSENGER)){
            switch (orderStatus){
                // 订单开始
                case OrdersConstants.ORDER_START:
                    cancelTypeCode = OrdersConstants.CANCEL_PASSENGER_BEFORE;
                    break;
                // 司机接到订单
                case OrdersConstants.DRIVER_RECEIVE_ORDER:
                    LocalDateTime receiveOrderTime = orderInfo.getReceiveOrderTime();
                    long between = ChronoUnit.MINUTES.between(receiveOrderTime, cancelTime);
                    if (between > 1){
                        cancelTypeCode = OrdersConstants.CANCEL_PASSENGER_ILLEGAL;
                    }else {
                        cancelTypeCode = OrdersConstants.CANCEL_PASSENGER_BEFORE;
                    }
                    break;
                // 司机去接乘客
                case OrdersConstants.DRIVER_TO_PICK_UP_PASSENGER:
                    // 司机到达乘客起点
                case OrdersConstants.DRIVER_ARRIVED_DEPARTURE:
                    cancelTypeCode = OrdersConstants.CANCEL_PASSENGER_ILLEGAL;
                    break;
                default:
                    log.info("乘客取消失败");
                    cancelType = 0;
                    break;
            }
        }

        // 如果是司机取消
        if (identity.trim().equals(IdentityConstantEnum.IDENTITY_DRIVER)){
            switch (orderStatus){
                // 订单开始
                // 司机接到乘客
                case OrdersConstants.DRIVER_RECEIVE_ORDER:
                case OrdersConstants.DRIVER_TO_PICK_UP_PASSENGER:
                case OrdersConstants.DRIVER_ARRIVED_DEPARTURE:
                    LocalDateTime receiveOrderTime = orderInfo.getReceiveOrderTime();
                    long between = ChronoUnit.MINUTES.between(receiveOrderTime, cancelTime);
                    if (between > 1){
                        cancelTypeCode = OrdersConstants.CANCEL_DRIVER_ILLEGAL;
                    }else {
                        cancelTypeCode = OrdersConstants.CANCEL_DRIVER_BEFORE;
                    }
                    break;

                default:
                    log.info("司机取消失败");
                    cancelType = 0;
                    break;
            }
        }


        if (cancelType == 0){
            return ResponseResult.fail(CommonStatusEnum.ORDER_CANCEL_ERROR.getCode(),CommonStatusEnum.ORDER_CANCEL_ERROR.getValue());
        }

        orderInfo.setCancelTypeCode(cancelTypeCode);
        orderInfo.setCancelTime(cancelTime);
        orderInfo.setCancelOperator(Integer.parseInt(identity));
        orderInfo.setOrderStatus(OrdersConstants.ORDER_CANCEL);

        orderInfoMapper.updateById(orderInfo);
        return ResponseResult.success();
    }

    public ResponseResult pushPayInfo(OrdersRequest orderRequest) {

        Long orderId = orderRequest.getOrderId();

        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        orderInfo.setOrderStatus(OrdersConstants.TO_START_PAY);
        orderInfoMapper.updateById(orderInfo);
        return ResponseResult.success();

    }

    public ResponseResult<OrderInfo> detail(Long orderId){
        OrderInfo orderInfo =  orderInfoMapper.selectById(orderId);
        return ResponseResult.success(orderInfo);
    }

    public ResponseResult<OrderInfo> current(String phone, String identity){
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();

        if (identity.equals(IdentityConstantEnum.IDENTITY_DRIVER)){
            queryWrapper.eq("driver_phone",phone);

            queryWrapper.and(wrapper->wrapper
                    .eq("order_status",OrdersConstants.DRIVER_RECEIVE_ORDER)
                    .or().eq("order_status",OrdersConstants.DRIVER_TO_PICK_UP_PASSENGER)
                    .or().eq("order_status",OrdersConstants.DRIVER_ARRIVED_DEPARTURE)
                    .or().eq("order_status",OrdersConstants.PICK_UP_PASSENGER)

            );
        }
        if (identity.equals(IdentityConstantEnum.IDENTITY_PASSENGER)){
            queryWrapper.eq("passenger_phone",phone);
            queryWrapper.and(wrapper->wrapper.eq("order_status",OrdersConstants.ORDER_START)
                    .or().eq("order_status",OrdersConstants.DRIVER_RECEIVE_ORDER)
                    .or().eq("order_status",OrdersConstants.DRIVER_TO_PICK_UP_PASSENGER)
                    .or().eq("order_status",OrdersConstants.DRIVER_ARRIVED_DEPARTURE)
                    .or().eq("order_status",OrdersConstants.PICK_UP_PASSENGER)
                    .or().eq("order_status",OrdersConstants.PASSENGER_GETOFF)
                    .or().eq("order_status",OrdersConstants.TO_START_PAY)
            );
        }

        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);
        return ResponseResult.success(orderInfo);
    }
}
