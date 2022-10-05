package com.hgx.apipassenger.remote;


import com.hgx.internalcomm.dto.PassengerUser;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 *注册乘客服务
 */
@FeignClient(value = "service-passenger-user")
public interface ServicePassengerUserClient {

    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);

    @RequestMapping (method = RequestMethod.GET,value="/selectuser/{phone}")
    public ResponseResult<PassengerUser> getUserByPhone(@PathVariable("phone") String passengerPhone);

}
