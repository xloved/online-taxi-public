package com.hgx.serviceDriverUser.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.constant.DriverCarConstants;
import com.hgx.internalcomm.dto.*;
import com.hgx.internalcomm.response.OrderDriverResponse;
import com.hgx.serviceDriverUser.mapper.CarMapper;
import com.hgx.serviceDriverUser.mapper.DriverCarBindingRelationshipMapper;
import com.hgx.serviceDriverUser.mapper.DriverUserMapper;
import com.hgx.serviceDriverUser.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @Description com.hgx.serviceDriverUser.service
 * @Author huogaoxu
 * @Date 2023-05-28 21:26
 * @Version 1.0
 **/
@Service
public class DriverUserService {

    @Resource
    private DriverUserMapper driverUserMapper;

    @Autowired
    private CarMapper carMapper;
    @Resource
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;
    @Autowired
    DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    /**
     * 插入司机信息，在插入司机信息的同时初始化司机状态
     * @param driverUser
     * @return
     */
    public ResponseResult addDriverUser(DriverUser driverUser){
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);
        driverUserMapper.insert(driverUser);
        /**
         * 初始化司机状态
         */
        DriverUserWorkStatus driverUserWorkStatus = new DriverUserWorkStatus();
        driverUserWorkStatus.setDriverId(driverUser.getId());
        driverUserWorkStatus.setWorkStatus(DriverCarConstants.DRIVER_WORK_STATUS_STOP);
        driverUserWorkStatus.setGmtCreate(now);
        driverUserWorkStatus.setGmtModified(now);
        driverUserWorkStatusMapper.insert(driverUserWorkStatus);

        return ResponseResult.success("");
    }

    /**
     *修改司机信息
     * @param driverUser
     * @return
     */
    public ResponseResult updateDriverUser(DriverUser driverUser){
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtModified(now);
        driverUserMapper.updateById(driverUser);

        return ResponseResult.success("");
    }

    /**
     * check当前司机
     * @param driverPhone
     * @return
     */
    public ResponseResult<DriverUser> getUserByPhone(String driverPhone){
        // 根据手机号和状态获取当前司机
        HashMap<String, Object> map = new HashMap<>();
        map.put("driver_phone",driverPhone);
        map.put("state", DriverCarConstants.DRIVER_STATE_VALD);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);
        // 判断司机是否存在，不存在返回提示信息，存在获取第一条数据
        if (driverUsers.isEmpty()) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXITS.getCode(),
                    CommonStatusEnum.DRIVER_NOT_EXITS.getValue());
        }

        DriverUser driverUser = driverUsers.get(0);
        return ResponseResult.success(driverUser);

    }


    /**
     * 根据车辆Id查询订单需要的司机信息
     * @param carId
     * @return
     */
    public ResponseResult<OrderDriverResponse> getAvailableDriver(Long carId){
        // 车辆和司机绑定关系查询
        QueryWrapper<DriverCarBindingRelationship> driverCarBindingRelationshipQueryWrapper = new QueryWrapper<>();
        driverCarBindingRelationshipQueryWrapper.eq("car_id",carId);
        driverCarBindingRelationshipQueryWrapper.eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);

//        DriverCarBindingRelationship driverCarBindingRelationship = driverCarBindingRelationshipMapper.selectOne(queryWrapper);
        DriverCarBindingRelationship driverCarBindingRelationship = driverCarBindingRelationshipMapper.selectOne(driverCarBindingRelationshipQueryWrapper);
        Long driverId = driverCarBindingRelationship.getDriverId();
        // 司机工作状态的查询
        QueryWrapper<DriverUserWorkStatus> driverUserWorkStatusQueryWrapper = new QueryWrapper<>();
        driverUserWorkStatusQueryWrapper.eq("driver_id",driverId);
        driverUserWorkStatusQueryWrapper.eq("work_status",DriverCarConstants.DRIVER_WORK_STATUS_START);

//        QueryWrapper<DriverUserWorkStatus> queryWrapper1 = new QueryWrapper<>();
//        queryWrapper1.eq("driver_id",driverId);
//        queryWrapper1.eq("work_status",DriverCarConstants.DRIVER_WORK_STATUS_START);
//
//        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatusMapper.selectOne(queryWrapper1);
        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatusMapper.selectOne(driverUserWorkStatusQueryWrapper);
        if (null == driverUserWorkStatus){
            return ResponseResult.fail(CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode(),
                    CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getValue());
        }else {

            // 查询司机信息
            QueryWrapper<DriverUser> driverUserQueryWrapper = new QueryWrapper<>();
            driverUserQueryWrapper.eq("id",driverId);
            DriverUser driverUser = driverUserMapper.selectOne(driverUserQueryWrapper);
            // 查询车辆信息
            QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
            carQueryWrapper.eq("id",carId);
            Car car = carMapper.selectOne(carQueryWrapper);

//            QueryWrapper<DriverUser> queryWrapper2 = new QueryWrapper<>();
//            queryWrapper2.eq("id",driverId);
//            DriverUser driverUser = driverUserMapper.selectOne(queryWrapper2);
            OrderDriverResponse orderDriverResponse = new OrderDriverResponse();
            orderDriverResponse.setCarId(carId);
            orderDriverResponse.setDriverId(driverId);
            orderDriverResponse.setDriverPhone(driverUser.getDriverPhone());

            orderDriverResponse.setLicenseId(driverUser.getLicenseId());
            orderDriverResponse.setVehicleNo(car.getVehicleNo());

            return ResponseResult.success(orderDriverResponse);
        }
    }

    /**
     * 测试方法
     * @return
     */
    public ResponseResult testGetDriverUser(){
        DriverUser driverUser = driverUserMapper.selectById(1);
        return ResponseResult.success(driverUser);
    }
}
