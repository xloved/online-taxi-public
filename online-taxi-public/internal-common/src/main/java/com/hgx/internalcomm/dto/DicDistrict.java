package com.hgx.internalcomm.dto;

import lombok.Data;

/**
 * @Description 城市编码字典
 * @Author huogaoxu
 * @Date 2023-01-30 22:14
 **/
@Data
public class DicDistrict {

    private String addressCode;

    private String addressName;

    private String parentAddressCode;

    private Integer level;

}
