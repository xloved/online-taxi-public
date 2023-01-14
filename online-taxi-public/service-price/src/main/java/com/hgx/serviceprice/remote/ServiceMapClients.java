package com.hgx.serviceprice.remote;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.ForecastPriceDTO;
import com.hgx.internalcomm.response.DirectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description
 * @Author huogaoxu
 * @Date 2023-01-03 22:27
 **/
@FeignClient("service-map")
public interface ServiceMapClients {

    @RequestMapping(method = RequestMethod.GET,value = "/direction/driving")
    public ResponseResult<DirectionResponse> direction(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
