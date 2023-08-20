package com.hgx.apidriver.remote;

import com.hgx.internalcomm.request.PushRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-sse-push")
public interface ServiceSsePushClient {


//    @RequestMapping(method = RequestMethod.GET,value = "/push")
//    public String push(@RequestParam Long userId, @RequestParam String identity, @RequestParam String content);

    @RequestMapping(method = RequestMethod.POST,value = "/push")
    public String push(@RequestBody PushRequest pushRequest);
}