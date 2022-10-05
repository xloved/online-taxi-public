package com.hgx.internalcomm.dto;

import com.hgx.internalcomm.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;

    //默认成功返回的方法
    public static<T> ResponseResult success(){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue());
    }

    /**
     * 成功响应前端传入的状态方法
     * @param data
     * @param <T>
     * @return
     */
    public static<T> ResponseResult success(T data){

        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).
                setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }

    /**
     * 默认失败方法，不建议使用
     * @param data
     * @param <T>
     * @return
     */
    public static<T> ResponseResult fail(T data){
        return new ResponseResult().setData(data);
    }

    /**
     * 自定义失败方法，会显示错误码和提示信息
     * @param code
     * @param message
     * @return
     */
    public static ResponseResult fail(int code,String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * 自定义失败方法：显示错误码，提示信息和具体错误
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ResponseResult fail(int code,String message, String data){
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }
}
