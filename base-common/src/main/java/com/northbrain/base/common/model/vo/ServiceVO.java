package com.northbrain.base.common.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.northbrain.base.common.model.bo.Errors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类名：Service值对象
 * 用途：公共报文类，用于service对请求和响应报文进行封装，包含请求和响应的流水号、时间、域等信息，以便溯源、统计、分析。
 * 微服务层的响应代码为细粒度（未映射）的代码，由编排层统一封装并暴露给展现层。
 * @author Jiakun
 * @version 1.0
 */
public class ServiceVO
{
    @JSONField(ordinal = 1)
    private String 	requestTime;			//请求时间
    @JSONField(ordinal = 2)
    private String 	responseTime;			//响应时间
    @JSONField(ordinal = 3)
    private String 	responseCode;			//响应代码（业务级）
    @JSONField(ordinal = 4)
    private String 	responseDesc;	        //响应描述（业务级）
    @JSONField(ordinal = 5)
    private Object  response;               //响应对象

    public ServiceVO()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

        Date date = new Date();
        this.setRequestTime(dateFormat.format(date));
        this.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
    }

    public String getRequestTime()
    {
        return requestTime;
    }

    private void setRequestTime(String requestTime)
    {
        this.requestTime = requestTime;
    }

    public void setRequestTime(Date requestTime)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

        this.requestTime = dateFormat.format(requestTime);
    }

    public String getResponseTime()
    {
        return responseTime;
    }

    public void setResponseTime(String responseTime)
    {
        this.responseTime = responseTime;
    }

    public void setResponseTime(Date responseTime)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

        this.responseTime = dateFormat.format(responseTime);
    }

    public String getResponseCode()
    {
        return responseCode;
    }

    public void setResponseCode(String responseCode)
    {
        this.responseCode = responseCode;
    }

    public String getResponseDesc()
    {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc)
    {
        this.responseDesc = responseDesc;
    }
    
    public void setResponseCodeAndDesc(Errors error)
    {
    	this.responseCode = error.getCode();
    	this.responseDesc = error.getDesc();
    }

    public void setResponse(Object response)
    {
        this.response = response;
    }

    public Object getResponse()
    {
        return this.response;
    }
}
