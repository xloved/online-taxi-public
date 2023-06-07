package com.hgx.servicemap.remote;

import com.hgx.internalcomm.constant.AmapConfigConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description 调用高德web服务中的行政区域查询接口
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

    /**
     *
     * @param keywords
     * @return
     */
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

        //返回具体的结果信息，插入数据库
        return forEntity.getBody();
    }
}
