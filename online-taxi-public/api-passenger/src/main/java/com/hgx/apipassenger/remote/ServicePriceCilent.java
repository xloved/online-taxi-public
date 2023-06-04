package com.hgx.apipassenger.remote;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.ForecastPriceDTO;
import com.hgx.internalcomm.response.ForecastPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description  客户端调用预估价格服务
 * @Author huogaoxu
 * @Date 2023-01-14 20:40
 **/
@FeignClient("service-price")
public interface ServicePriceCilent {

    @RequestMapping(value="/forecast-price", method = RequestMethod.POST)
    ResponseResult<ForecastPriceResponse> forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
