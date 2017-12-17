package com.northbrain.base.common.model.vo.basic;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 类名：响应值对象抽象类
 * 用途：公共报文类，用于编排服务请求原子服务时，封装DAO返回的结果，以便暴露给上层。
 * @author Jiakun
 * @version 1.0
 */
public class ResponseVO<T>
{
    //响应代码（业务级）
    private String 	responseCode;

    //响应描述（业务级）
    private String 	responseDesc;

    //响应对象
    private T response;

    public String getResponseCode() {
        return responseCode;
    }

    public ResponseVO()
    {
        this.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public void setResponseCodeAndDesc(String responseCode, String responseDesc)
    {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
    }

    public void setResponseCodeAndDesc(Errors error)
    {
        this.responseCode = error.getCode();
        this.responseDesc = error.getDesc();
    }

    public String getResponseCodeAndDesc()
    {
        return this.responseCode + this.responseDesc;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
