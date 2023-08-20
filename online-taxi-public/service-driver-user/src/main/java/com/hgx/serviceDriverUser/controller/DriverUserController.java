package com.hgx.serviceDriverUser.controller;

import com.hgx.internalcomm.constant.DriverCarConstants;
import com.hgx.internalcomm.dto.DriverCarBindingRelationship;
import com.hgx.internalcomm.dto.DriverUser;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.DriverUserExistsResponse;
import com.hgx.internalcomm.response.OrderDriverResponse;
import com.hgx.serviceDriverUser.service.DriverCarBindingRelationshipService;
import com.hgx.serviceDriverUser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description 司机用户服务，司机信息controller
 * @Author huogaoxu
 * @Date 2023-05-28 21:44
 * @Version 1.0
 **/
@RestController
@Slf4j
public class DriverUserController {

    @Resource
    DriverUserService driverUserService;

    /**
     * 添加司机信息
     * @param driverUser
     * @return
     */
    @PostMapping("/users")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){

        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * 修改司机信息
     * @param driverUser
     * @return
     */
    @PutMapping("/users")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.updateDriverUser(driverUser);
    }

    /**
     * 根据手机号验证当前司机是否存在
     * @param driverPhone
     * @return
     */
    @GetMapping("/check-driver/{driverPhone}")
    public  ResponseResult<DriverUserExistsResponse> getUserByPhone(@PathVariable String driverPhone){
       // String driverPhone = driverUser.getDriverPhone();
        ResponseResult<DriverUser> userByPhone = driverUserService.getUserByPhone(driverPhone);
        DriverUser driverPhoneDB = userByPhone.getData();
        // 返回前端接口的响应值
        DriverUserExistsResponse response = new DriverUserExistsResponse();
        // 当前司机存在
        int ifExists = DriverCarConstants.DRIVER_EXISTS;
        // 判断从数据库获取的值为null，然后返回前端当前司机状态为不存在，否则司机信息为存在
        if (driverPhoneDB == null) {
            ifExists = DriverCarConstants.DRIVER_NOT_EXISTS;
            response.setDriverPhone(driverPhone);
            response.setIfExists(ifExists);
        }else {
            response.setDriverPhone(driverPhoneDB.getDriverPhone());
            response.setIfExists(ifExists);
        }

        // 返回响应值
        return ResponseResult.success(response);
    }


    /**
     * 根据车辆Id查询订单需要的司机信息
     * @param carId
     * @return
     */
    @GetMapping("/get-available-driver/{carId}")
    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId){
        return driverUserService.getAvailableDriver(carId);
    }

    @Autowired
    DriverCarBindingRelationshipService driverCarBindingRelationshipService;

    /**
     * 根据司机手机号查询司机和车辆绑定关系
     * @param driverPhone
     * @return
     */
    @GetMapping("/driver-car-binding-relationship")
    public ResponseResult<DriverCarBindingRelationship> getDriverCarRelationShip(@RequestParam String driverPhone){
        return driverCarBindingRelationshipService.getDriverCarRelationShipByDriverPhone(driverPhone);
    }
}
