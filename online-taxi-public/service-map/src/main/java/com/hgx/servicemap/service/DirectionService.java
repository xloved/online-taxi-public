package com.hgx.servicemap.service;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.DirectionResponse;
import com.hgx.servicemap.remote.MapDirectionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description 根据起点和终点经纬度获取行驶时长（分钟）和距离（米）
 * @Author huogaoxu
 * @Date 2023-01-02 16:10
 **/
@Service
@Slf4j
public class DirectionService {

    @Resource
    private MapDirectionClient mapDirectionClient;
    /**
     * 根据起点和终点经纬度获取距离和时长
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult GetDirection(String depLongitude, String depLatitude,
                                       String destLongitude, String destLatitude){

        //调用第三方接口
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatitude,
                destLongitude, destLatitude);

        return ResponseResult.success(direction);
    }
}
