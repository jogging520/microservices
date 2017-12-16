package com.northbrain.base.common.util;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.AtomicServiceResponseException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.basic.ServiceVO;

/**
 * 类名：Json转换工具类
 * 用途：将原子服务返回的ServiceVO的JSON串转换成基础和复杂对象
 * @author Jiakun
 * @version 1.0
 */
public class JsonTransformationUtil
{
    private static Logger logger = Logger.getLogger(JsonTransformationUtil.class);

    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成INTEGER
     *
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return JSON串转换成INTEGER
     */
    public static Integer transformJSONStringIntoInteger(String serviceVOJSONString) throws Exception
    {
        if(serviceVOJSONString == null || serviceVOJSONString.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceVOJSONString");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_JSON_VO_CONVERTION);

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
    public static boolean transformJSONStringIntoBoolean(String serviceVOJSONString) throws Exception
    {
        if(serviceVOJSONString == null || serviceVOJSONString.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceVOJSONString");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_JSON_VO_CONVERTION);

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

    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成各类通用VO的JSON数组
     *
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return JSON串转换成JSON数组
     */
    public static JSONArray transformJSONStringIntoVOArray(String serviceVOJSONString) throws Exception
    {
        if(serviceVOJSONString == null || serviceVOJSONString.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceVOJSONString");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_JSON_VO_CONVERTION);

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
     * 方法：将原子服务返回的ServiceVO的JSON串转换成通用的值对象，由调用者进行强制转换
     *
     * @param serviceVOJSONString 调用原子服务返回的JSON串
     * @return StorageVO对象
     * @throws Exception 异常
     */
    public static Object transformJSONStringIntoValueObject(String serviceVOJSONString, Class clazz) throws Exception
    {
        if(serviceVOJSONString == null || serviceVOJSONString.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceVOJSONString");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_JSON_VO_CONVERTION);

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

        return JSONObject.toJavaObject((JSON) atomServiceVOResponse, clazz.getClass());
    }

    /**
     * 方法：将对象转换成通用的值对象，由引用者进行强制转换。
     *
     * @param object 待转换对象
     * @return CourseVO
     * @throws Exception 异常
     */
    public static Object transformPlainObjectIntoValueObject(Object object, Class clazz) throws Exception
    {
        if(object == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "object");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_JSON_VO_CONVERTION);

        return JSONObject.toJavaObject((JSON) object, clazz.getClass());
    }
}
