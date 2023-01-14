package com.hgx.apipassenger.service;

import com.hgx.apipassenger.remote.ServicePriceCilent;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.ForecastPriceDTO;
import com.hgx.internalcomm.response.DirectionResponse;
import com.hgx.internalcomm.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class ForecastPriceService {

    @Resource
    private ServicePriceCilent servicePriceCilent;

    public ResponseResult forecastPrice(String depLongitude,String depLatitude,String destLongitude,String destLatitude){
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
        ResponseResult<ForecastPriceResponse> forecastPrice = servicePriceCilent.forecastPrice(forecastPriceDTO);
        double price = forecastPrice.getData().getPrice();

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        return ResponseResult.success(forecastPriceResponse);


    }
}
