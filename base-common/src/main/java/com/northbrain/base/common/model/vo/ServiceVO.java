package com.northbrain.base.common.model.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;

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
    @JSONField(format= Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date  	requestTime;			//请求时间
    @JSONField(format=Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date 	responseTime;			//响应时间
    private String 	responseCode;			//响应代码（业务级）
    private String 	responseDesc;	        //响应描述（业务级）
    private Object  response;               //响应对象

    public ServiceVO()
    {
        this.setRequestTime(new Date());
        this.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
    }

    public Date getRequestTime()
    {
        return requestTime;
    }

    private void setRequestTime(Date requestTime)
    {
        this.requestTime = requestTime;
    }

    public Date getResponseTime()
    {
        return responseTime;
    }

    public void setResponseTime(Date responseTime)
    {
        this.responseTime = responseTime;
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

    public String toString()
    {
        return JSON.toJSONString(this);
    }
}
