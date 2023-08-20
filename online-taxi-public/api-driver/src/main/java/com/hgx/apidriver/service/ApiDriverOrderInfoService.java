package com.hgx.apidriver.service;

import com.hgx.apidriver.remote.ServiceOrderClient;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiDriverOrderInfoService {

    @Autowired
    ServiceOrderClient serviceOrderClient;

    /**
     * 去接乘客
     * @param orderRequest
     * @return
     */
    public ResponseResult toPickUpPassenger( OrdersRequest orderRequest){
        return serviceOrderClient.toPickUpPassenger(orderRequest);
    }


    /**
     * 到达乘客上车点
     * @param orderRequest
     * @return
     */
    public ResponseResult arrivedDeparture(OrdersRequest orderRequest){
        return serviceOrderClient.arrivedDeparture(orderRequest);
    }

    /**
     * 司机接到乘客
     * @param orderRequest
     * @return
     */
    public ResponseResult pickUpPassenger( OrdersRequest orderRequest){
        return  serviceOrderClient.pickUpPassenger(orderRequest);
    }

    public ResponseResult passengerGetoff(OrdersRequest orderRequest){
        return serviceOrderClient.passengerGetoff(orderRequest);
    }
}