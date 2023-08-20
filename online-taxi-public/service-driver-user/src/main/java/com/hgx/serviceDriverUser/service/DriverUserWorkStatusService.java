package com.hgx.serviceDriverUser.service;

import com.hgx.internalcomm.dto.DriverUserWorkStatus;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.serviceDriverUser.mapper.DriverUserWorkStatusMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  司机工作状态
 * </p>
 *
 * @author huogaoxu
 * @since 2023-06-04
 */
@Service
public class DriverUserWorkStatusService {

    @Resource
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    /**
     * 修改当前司机的工作状态
     * @param driverId
     * @param workStatus
     * @return
     */
    public ResponseResult updateWorkerStatus(Long driverId, Integer workStatus ){
        // 根据driverID查询当前司机信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("driver_id",driverId);
        List<DriverUserWorkStatus> driverUserWorkStatuses = driverUserWorkStatusMapper.selectByMap(map);
        // 获取查询到的第一条数据
        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatuses.get(0);
        // 设置当前司机的状态
        driverUserWorkStatus.setWorkStatus(workStatus);
        // 修改数据库
        driverUserWorkStatusMapper.updateById(driverUserWorkStatus);

        return ResponseResult.success("");
    }

    public ResponseResult<DriverUserWorkStatus> getWorkStatus(Long driverId) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("driver_id",driverId);
        List<DriverUserWorkStatus> driverUserWorkStatuses = driverUserWorkStatusMapper.selectByMap(queryMap);
        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatuses.get(0);

        return ResponseResult.success(driverUserWorkStatus);

    }
}
