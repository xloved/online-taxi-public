package com.hgx.serviceDriverUser.service;

import com.hgx.internalcomm.dto.Car;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.TerminalResponse;
import com.hgx.internalcomm.response.TrackResponse;
import com.hgx.serviceDriverUser.mapper.CarMapper;
import com.hgx.serviceDriverUser.remote.ServiceMapClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huogaoxu
 * @since 2023-05-30
 */
@Service
public class CarService  {

    @Resource
    CarMapper carMapper;

    @Resource
    private ServiceMapClient serviceMapClient;

    /**
     * 添加车辆信息
     * @param car
     * @return
     */
    public ResponseResult addCar(Car car){
        Date date = new Date();
        car.setGmtCreate(date);
        car.setGmtModified(date);
        carMapper.insert(car);

        // 调用service-map获取此车辆对应的tid
        ResponseResult<TerminalResponse> result = serviceMapClient.add(car.getVehicleNo(), car.getId()+"");
        String tid = result.getData().getTid();
        car.setTid(tid);


        // 获取次车辆对应的轨迹点 trid
        ResponseResult<TrackResponse> trackResponse = serviceMapClient.addTrack(tid);

        String trid = trackResponse.getData().getTrid();
        String trname = trackResponse.getData().getTrname();
        car.setTrid(trid);
        car.setTrname(trname);

        carMapper.updateById(car);

        return ResponseResult.success("");
    }

    /**
     * 根据车辆ID查询车辆信息
     * @param id
     * @return
     */
    public ResponseResult<Car> getCarById(Long id){
        HashMap<String,Object> map = new HashMap<>();
        map.put("id", id);
        List<Car> cars = carMapper.selectByMap(map);
        return ResponseResult.success(cars.get(0));

    }

}
