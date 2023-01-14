package com.hgx.servicemap.controller;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.ForecastPriceDTO;
import com.hgx.servicemap.service.DirectionService;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author huogaoxu
 * @Date 2023-01-02 16:07
 **/
@RestController
@RequestMapping("/direction")
public class DirectionController {

    @Resource
    private DirectionService directionService;

    /**
     * 地图服务：根据经纬度获取位置
     * @param forecastPriceDTO
     * @return
     */
    @GetMapping("/driving")
    public ResponseResult getDirection(@RequestBody ForecastPriceDTO forecastPriceDTO){
        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();
        return directionService.GetDirection(depLongitude,depLatitude,destLongitude,destLatitude);
    }
}
