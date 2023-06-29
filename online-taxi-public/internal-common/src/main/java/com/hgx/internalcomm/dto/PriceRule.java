package com.hgx.internalcomm.dto;

import lombok.Data;

/**
 * @Description 计价规则
 * @Author huogaoxu
 * @Date 2023-01-08 22:52
 **/
@Data
public class PriceRule {

    /**
     * 城市代码
     */
    private String cityCode;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 起步价
     */
    private Double startFare;

    /**
     * 起步里程
     */
    private Integer startMile;

    /**
     * 版本号
     */
    private Integer fareVersion;

    /**
     * 运价类型
     */
    private String fareType;

    /**
     * 计程单价
     */
    private Double unitPricePerMile;

    /**
     * 计时单价
     */
    private Double unitPricePerMinute;
}
