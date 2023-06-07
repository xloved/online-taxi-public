package com.hgx.apidriver.service;

import com.hgx.apidriver.remote.ServiceDriverUserClients;
import com.hgx.apidriver.remote.ServiceMapClient;
import com.hgx.internalcomm.dto.Car;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.ApiDriverPointRequest;
import com.hgx.internalcomm.request.PointRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description com.hgx.apidriver.service
 * @Author huogaoxu
 * @Date 2023-06-06 14:01
 * @Version 1.0
 **/
@Service
@Slf4j
public class ApiPointService {
    @Resource
    private ServiceDriverUserClients serviceDriverUserClients;

    @Resource
    private ServiceMapClient serviceMapClient;

    /**
     * 上传汽车位置轨迹点
     * @param apiDriverPointRequest
     * @return
     */
    public ResponseResult upload(ApiDriverPointRequest apiDriverPointRequest){
        // 1.获取carId
        Long carId = apiDriverPointRequest.getCarId();

        // 通过carId,调用service-driver-user的接口获取trid，tid
        ResponseResult<Car> carById = serviceDriverUserClients.getCarById(carId);
        Car data = carById.getData();
        log.info("车辆信息："+data.toString());
        String tid = data.getTid();
        String trid = data.getTrid();

        //调用地图进行轨迹上传
        PointRequest pointRequest = new PointRequest();
        pointRequest.setTid(tid);
        pointRequest.setTrid(trid);
        pointRequest.setPoints(apiDriverPointRequest.getPoints());
        serviceMapClient.uplod(pointRequest);

        return ResponseResult.success();
    }
}
