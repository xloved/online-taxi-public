package com.hgx.servicemap.remote;

import com.hgx.internalcomm.constant.AmapConfigConstants;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.PointRequest;
import com.hgx.internalcomm.request.PointsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

/**
 * @Description com.hgx.servicemap.remote
 * @Author huogaoxu
 * @Date 2023-06-06 9:31
 * @Version 1.0
 **/
@Service
@Slf4j
public class PointClient {

    @Value("${amap.AmapKey}")
    private String amapKey;

    @Value("${amap.Sid}")
    private String Sid;
    @Resource
    private RestTemplate restTemplate;

    /**
     * 轨迹点信息上传
     * @param pointRequest
     * @return
     */
    public ResponseResult upload(PointRequest pointRequest) {
        //拼装请求url
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.POINT_UPLOAD_URL);
        urlBuilder.append("?key=" + amapKey);
        urlBuilder.append("&");
        urlBuilder.append("sid=" + Sid);
        urlBuilder.append("&");
        urlBuilder.append("tid=").append(pointRequest.getTid());
        urlBuilder.append("&");
        urlBuilder.append("trid=" + pointRequest.getTrid());
        urlBuilder.append("&");
        urlBuilder.append("points=");
        PointsDTO[] points = pointRequest.getPoints();
        urlBuilder.append("%5B");
        for (PointsDTO point : points) {
            urlBuilder.append("%7B");
            String locatetime = point.getLocatetime();
            String location = point.getLocation();
            urlBuilder.append("%22location%22");
            urlBuilder.append("%3A");
            urlBuilder.append("%22" + location + "%22");
            urlBuilder.append("%2C");
            urlBuilder.append("%22locatetime%22");
            urlBuilder.append("%3A");
            urlBuilder.append(locatetime);
            urlBuilder.append("%7D");
        }
        urlBuilder.append("%5D");
        log.info("上传轨迹点url：" + urlBuilder.toString());

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(URI.create(urlBuilder.toString()), "", String.class);
        String body = stringResponseEntity.getBody();
        log.info("高德地图响应数据：" + body);

        return ResponseResult.success();
    }
}
