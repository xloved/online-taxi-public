package com.hgx.internalcomm.request;

import lombok.Data;

/**
 * @Description 轨迹上传请求参数
 * @Author huogaoxu
 * @Date 2023-06-06 9:15
 * @Version 1.0
 **/
@Data
public class PointRequest {

    private String tid;
    private String trid;

    /**
     * 引入具体上传轨迹点的信息 json数组形式，定义成数组
     */
    private PointsDTO[] points;

}


