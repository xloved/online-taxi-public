package com.hgx.apidriver.remote;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.PointRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description 调用service-map服务
 * @Author huogaoxu
 * @Date 2023-06-06 15:21
 * @Version 1.0
 **/
@FeignClient("service-map")
public interface ServiceMapClient {

    /**
     * 轨迹点上穿
     * @param pointRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/point/upload")
    ResponseResult uplod(@RequestBody PointRequest pointRequest);
}
