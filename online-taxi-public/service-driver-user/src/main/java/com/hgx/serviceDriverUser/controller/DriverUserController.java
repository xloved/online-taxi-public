package com.hgx.serviceDriverUser.controller;

import com.hgx.internalcomm.constant.DriverCarConstants;
import com.hgx.internalcomm.dto.DriverUser;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.DriverUserExistsResponse;
import com.hgx.serviceDriverUser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
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

    @GetMapping("/check-driver/{driverPhone}")
    public  ResponseResult<DriverUserExistsResponse> getUserByPhone(@PathVariable String driverPhone){
       // String driverPhone = driverUser.getDriverPhone();
        ResponseResult<DriverUser> userByPhone = driverUserService.getUserByPhone(driverPhone);
        DriverUser driverPhoneDB = userByPhone.getData();
        DriverUserExistsResponse response = new DriverUserExistsResponse();
        int ifExists = DriverCarConstants.DRIVER_EXISTS;
        if (driverPhoneDB == null) {
            ifExists = DriverCarConstants.DRIVER_NOT_EXISTS;
            response.setDriverPhone(driverPhone);
            response.setIfExists(ifExists);
        }else {
            response.setDriverPhone(driverPhoneDB.getDriverPhone());
            response.setIfExists(ifExists);
        }


        return ResponseResult.success(response);
    }
}
