package com.hgx.serviceorder.remote;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.TerminalResponse;
import com.hgx.internalcomm.response.TrsearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description com.hgx.serviceorder.remote
 * @Author huogaoxu
 * @Date 2023-07-01 17:37
 * @Version 1.0
 **/
@FeignClient("service-map")
public interface ServiceMapClient {

    /**
     * 终端周边搜索
     * @param center
     * @param radius
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/terminal/aroundsearch")
    public ResponseResult<List<TerminalResponse>> aroundsearch(@RequestParam String center, @RequestParam Integer radius);

    @RequestMapping(method = RequestMethod.POST, value = "/terminal/trsearch")
    public ResponseResult<TrsearchResponse> trsearch(@RequestParam String tid, @RequestParam Long starttime, @RequestParam Long endtime);
}
