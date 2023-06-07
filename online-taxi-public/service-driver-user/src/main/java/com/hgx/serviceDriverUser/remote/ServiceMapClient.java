package com.hgx.serviceDriverUser.remote;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.TerminalResponse;
import com.hgx.internalcomm.response.TrackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description 调用service-map 服务的创建终端接口
 * @Author huogaoxu
 * @Date 2023-06-04 22:56
 * @Version 1.0
 **/
@FeignClient("service-map")
public interface ServiceMapClient {

    @RequestMapping(method = RequestMethod.POST, value = "/terminal/add")
    public ResponseResult<TerminalResponse> add(@RequestParam String name, @RequestParam String desc);

    @RequestMapping(method = RequestMethod.POST, value = "/track/add")
    public ResponseResult<TrackResponse> addTrack(@RequestParam String tid);
}
