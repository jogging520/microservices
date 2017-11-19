package com.northbrain.list.course.dto.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.AtomicServiceResponseException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.CourseVO;
import com.northbrain.base.common.model.vo.OperationRecordVO;
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
     * 方法：将原子服务返回的ServiceVO的JSON串转换成CourseVO的JSON数组
     *
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return JSON串转换成JSON数组
     */
    @Override
    public JSONArray convertToCourseVOArray(String serviceVOJSONString) throws Exception
    {
        if(serviceVOJSONString == null || serviceVOJSONString.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceVOJSONString");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        ServiceVO atomServiceVO = JSON.parseObject(serviceVOJSONString, ServiceVO.class);

        if (atomServiceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(!atomServiceVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE + atomServiceVO.getResponseCode());
            throw new AtomicServiceResponseException(Errors.ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE_EXCEPTION);
        }

        Object atomServiceVOResponse = atomServiceVO.getResponse();

        if (atomServiceVOResponse == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVOResponse");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
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
        if(object == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "object");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

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
        if(serviceVOJSONString == null || serviceVOJSONString.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceVOJSONString");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        ServiceVO atomServiceVO = JSON.parseObject(serviceVOJSONString, ServiceVO.class);

        if (atomServiceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(!atomServiceVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE + atomServiceVO.getResponseCode());
            throw new AtomicServiceResponseException(Errors.ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE_EXCEPTION);
        }

        Object atomServiceVOResponse = atomServiceVO.getResponse();

        if (atomServiceVOResponse == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVOResponse");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JSONObject.toJavaObject((JSON) atomServiceVOResponse, StorageVO.class);
    }

    /**
     * 方法：将对象转换成StorageVO
     *
     * @param object 待转换对象
     * @return StorageVO
     * @throws Exception 异常
     */
    @Override
    public StorageVO convertToStorageVO(Object object) throws Exception
    {
        if(object == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "object");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        return JSONObject.toJavaObject((JSON) object, StorageVO.class);
    }

    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成StorageVO的JSON数组
     *
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return JSON串转换成JSON数组
     * @throws Exception 异常
     */
    @Override
    public JSONArray convertToStorageVOArray(String serviceVOJSONString) throws Exception
    {
        if(serviceVOJSONString == null || serviceVOJSONString.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceVOJSONString");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        ServiceVO atomServiceVO = JSON.parseObject(serviceVOJSONString, ServiceVO.class);

        if (atomServiceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(!atomServiceVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE + atomServiceVO.getResponseCode());
            throw new AtomicServiceResponseException(Errors.ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE_EXCEPTION);
        }

        Object atomServiceVOResponse = atomServiceVO.getResponse();

        if (atomServiceVOResponse == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVOResponse");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JSONArray.class.cast(atomServiceVOResponse);
    }

    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成INTEGER
     *
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return JSON串转换成INTEGER
     */
    @Override
    public Integer convertToInteger(String serviceVOJSONString) throws Exception
    {
        if(serviceVOJSONString == null || serviceVOJSONString.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceVOJSONString");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        ServiceVO atomServiceVO = JSON.parseObject(serviceVOJSONString, ServiceVO.class);

        if (atomServiceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(!atomServiceVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE + atomServiceVO.getResponseCode());
            throw new AtomicServiceResponseException(Errors.ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE_EXCEPTION);
        }

        Object atomServiceVOResponse = atomServiceVO.getResponse();

        if (atomServiceVOResponse == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVOResponse");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return Integer.class.cast(atomServiceVOResponse);
    }

    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成boolean值
     *
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return boolean值
     * @throws Exception 异常
     */
    @Override
    public boolean convertToBoolean(String serviceVOJSONString) throws Exception
    {
        if(serviceVOJSONString == null || serviceVOJSONString.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceVOJSONString");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        ServiceVO atomServiceVO = JSON.parseObject(serviceVOJSONString, ServiceVO.class);

        if (atomServiceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(!atomServiceVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE + atomServiceVO.getResponseCode());
            throw new AtomicServiceResponseException(Errors.ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE_EXCEPTION);
        }

        Object atomServiceVOResponse = atomServiceVO.getResponse();

        if (atomServiceVOResponse == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVOResponse");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(atomServiceVOResponse.getClass() == Boolean.class)
            return (Boolean) atomServiceVOResponse;
        else
            throw new ClassCastException();
    }
}
