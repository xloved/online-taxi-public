package com.hgx.apidriver.remote;

import com.hgx.internalcomm.dto.*;
import com.hgx.internalcomm.response.DriverUserExistsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 调用service-driver-user服务
 * @Author huogaoxu
 * @Date 2023-05-30 21:23
 * @Version 1.0
 **/
@FeignClient("service-driver-user")
public interface ServiceDriverUserClients {

    /**
     * 修改司机信息
     * @param driverUser
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);

    /**
     * 根据手机号查询当前司机信息
     * @param driverPhone
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/check-driver/{driverPhone}")
    ResponseResult<DriverUserExistsResponse> getUserByPhone(@PathVariable("driverPhone") String driverPhone);

    /**
     * 根据车辆ID查询车辆信息
     * @param carId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user/getCar")
    ResponseResult<Car> getCarById(@RequestParam("carId") Long carId);

    @RequestMapping(method = RequestMethod.POST, value="/driver-user-work-status")
    public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus);

    @GetMapping("/driver-car-binding-relationship")
    public ResponseResult<DriverCarBindingRelationship> getDriverCarRelationShip(@RequestParam String driverPhone);
}
