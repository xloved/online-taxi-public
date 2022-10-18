package com.hgx.internalcomm.request;

import lombok.Data;

/**
 * 经纬度通用类
 */
@Data
public class ForecastPriceDTO {

    private String depLongitude;
    private String depLatitude;
    private String destLongitude;
    private String destLatitude;

}
