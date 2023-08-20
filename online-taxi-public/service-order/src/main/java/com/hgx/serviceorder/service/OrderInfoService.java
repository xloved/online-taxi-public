package com.hgx.serviceorder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.constant.OrdersConstants;
import com.hgx.internalcomm.dto.Car;
import com.hgx.internalcomm.dto.OrderInfo;
import com.hgx.internalcomm.dto.PriceRule;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import com.hgx.internalcomm.request.PriceRuleIsNewRequest;
import com.hgx.internalcomm.response.OrderDriverResponse;
import com.hgx.internalcomm.response.TerminalResponse;
import com.hgx.internalcomm.utils.RedisPrefixUtils;
import com.hgx.serviceorder.mapper.OrderInfoMapper;
import com.hgx.serviceorder.remote.ServiceDriverUserClient;
import com.hgx.serviceorder.remote.ServiceMapClient;
import com.hgx.serviceorder.remote.ServicePriceClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
            dispatchRealTimeOrder(orderInfo);
        return ResponseResult.success("");
    }

    /**
     * 实时订单派单逻辑
     * @param orderInfo
     */
    public void  dispatchRealTimeOrder(OrderInfo orderInfo){

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
            List<TerminalResponse> data = listResponseResult.getData();
            for (int j=0;j<data.size();j++){
                TerminalResponse terminalResponse = data.get(j);
                Long carId = terminalResponse.getCarId();

                String longitude = terminalResponse.getLongitude();
                String latitude = terminalResponse.getLatitude();
                // 查询是否有对于的可派单司机
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

                    lock.unlock();
                    // 退出，不在进行 司机的查找
                    break radius;
                  }
                }
                // 根据解析出来的终端，查询车辆信息
                // 找到符合的车辆，进行派单
                // 如果派单成功退出循环

            }
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

}
