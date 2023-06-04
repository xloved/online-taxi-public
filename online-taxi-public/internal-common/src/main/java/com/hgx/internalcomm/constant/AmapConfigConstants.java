package com.hgx.internalcomm.constant;

import javax.sql.rowset.serial.SerialStruct;

/**
 * @Description
 * @Author huogaoxu
 * @Date 2023-01-02 18:03
 **/
public class AmapConfigConstants {

    /**
     * 路径规划地址
     */
    public static final String DIRECTION_URL = "https://restapi.amap.com/v3/direction/driving";

    /**
     * 省政区地址
     */
    public static final String DISTRICT_URL = "https://restapi.amap.com/v3/config/district";

    /**
     * 路径规划json key的值
     */
    public static final String STATUS = "status";

    /**
     * 	驾车路径规划信息列表
     */
    public static final String ROUTE = "route";

    /**
     * 	驾车换乘方案
     */
    public static final String PATHS = "paths";

    /**
     * 路径规划，行驶距离
     */
    public static final String DISTANCE = "distance";

    /**
     * 路径规划，预计行驶时间
     */
    public static final String DURATION = "duration";

    /**
     * 地图相关json信息
     */
    public static final String DISTRICTS = "districts";
    public static final String ADCODE = "adcode";
    public static final String NAME = "name";
    public static final String LEVEL = "level";
    public static final String STREET = "street";

}
