package com.hgx.internalcomm.request;

import lombok.Data;

/**
 * @Description 具体上传轨迹点的信息  json数组形式，里面最多包含100个对象。
 * @Author huogaoxu
 * @Date 2023-06-06 19:43
 * @Version 1.0
 **/
@Data
public class PointsDTO {

    private String location;
    private String locatetime;
}
