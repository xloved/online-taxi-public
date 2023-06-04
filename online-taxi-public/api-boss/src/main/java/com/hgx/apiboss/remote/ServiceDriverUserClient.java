package com.hgx.apiboss.remote;

import com.hgx.internalcomm.dto.Car;
import com.hgx.internalcomm.dto.DriverCarBindingRelationship;
import com.hgx.internalcomm.dto.DriverUser;
import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description 调用司机信息服务
 * @Author huogaoxu
 * @Date 2023-05-29 11:07
 * @Version 1.0
 **/
@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    /**
     * 添加司机信息
     * @param driverUser
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/users")
    ResponseResult addDriverUser(@RequestBody DriverUser driverUser);

    /**
     * 修改司机信息
     * @param driverUser
     * @return
     */
   @RequestMapping(method = RequestMethod.PUT,value = "/users")
   ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);

    /**
     * 添加车辆信息
     * @param car
     * @return
     */
   @RequestMapping(method = RequestMethod.POST, value = "/user/car")
   ResponseResult addCarUser(@RequestBody Car car);


    @RequestMapping(method = RequestMethod.POST,value = "/driver-car-binding-relationship/bind")
    public ResponseResult bindCar(@RequestBody DriverCarBindingRelationship descriptor);

    @RequestMapping(method = RequestMethod.POST,value = "/driver-car-binding-relationship/unbind")
    public ResponseResult unbindCar(@RequestBody DriverCarBindingRelationship descriptor);

}
