package com.hgx.serviceDriverUser.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.constant.DriverCarConstants;
import com.hgx.internalcomm.dto.DriverCarBindingRelationship;
import com.hgx.internalcomm.dto.DriverUser;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.serviceDriverUser.mapper.DriverCarBindingRelationshipMapper;
import com.hgx.serviceDriverUser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huogaoxu
 * @since 2023-05-31
 */
@Service
public class DriverCarBindingRelationshipService  {

    @Resource
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMap;

    @Autowired
    DriverUserMapper driverUserMapper;

    /**
     * 司机与车辆绑定
     * @param descriptor
     * @return
     */
    public ResponseResult carBind(DriverCarBindingRelationship descriptor){
        // 判断，如果参数中的车辆和司机已经做过绑定，则不允许重复绑定
        QueryWrapper<DriverCarBindingRelationship> wrapper = new QueryWrapper<>();
        wrapper.eq("driver_id",descriptor.getDriverId())
                .eq("car_id",descriptor.getCarId())
                .eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
        Integer integer = driverCarBindingRelationshipMap.selectCount(wrapper);
        if((integer > 0)){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_EXITS.getCode(),
                    CommonStatusEnum.DRIVER_CAR_BIND_EXITS.getValue());
        }
        // 车辆被绑定了
         wrapper = new QueryWrapper<>();
        wrapper.eq("car_id",descriptor.getCarId())
                .eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
         integer = driverCarBindingRelationshipMap.selectCount(wrapper);
        if((integer > 0)){
            return ResponseResult.fail(CommonStatusEnum.CAR_BIND_EXITS.getCode(),
                    CommonStatusEnum.CAR_BIND_EXITS.getValue());
        }
        // 司机被绑定了
         wrapper = new QueryWrapper<>();
        wrapper.eq("driver_id",descriptor.getDriverId())
                .eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
         integer = driverCarBindingRelationshipMap.selectCount(wrapper);
        if((integer > 0)){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_BIND_EXITS.getCode(),
                    CommonStatusEnum.DRIVER_BIND_EXITS.getValue());
        }

        LocalDateTime now = LocalDateTime.now();
        descriptor.setBindingTime(now);
        descriptor.setBindState(DriverCarConstants.DRIVER_CAR_BIND);
        driverCarBindingRelationshipMap.insert(descriptor);

        return ResponseResult.success("");
    }


    /**
     * 司机与车辆解绑
     * @param descriptor
     * @return
     */
    public ResponseResult unbindCar(DriverCarBindingRelationship descriptor){
        LocalDateTime now = LocalDateTime.now();

        Map<String,Object> map = new HashMap<>();
        map.put("driver_id", descriptor.getDriverId());
        map.put("car_id", descriptor.getCarId());
        map.put("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
        List<DriverCarBindingRelationship> relationships = driverCarBindingRelationshipMap.selectByMap(map);
        // 判断，如果数据不存在，返回提示
        if(relationships.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXITS.getCode(),
                    CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXITS.getValue());
        }
        DriverCarBindingRelationship bindingRelationship = relationships.get(0);
        bindingRelationship.setBindState(DriverCarConstants.DRIVER_CAR_UNBIND);
        bindingRelationship.setUnBindingTime(now);
        driverCarBindingRelationshipMap.updateById(bindingRelationship);

        return ResponseResult.success("");
    }

    public ResponseResult<DriverCarBindingRelationship> getDriverCarRelationShipByDriverPhone(@RequestParam String driverPhone){
        QueryWrapper<DriverUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_phone",driverPhone);

        DriverUser driverUser = driverUserMapper.selectOne(queryWrapper);
        Long driverId = driverUser.getId();

        QueryWrapper<DriverCarBindingRelationship> driverCarBindingRelationshipQueryWrapper = new QueryWrapper<>();
        driverCarBindingRelationshipQueryWrapper.eq("driver_id",driverId);
        driverCarBindingRelationshipQueryWrapper.eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);

        DriverCarBindingRelationship driverCarBindingRelationship = driverCarBindingRelationshipMap.selectOne(driverCarBindingRelationshipQueryWrapper);
        return ResponseResult.success(driverCarBindingRelationship);

    }
}
