package com.hgx.apidriver.remote;

import com.hgx.internalcomm.dto.DriverUser;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.DriverUserExistsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Description com.hgx.apidriver.remote
 * @Author huogaoxu
 * @Date 2023-05-30 21:23
 * @Version 1.0
 **/
@FeignClient("service-driver-user")
public interface ServiceDriverUserClients {

    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.GET,value = "/check-driver/{driverPhone}")
    public  ResponseResult<DriverUserExistsResponse> getUserByPhone(@PathVariable("driverPhone") String driverPhone);
}
