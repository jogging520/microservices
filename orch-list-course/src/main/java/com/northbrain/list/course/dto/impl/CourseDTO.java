package com.northbrain.list.course.dto.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.CourseVO;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.base.common.model.vo.StorageVO;
import com.northbrain.list.course.dto.ICourseDTO;

/**
 * 类名：课程数据转换对象接口的实现类
 * 用途：转换课程相关的数据
 * @author Jiakun
 * @version 1.0
 */
@Component
public class CourseDTO implements ICourseDTO
{
    private static Logger logger = Logger.getLogger(CourseDTO.class);
    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成JSON数组
     *
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return JSON串转换成JSON数组
     */
    @Override
    public JSONArray convertToCourseVOArray(String serviceVOJSONString) throws Exception
    {
        ServiceVO atomServiceVO = JSON.parseObject(serviceVOJSONString, ServiceVO.class);

        if (atomServiceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVO");
            return null;
        }

        if(!atomServiceVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE + atomServiceVO.getResponseCode());
            return null;
        }

        Object atomServiceVOResponse = atomServiceVO.getResponse();

        if (atomServiceVOResponse == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVOResponse");
            return null;
        }

        return JSONArray.class.cast(atomServiceVOResponse);
    }

    /**
     * 方法：将对象转换成CourseVO
     *
     * @param object 待转换对象
     * @return CourseVO
     * @throws Exception 异常
     */
    @Override
    public CourseVO convertToCourseVO(Object object) throws Exception
    {
        return JSONObject.toJavaObject((JSON) object, CourseVO.class);
    }

    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成StorageVO对象
     *
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return StorageVO对象
     * @throws Exception 异常
     */
    @Override
    public StorageVO convertToStorageVO(String serviceVOJSONString) throws Exception
    {
        ServiceVO atomServiceVO = JSON.parseObject(serviceVOJSONString, ServiceVO.class);

        if (atomServiceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVO");
            return null;
        }

        if(!atomServiceVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE + atomServiceVO.getResponseCode());
            return null;
        }

        Object atomServiceVOResponse = atomServiceVO.getResponse();

        if (atomServiceVOResponse == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVOResponse");
            return null;
        }

        return JSONObject.toJavaObject((JSON) atomServiceVOResponse, StorageVO.class);
    }
}
