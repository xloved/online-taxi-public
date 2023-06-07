package com.hgx.servicemap.service;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.servicemap.remote.ServiceClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description com.hgx.servicemap.service
 * @Author huogaoxu
 * @Date 2023-06-04 20:37
 * @Version 1.0
 **/
@Service
public class ServiceFromMapService {

    @Resource
    private ServiceClient serviceClient;
    public ResponseResult add(String name){

        return serviceClient.add(name);

    }
}
