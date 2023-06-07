package com.hgx.servicemap.remote;

import com.hgx.internalcomm.constant.AmapConfigConstants;
import com.hgx.internalcomm.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description 调用高德地图接口获取路径信息
 * @Author huogaoxu
 * @Date 2023-01-02 17:57
 **/
@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.AmapKey}")
    private String amapKey;

    @Resource
    private RestTemplate restTemplate;

    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        //组装调用url
        /**
         * https://restapi.amap.com/v3/direction/driving?
         * origin=116.481028,39.989643&destination=116.465302,40.004717&extensions=all&
         * output=json&key=3d0e72076c56fad88ae7398a9ed6b255
         * https://restapi.amap.com/v3/direction/driving?
         * origin=116.372709,39.911337&116.460599,39.911337&extensions=base&
         * output=json&key=3d0e72076c56fad88ae7398a9ed6b255
         */
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.DIRECTION_URL)
                .append("?")
                .append("origin="+depLongitude+","+depLatitude)
                .append("&destination="+destLongitude+","+destLatitude)
                .append("&extensions=base")
                .append("&")
                .append("output=json&")
                .append("key="+amapKey);
        log.info("高德路径规划请求信息"+urlBuilder.toString());
        //调用高德接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuilder.toString(), String.class);
        String directionString = directionEntity.getBody();
        log.info("高德路径规划显示信息"+directionString);
        //解析接口
        DirectionResponse directionResponse = parseDirection(directionString);
        return directionResponse;
    }

    /**
     * 获取路径规划返回的结果数据，然后循坏拆分
     * @param directionString
     * @return
     */
    public DirectionResponse parseDirection(String directionString){
        DirectionResponse directionResponse = null;//初始化
        try {
            directionResponse = new DirectionResponse();
            //最外层json
            JSONObject result = JSONObject.fromObject(directionString);
            if (result.has(AmapConfigConstants.STATUS)){//判断状态是否存在
                int status = result.getInt(AmapConfigConstants.STATUS);
                if (status == 1) {//状态为1是正常，为0异常
                    // 判断是否是route层
                    if(result.has(AmapConfigConstants.ROUTE)){
                        JSONObject routeObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                        JSONArray pathsArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                        JSONObject pathObject = pathsArray.getJSONObject(0);
                        if(pathObject.has(AmapConfigConstants.DISTANCE)){
                            int distance = pathObject.getInt(AmapConfigConstants.DISTANCE);
                            directionResponse.setDistance(distance);
                        }
                        if(pathObject.has(AmapConfigConstants.DURATION)){
                            int duration = pathObject.getInt(AmapConfigConstants.DURATION);
                            directionResponse.setDuration(duration);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directionResponse;
    }
}
