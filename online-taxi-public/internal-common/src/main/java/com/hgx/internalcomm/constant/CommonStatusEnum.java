package com.hgx.internalcomm.constant;


import lombok.Getter;


/**
 * 定义返回值状态通用类
 */
public enum CommonStatusEnum {
    //定义验证码错误
    VERIFICATION_CODE_ERRROR(1099,"验证码不正确"),

    //token类提示，1100-1199
    TOKEN_ERROR(1199,"token错误"),

    //user类提示，1200-1299
    USER_MESS_ERROR(1299,"用户不存在"),

    //计价规则，1300-1399
    PRICE_RULE_EMPTY(1300,"计价规则不存在"),

    PRICE_RULE_EXISTS(1301, "计价规则已存在，不允许添加"),

    PRICE_RULE_NOT_EDIT(1302, "计价规则没有发生变化"),
    PRICE_RULE_CHANGE(1303,"计价规则发生变化"),

    //地图信息，1400-1499
    MAP_DISTRICT_ERROR(1400, "请求地图错误"),

    /**
     * 司机和车辆：1500-1599
     */
    DRIVER_CAR_BIND_NOT_EXITS(1500,"司机和车辆未绑定"),
    DRIVER_NOT_EXITS(1501,"司机不存在"),
    DRIVER_CAR_BIND_EXITS(1502,"司机和车辆绑定关系已存在，请勿重复绑定"),
    DRIVER_BIND_EXITS(1503,"司机已经被绑定了，请勿重复绑定"),
    CAR_BIND_EXITS(1504,"车辆已经被绑定了，请勿重复绑定"),
    CITY_DRIVER_EMPTY(1505,"当前城市没有可用的司机"),

    /**
     * 订单：1600-1699
     */
    ORDER_GOING_ON(1600, "有正在进行的订单"),
    DEVICE_IS_BLACK(1601,"该设备超过下单次数"),
    CITY_SERVICE_NOT_SERVICE(1602,"当前城市不提供叫车服务"),



    //定义返回成功
    SUCCESS(1,"success"),
    //定义返回失败
    FAIL(0,"fail");

    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

}
