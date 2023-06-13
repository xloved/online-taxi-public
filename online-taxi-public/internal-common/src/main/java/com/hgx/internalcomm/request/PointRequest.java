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

    /**
     * 终端id
     */
    private String tid;

    /**
     * 轨迹id
     */
    private String trid;

    /**
     * 轨迹信息
     */
    private PointsDTO[] points;

}


