package com.hgx.serviceorder.remote;

import com.hgx.internalcomm.request.PushRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-sse-push")
public interface ServiceSsePushClient {

//    @RequestMapping(method = RequestMethod.GET,value = "/push")
//    public String push(@RequestParam Long userId, @RequestParam String identity, @RequestParam String content);

    @PostMapping(value = "/push")
    public String push(@RequestBody PushRequest pushRequest);
}