package com.hgx.servicemap.remote;

import com.hgx.internalcomm.constant.AmapConfigConstants;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.ServiceResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description com.hgx.servicemap.remote
 * @Author huogaoxu
 * @Date 2023-06-04 20:38
 * @Version 1.0
 **/
@Service
public class ServiceClient {

    @Value("${amap.AmapKey}")
    private String amapKey;

    @Resource
    private RestTemplate restTemplate;

    public ResponseResult add(String name){
        StringBuilder builder  = new StringBuilder();
        builder.append(AmapConfigConstants.SERVIC_ADD_URL)
                .append("?").append("key=").append(amapKey)
                .append("&")
                .append("name=").append(name);

        //解析结果
        ResponseEntity<String> forEntity = restTemplate.postForEntity(builder.toString(),null,String.class);
        String body = forEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String sid = data.getString("sid");
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setSid(sid);
        return ResponseResult.success(serviceResponse);

    }
}
