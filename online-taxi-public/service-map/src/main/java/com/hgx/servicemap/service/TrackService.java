package com.hgx.servicemap.service;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.TrackResponse;
import com.hgx.servicemap.remote.TrackClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description com.hgx.servicemap.service
 * @Author huogaoxu
 * @Date 2023-06-05 20:47
 * @Version 1.0
 **/
@Service
public class TrackService {

    @Resource
    private TrackClient trackClient;
    public ResponseResult<TrackResponse> addTrack(String tid){

        return trackClient.add(tid);
    }
}
