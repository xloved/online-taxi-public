package com.hgx.servicemap.remote;

import com.hgx.internalcomm.constant.AmapConfigConstants;
import com.hgx.internalcomm.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URL;

/**
 * @Description
 * @Author huogaoxu
 * @Date 2023-02-01 21:49
 **/
@Service
@Slf4j
public class MapDicDistrictClient {

    @Value("${amap.AmapKey}")
    private String amapKey;
    @Resource
    private RestTemplate restTemplate;

    public String dicDistrict(String keywords){

        /**
         * 拼装请求的URL
         * https://restapi.amap.com/v3/config/district?keywords=北京&subdistrict=2&key=<用户的key>
         */
        StringBuilder builder  = new StringBuilder();
        builder.append(AmapConfigConstants.DISTRICT_URL)
                .append("?").append("keywords=").append(keywords)
                .append("&subdistrict=3&").append("key=").append(amapKey);

        //解析结果
        ResponseEntity<String> forEntity = restTemplate.getForEntity(builder.toString(), String.class);
        //插入数据库
        return forEntity.getBody();
    }
}
