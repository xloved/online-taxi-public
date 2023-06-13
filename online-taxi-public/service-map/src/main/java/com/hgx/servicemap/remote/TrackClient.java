package com.hgx.servicemap.remote;

import com.hgx.internalcomm.constant.AmapConfigConstants;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.TrackResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description com.hgx.servicemap.remote
 * @Author huogaoxu
 * @Date 2023-06-05 20:48
 * @Version 1.0
 **/
@Service
@Slf4j
public class TrackClient {

    @Value("${amap.AmapKey}")
    private String amapKey;

    @Value("${amap.Sid}")
    private String Sid;
    @Resource
    private RestTemplate restTemplate;

    /**
     * 根据name创建终端信息
     * @param tid
     * @return
     */
    public ResponseResult<TrackResponse> add(String tid) {
        // 获取高德猎鹰终端管理的创建终端的URL
        StringBuilder builder = new StringBuilder();
        builder.append(AmapConfigConstants.TRACK_ADD_URL)
                .append("?").append("key=").append(amapKey)
                .append("&")
                .append("sid=").append(Sid)
                .append("&")
                .append("tid=").append(tid);

        log.info("新增轨迹："+builder.toString());
        //解析URL结果
        ResponseEntity<String> forEntity = restTemplate.postForEntity(builder.toString(), null, String.class);
        String body = forEntity.getBody();
        log.info("新增轨迹响应数据：" + forEntity.getBody());
        // 转换成JSON
        JSONObject jsonObject = JSONObject.fromObject(body);
        // 获取整个的data数据
        JSONObject data = jsonObject.getJSONObject("data");

        // 获取data数据中具体的数据值
        String trid = data.getString("trid");
        String trname = "";
        if (data.has("trname")){
            trname = data.getString("trname");
        }
        // 创建TrackResponse，返回高德的响应值
        TrackResponse trackResponse = new TrackResponse();
        trackResponse.setTrid(trid);
        trackResponse.setTrname(trname);

        // 把返回值响应到前端
        return ResponseResult.success(trackResponse);
    }
}
