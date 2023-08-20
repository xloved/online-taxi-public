package com.hgx.servicemap.service;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.TerminalResponse;
import com.hgx.internalcomm.response.TrsearchResponse;
import com.hgx.servicemap.remote.TerminalClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 高德猎鹰终端服务
 * @Author huogaoxu
 * @Date 2023-06-04 21:54
 * @Version 1.0
 **/
@Service
public class TerminalService {

    @Resource
    private TerminalClient terminalClient;

    /**
     * 创建终端
     * @param name
     * @return
     */
    public ResponseResult<TerminalResponse> add(String name,String desc){
        return terminalClient.add(name, desc);
    }


    /**
     * 周边搜索终端
     * @param center
     * @param radius
     * @return
     */
    public ResponseResult<List<TerminalResponse>> aroundsearch(String center, Integer radius){

        return terminalClient.aroundsearch(center, radius);
    }

    public ResponseResult<TrsearchResponse> trsearch(String tid , Long starttime , Long endtime){

        return terminalClient.trsearch(tid,starttime,endtime);
    }

}
