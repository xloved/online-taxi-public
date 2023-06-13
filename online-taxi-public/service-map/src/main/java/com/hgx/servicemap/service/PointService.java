package com.hgx.servicemap.service;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.PointRequest;
import com.hgx.servicemap.remote.PointClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description com.hgx.servicemap.service
 * @Author huogaoxu
 * @Date 2023-06-06 9:30
 * @Version 1.0
 **/
@Service
public class PointService {

    @Resource
    private PointClient pointClient;

    public ResponseResult upload(PointRequest pointRequest){

        return pointClient.upload(pointRequest);
    }


}
