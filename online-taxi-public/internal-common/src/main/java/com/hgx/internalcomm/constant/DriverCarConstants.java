package com.hgx.internalcomm.constant;

/**
 * @Description com.hgx.internalcomm.constant
 * @Author huogaoxu
 * @Date 2023-05-31 20:48
 * @Version 1.0
 **/
public class DriverCarConstants {

    /**
     * 司机车辆绑定状态：绑定
     */
    public static int DRIVER_CAR_BIND = 1;
    /**
     * 司机车辆绑定状态：解绑
     */
    public static int DRIVER_CAR_UNBIND = 2;

    /**
     *  司机状态：1 有效
     */
    public static int DRIVER_STATE_VALD = 1;

    /**
     *  司机状态：0 无效
     */
    public static int DRIVER_STATE_INVALD = 0;


    /**
     * 司机状态 ：存在 1
     */
    public static int DRIVER_EXISTS = 1;

    /**
     * 司机状态 ：不存在 0
     */
    public static int DRIVER_NOT_EXISTS = 0;

    /**
     * 司机工作状态 0：收车
     */
    public static int DRIVER_WORK_STATUS_STOP = 0;

    /**
     * 司机工作状态 1：出车
     */
    public static int DRIVER_WORK_STATUS_START = 1;

    /**
     * 司机工作状态 0：暂停接单
     */
    public static int DRIVER_WORK_STATUS_SUSPEND = 2;


}
