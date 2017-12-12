package com.northbrain.foundation.authentication.dto;

import com.alibaba.fastjson.JSONArray;

/**
 * 类名：鉴权数据转换对象接口
 * 用途：转换鉴权相关的数据，包括注册、登录、权限、参数等
 * @author Jiakun
 * @version 1.0
 */
public interface IAuthenticationDTO
{
    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成StorageVO的JSON数组
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return JSON串转换成JSON数组
     * @throws Exception 异常
     */
    JSONArray convertToStorageVOArray(String serviceVOJSONString) throws Exception;

    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成INTEGER
     *
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return JSON串转换成INTEGER
     */
    Integer convertToInteger(String serviceVOJSONString) throws Exception;

    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成boolean值
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return boolean值
     * @throws Exception 异常
     */
    boolean convertToBoolean(String serviceVOJSONString) throws Exception;
}
