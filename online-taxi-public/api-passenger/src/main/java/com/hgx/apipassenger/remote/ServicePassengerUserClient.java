package com.hgx.apipassenger.remote;


import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *注册乘客服务
 */
@FeignClient(value = "service-passenger-user")
public interface ServicePassengerUserClient {

    @RequestMapping(method = RequestMethod.POST,value = "/user")
     ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);

}
