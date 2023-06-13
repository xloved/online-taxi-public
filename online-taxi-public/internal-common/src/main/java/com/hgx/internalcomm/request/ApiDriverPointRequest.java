package com.hgx.internalcomm.request;

import lombok.Data;

/**
 * @Description 司机端上传轨迹点请求值
 * @Author huogaoxu
 * @Date 2023-06-06 13:57
 * @Version 1.0
 **/
@Data
public class ApiDriverPointRequest {

    /**
     * 车辆Id
     */
    private Long carId;

    private PointsDTO[] points;

}
