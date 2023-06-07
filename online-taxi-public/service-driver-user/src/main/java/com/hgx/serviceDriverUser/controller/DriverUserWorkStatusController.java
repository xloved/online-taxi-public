package com.hgx.serviceDriverUser.controller;


import com.hgx.internalcomm.dto.DriverUserWorkStatus;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.serviceDriverUser.service.DriverUserWorkStatusService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  司机工作状态服务
 * </p>
 *
 * @author huogaoxu
 * @since 2023-06-04
 */
@RestController
@RequestMapping("/driver-user")
public class DriverUserWorkStatusController {

    @Resource
    private DriverUserWorkStatusService driverUserWorkStatusService;

    /**
     * 修改司机工作状态
     * @param driverUserWorkStatus
     * @return
     */
    @PutMapping("/worker-status")
    public ResponseResult updateWorkerStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){
        Long driverId = driverUserWorkStatus.getDriverId();
        Integer workStatus = driverUserWorkStatus.getWorkStatus();
        return driverUserWorkStatusService.updateWorkerStatus(driverId, workStatus);
    }

}
