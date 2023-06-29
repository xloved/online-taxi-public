package com.hgx.apipassenger.service;

import com.hgx.apipassenger.remote.ServicePriceCilent;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.ForecastPriceDTO;
import com.hgx.internalcomm.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class ForecastPriceService {

    @Resource
    private ServicePriceCilent servicePriceCilent;

    public ResponseResult forecastPrice(String depLongitude,String depLatitude,String destLongitude,
                                        String destLatitude,String cityCode, String vehicleType){
        log.info("出发地经度："+depLongitude);
        log.info("出发地纬度："+depLatitude);
        log.info("目的地经度："+destLongitude);
        log.info("目的地纬度："+destLatitude);
        log.info("调用计价服务：计算价格");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        forecastPriceDTO.setCityCode(cityCode);
        forecastPriceDTO.setVehicleType(vehicleType);
        ResponseResult<ForecastPriceResponse> forecastPrice = servicePriceCilent.forecastPrice(forecastPriceDTO);
        // todo 待测试价格预估参数返回版本号和车辆类型
//        ForecastPriceResponse data = forecastPrice.getData();
//        double price = data.getPrice();
//        String fareType = data.getFareType();
//        Integer fareVersion = data.getFareVersion();
//        log.info("预估价格为：" + price);
//        // 响应到前端的值
//        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
//        forecastPriceResponse.setPrice(price);
//        forecastPriceResponse.setCityCode(cityCode);
//        forecastPriceResponse.setVehicleType(vehicleType);
//        forecastPriceResponse.setFareType(fareType);
//        forecastPriceResponse.setFareVersion(fareVersion);
        return ResponseResult.success(forecastPrice.getData());


    }
}
