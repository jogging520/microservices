package com.northbrain.list.course.dto;

import com.alibaba.fastjson.JSONArray;
import com.northbrain.base.common.model.vo.CourseVO;
import com.northbrain.base.common.model.vo.StorageVO;

/**
 * 类名：课程数据转换对象接口
 * 用途：转换课程相关的数据
 * @author Jiakun
 * @version 1.0
 */
public interface ICourseDTO
{
    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成JSON数组
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return JSON串转换成JSON数组
     * @throws Exception 异常
     */
    JSONArray convertToCourseVOArray(String serviceVOJSONString) throws Exception;

    /**
     * 方法：将对象转换成CourseVO
     * @param object 待转换对象
     * @return CourseVO
     * @throws Exception 异常
     */
    CourseVO convertToCourseVO(Object object) throws Exception;

    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成StorageVO对象
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return StorageVO对象
     * @throws Exception 异常
     */
    StorageVO convertToStorageVO(String serviceVOJSONString) throws Exception;

    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成INTEGER
     *
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return JSON串转换成INTEGER
     */
    Integer convertToInteger(String serviceVOJSONString) throws Exception;
}
