package com.hgx.internalcomm.response;

import lombok.Data;

/**
 * @Description 地图服务行驶距离响应内容
 * @Author huogaoxu
 * @Date 2023-01-02 16:13
 **/
@Data
public class DirectionResponse {

    private Integer distance;
    private Integer duration;
}
