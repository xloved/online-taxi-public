package com.hgx.apidriver.controller;

import com.hgx.apidriver.service.ApiUserService;
import com.hgx.internalcomm.dto.DriverUser;
import com.hgx.internalcomm.dto.DriverUserWorkStatus;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.dto.TokenResult;
import com.hgx.internalcomm.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 司机端服务
 * @Author huogaoxu
 * @Date 2023-05-30 21:21
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Resource
    private ApiUserService apiUserService;

    /**
     * 修改司机信息
     * @param driverUser
     * @return
     */
    @PutMapping("/user")
    public ResponseResult updateApiUser(@RequestBody DriverUser driverUser){

        return apiUserService.updateApiUser(driverUser);
    }

    @PostMapping("/driver-user-work-status")
    public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){

        return apiUserService.changeWorkStatus(driverUserWorkStatus);
    }

    /**
     * 根据司机token查询 司机和车辆绑定关系
     * @param request
     * @return
     */
    @GetMapping("/driver-car-binding-relationship")
    public ResponseResult getDriverCarBindingRelationship(HttpServletRequest request){

        String authorization = request.getHeader("Authorization");
        TokenResult tokenResult = JwtUtils.checkToken(authorization);
        String driverPhone = tokenResult.getPhone();

        return apiUserService.getDriverCarBindingRelationship(driverPhone);

    }

    @GetMapping("/work-status")
    public ResponseResult<DriverUserWorkStatus> getWorkStatus(Long driverId){
        return apiUserService.getWorkStatus(driverId);
    }
}
