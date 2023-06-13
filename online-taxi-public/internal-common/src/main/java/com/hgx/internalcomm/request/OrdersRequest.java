package com.hgx.internalcomm.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 乘客下单请求参数
 * @Author huogaoxu
 * @Date 2023-06-12 9:19
 * @Version 1.0
 **/
@Data
public class OrdersRequest {

    /**
     * 乘客下单区域地址
     */
    private String address;
    /**
     * 乘客用车时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departTime;

    /**
     * 乘客下单时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;
    /**
     * 乘客出发地址
     */

    private String departure;

    /**
     * 乘客出发地址经度
     */
    private String depLongitude;

    /**
     * 乘客出发地址纬度
     */
    private String depLatitude;

    /**
     * 乘客要去的目的地地址
     */
    private String destination;

    /**
     * 乘客要去的目的地经度
     */
    private String destLongitude;

    /**
     * 乘客要去的目的地纬度
     */
    private String destLatitude;

    /**
     * 坐标加密标识
     * 1：gcj-02
     * 2：wgs84
     * 3：bd-09
     * 4：cgcs2000北斗
     * 0：其他
     */
    private Integer encrypt;

    /**
     * 运价类型编码
     */
    private String fareType;

}
