package com.hgx.serviceorder.remote;

import com.hgx.internalcomm.dto.Car;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.OrderDriverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Description com.hgx.serviceorder.remote
 * @Author huogaoxu
 * @Date 2023-07-01 17:00
 * @Version 1.0
 **/
@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    /**
     * 根据城市码查询当前城市是否有可用司机
     * @param cityCode
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/city-driver/is-alailable-driver")
    ResponseResult<Boolean> isAvailableDriver(@RequestParam String cityCode);

    @GetMapping("/get-available-driver/{carId}")
    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId);


    @GetMapping("/car")
    public ResponseResult<Car> getCarById(@RequestParam  Long carId);
}
