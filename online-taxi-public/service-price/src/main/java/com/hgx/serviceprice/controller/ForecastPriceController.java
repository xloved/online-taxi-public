package com.hgx.serviceprice.controller;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.ForecastPriceDTO;
import com.hgx.serviceprice.service.ForecastPriceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description  预估价格服务
 * @Author huogaoxu
 * @Date 2023-01-02 15:21
 **/
@RestController
public class ForecastPriceController {

    @Resource
    private ForecastPriceService forecastPriceService;

    //通过经纬度进行价格预估
    @PostMapping(value="/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO){
        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();
        String cityCode = forecastPriceDTO.getCityCode();
        String vehicleType = forecastPriceDTO.getVehicleType();

        return forecastPriceService.forecasePrice(depLongitude,depLatitude,destLongitude,
                destLatitude, cityCode, vehicleType);
    }
}
