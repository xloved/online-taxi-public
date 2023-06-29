package com.hgx.internalcomm.response;

import lombok.Data;

/**
 * @Description 地图服务行驶距离响应内容
 * @Author huogaoxu
 * @Date 2023-01-02 16:13
 **/
@Data
public class DirectionResponse {

    // 行驶距离
    private Integer distance;

    // 行驶时长
    private Integer duration;
}
