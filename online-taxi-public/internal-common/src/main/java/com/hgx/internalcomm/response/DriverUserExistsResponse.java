package com.hgx.internalcomm.response;

import lombok.Data;

/**
 * @Description com.hgx.internalcomm.response
 * @Author huogaoxu
 * @Date 2023-06-03 23:12
 * @Version 1.0
 **/
@Data
public class DriverUserExistsResponse {

    private String driverPhone;

    /**
     * 判断司机是否存在：存在 1，不存在 0
     */
    private int ifExists;
}
