package com.hgx.serviceorder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.constant.OrdersConstants;
import com.hgx.internalcomm.dto.OrderInfo;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import com.hgx.internalcomm.utils.RedisPrefixUtils;
import com.hgx.serviceorder.mapper.OrderInfoMapper;
import com.hgx.serviceorder.remote.ServicePriceClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class OrderInfoService  {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private ServicePriceClient servicePriceClient;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 创建订单
     * @param ordersRequest
     * @return
     */
    public ResponseResult addOrder(OrdersRequest ordersRequest)  {
        // 判断当前价格是否为最新价格
        ResponseResult newVersion = servicePriceClient.isNewVersion(ordersRequest.getFareType(),
                ordersRequest.getFareVersion());
        Boolean isNewPrice = (Boolean) newVersion.getData();
        if(!isNewPrice){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),
                    CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        // 判断下单设备是否是黑名单
        String deviceCodePrefix = RedisPrefixUtils.blackDeviceCodePrefix + ordersRequest.getDeviceCode();
        // 设置key
        if (isBlackDevice((deviceCodePrefix))) {
            return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACK.getCode(),
                    CommonStatusEnum.DEVICE_IS_BLACK.getValue());
        }
        // 判断  有正在进行的订单不允许下单
        if(isOrderGoingon(ordersRequest.getPassengerId()) > 0){
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
        return ResponseResult.success("");
    }

    /**
     * 判断是否有 业务中的订单
     * @param passengerId
     * @return
     */
    private int isOrderGoingon(Long passengerId) {
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

    // 判断是否有黑名单
    private boolean isBlackDevice(String deviceCodePrefix) {
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

}
