package com.hgx.servicemap.controller;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.TerminalResponse;
import com.hgx.servicemap.service.TerminalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 高德猎鹰——终端管理
 * @Author huogaoxu
 * @Date 2023-06-04 21:28
 * @Version 1.0
 **/
@RestController
@RequestMapping("/terminal")
public class TerminalController {

    @Resource
    private TerminalService terminalService;

    /**
     * 创建终端
     * @param name
     * @return
     */
    @PostMapping("/add")
    public ResponseResult<TerminalResponse> add(String name, String desc){
        return terminalService.add(name, desc);
    }

    /**
     * 终端周边搜索
     * @param center
     * @param radius
     * @return
     */
    @PostMapping("/aroundsearch")
    public ResponseResult<List<TerminalResponse>> aroundsearch(String center, Integer radius){

        return terminalService.aroundsearch(center, radius);
    }

    /**
     * 轨迹查询
     * @param tid
     * @param starttime
     * @param endtime
     * @return
     */
    @PostMapping("/trsearch")
    public ResponseResult trsearch(String tid, Long starttime , Long endtime){

        return terminalService.trsearch(tid,starttime,endtime);
    }
}
