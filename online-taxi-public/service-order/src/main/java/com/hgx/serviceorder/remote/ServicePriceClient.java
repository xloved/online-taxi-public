package com.hgx.serviceorder.remote;

import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description 调用service-price服务
 * @Author huogaoxu
 * @Date 2023-06-21 15:46
 * @Version 1.0
 **/
@FeignClient("service-price")
public interface ServicePriceClient {

    /**
     * 判断当前计价规则是否是最新的
     * @param fareType
     * @param fareVersion
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/price-rule/isNewVersion")
    public ResponseResult isNewVersion(@RequestParam String fareType, @RequestParam Integer fareVersion);
}
