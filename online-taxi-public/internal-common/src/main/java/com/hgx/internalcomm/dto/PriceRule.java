package com.hgx.internalcomm.dto;

import lombok.Data;

/**
 * @Description 区域省市
 * @Author huogaoxu
 * @Date 2023-01-08 22:52
 **/
@Data
public class PriceRule {

    private String cityCode;
    private String vehicleType;
    private Double startFare;
    private Integer startMile;
    private Double unitPricePerMile;
    private Double unitPricePerMinute;
}
