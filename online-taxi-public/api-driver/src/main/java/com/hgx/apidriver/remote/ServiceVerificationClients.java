package com.hgx.apidriver.remote;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description 调用service-verificationcode 生成验证码
 * @Author huogaoxu
 * @Date 2023-06-04 13:45
 * @Version 1.0
 **/
@FeignClient("service-verificationcode")
public interface ServiceVerificationClients {

    @RequestMapping(method = RequestMethod.GET,value = "/numberCode/{size}")
    public ResponseResult<NumberCodeResponse> numberCode(@PathVariable("size") int size);
}
