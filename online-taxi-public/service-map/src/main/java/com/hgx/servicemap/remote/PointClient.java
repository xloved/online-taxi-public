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
    public ResponseResult upload(PointRequest pointRequest){
        // 获取高德猎鹰终端管理的轨迹点上传的URL
        StringBuilder builder = new StringBuilder();
        builder.append(AmapConfigConstants.POINT_ADD_URL)
                .append("?").append("key=").append(amapKey)
                .append("&")
                .append("sid=").append(Sid)
                .append("&")
                .append("tid=").append(pointRequest.getTid())
                .append("&")
                .append("trid=").append(pointRequest.getTrid())
                .append("&")
                .append("points=");
        // 获取具体的上传点的信息
        PointsDTO[] points = pointRequest.getPoints();
        builder.append("%5B");
        // 遍历取出数组中的值，然后使用URL编码格式把url输出
        for (PointsDTO point : points) {
            builder.append("%7B");
            String location = point.getLocation();
            String locatetime = point.getLocatetime();
            builder.append("%22location%22")
                    .append("%3A")
                    .append("%22").append(location).append("%22")
                    .append("%2C")
                    .append("%22locatetime%22")
                    .append("%3A")
                    .append("%22").append(locatetime).append("%22");

            builder.append("%7D");
        }
        builder.append("%5D");

        System.out.println("车辆位置上传请求: "+builder.toString());

        //解析返回数据的结果
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URI.create(builder.toString()),
                null, String.class);
        // 获取返回数据的结果内容
        String body = responseEntity.getBody();
       log.info("车辆位置上传响应数据"+body);

        return ResponseResult.success();

    }
}
