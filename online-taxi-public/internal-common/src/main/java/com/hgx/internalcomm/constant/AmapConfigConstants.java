package com.hgx.internalcomm.constant;

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
     * 高德地图猎鹰服务新增服务管理
     */
    public static final String SERVIC_ADD_URL = "https://tsapi.amap.com/v1/track/service/add";

    /**
     * 高德地图猎鹰服务新增终端管理
     */
    public static final String TERMINAL_ADD_URL = "https://tsapi.amap.com/v1/track/terminal/add";

    /**
     * 高德地图猎鹰服务新增轨迹
     */
    public static final String TRACK_ADD_URL = "https://tsapi.amap.com/v1/track/trace/add";

    /**
     * 高德地图猎鹰服务轨迹上传
     */
    public static final String POINT_ADD_URL = "https://tsapi.amap.com/v1/track/point/upload";

    /**
     * 高德猎鹰终端搜索
     */
    public static final String AROUNDSERACH_URL = "https://tsapi.amap.com/v1/track/terminal/aroundsearch";



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
