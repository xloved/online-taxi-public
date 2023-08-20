package com.hgx.servicemap.remote;

import com.hgx.internalcomm.constant.AmapConfigConstants;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.TerminalResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description service-map服务调用高德猎鹰轨迹服务，创建终端管理
 * @Author huogaoxu
 * @Date 2023-06-04 21:54
 * @Version 1.0
 **/
@Service
@Slf4j
public class TerminalClient {

    @Value("${amap.AmapKey}")
    private String amapKey;

    @Value("${amap.Sid}")
    private String Sid;
    @Resource
    private RestTemplate restTemplate;

    /**
     * 根据name创建终端信息
     * @param name
     * @return
     */
    public ResponseResult<TerminalResponse> add(String name,String desc){
        // 获取高德猎鹰终端管理的创建终端的URL
        StringBuilder builder  = new StringBuilder();
        builder.append(AmapConfigConstants.TERMINAL_ADD_URL)
                .append("?").append("key=").append(amapKey)
                .append("&")
                .append("sid=").append(Sid)
                .append("&")
                .append("name=").append(name)
                .append("&")
                .append("desc=").append(desc);
        log.info("创建终端的请求："+builder.toString());

        //解析URL结果
        ResponseEntity<String> forEntity = restTemplate.postForEntity(builder.toString(),null,String.class);
        log.info("创建终端的响应："+forEntity.getBody());
        String body = forEntity.getBody();
        // 转换成JSON
        JSONObject jsonObject = JSONObject.fromObject(body);
        // 获取整个的data数据
        JSONObject data = jsonObject.getJSONObject("data");
        /**
         *         "sid": "951878",
         *         "tid": "702701516"
         */
        //String sid = data.getString("sid");
        // 获取data数据中具体的数据值
        String tid = data.getString("tid");
        // 创建TerminalResponse，返回高德的响应值
        TerminalResponse terminalResponse = new TerminalResponse();
        //terminalResponse.setSid(sid);
        terminalResponse.setTid(tid);
        // 把返回值响应到前端
        return ResponseResult.success(terminalResponse);

    }

    /**
     * 终端周边搜索
     *
     * @param center
     * @param radius
     * @return
     */
    public ResponseResult<List<TerminalResponse>> aroundsearch(String center, Integer radius){
        StringBuilder builder  = new StringBuilder();
        builder.append(AmapConfigConstants.AROUNDSERACH_URL)
                .append("?").append("key=").append(amapKey)
                .append("&")
                .append("sid=").append(Sid)
                .append("&")
                .append("center=").append(center)
                .append("&")
                .append("radius=").append(radius);
        log.info("搜索终端的请求："+builder.toString());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(builder.toString(), null, String.class);
        String body = responseEntity.getBody();
        log.info("搜索终端的响应："+body);
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray array = data.getJSONArray("results");
        List<TerminalResponse> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            TerminalResponse terminalResponse = new TerminalResponse();
            JSONObject arrayJSONObject = array.getJSONObject(i);
            String tid = arrayJSONObject.getString("tid");
            // desc 是carid
            String desc = arrayJSONObject.getString("desc");
            Long carId = Long.parseLong(desc);

            JSONObject location = jsonObject.getJSONObject("location");
            String longitude = location.getString("longitude");
            String latitude = location.getString("latitude");

            terminalResponse.setCarId(carId);
            terminalResponse.setTid(tid);
            terminalResponse.setLongitude(longitude);
            terminalResponse.setLatitude(latitude);
            list.add(terminalResponse);

        }

        return ResponseResult.success(list);
    }
}
