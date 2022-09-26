package com.hgx.internalcomm.dto;

import lombok.Data;

//定义token令牌内容
@Data
public class TokenResult {
    private String passengerPhone;
    private String identity;
}
