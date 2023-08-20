package com.hgx.internalcomm.response;

import lombok.Data;

/**
 * @Description com.hgx.internalcomm.response
 * @Author huogaoxu
 * @Date 2023-06-04 21:57
 * @Version 1.0
 **/
@Data
public class TerminalResponse {
   // private String sid;
    /**
     * 终端ID
     */
    private String tid;

    /**
     * 车辆ID
     */
    private Long carId;
    /**
     * 经度
     */
    private String longitude ;
    /**
     * 纬度
     */
    private String latitude ;
}
