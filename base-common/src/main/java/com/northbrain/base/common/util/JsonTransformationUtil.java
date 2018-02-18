package com.northbrain.base.common.util;

import java.lang.reflect.ParameterizedType;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.basic.ResponseVO;
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
     * 方法：将原子服务返回的ServiceVO的JSON串转换成具体的泛型值
     *
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return ResponseVO封装的响应体及泛型值
     * @throws Exception 异常
     */
    public static<T> ResponseVO<T> transformJSONString(String serviceVOJSONString) throws Exception
    {
        if(serviceVOJSONString == null || serviceVOJSONString.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceVOJSONString");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_JSON_VO_CONVERTION);

        ServiceVO serviceVO = JSON.parseObject(serviceVOJSONString, ServiceVO.class);

        if (serviceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "serviceVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        Object serviceVOResponse = serviceVO.getResponse();

        if (serviceVOResponse == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "serviceVOResponse");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }


        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setResponseCode(serviceVO.getResponseCode());
        responseVO.setResponseDesc(serviceVO.getResponseDesc());

        responseVO.setResponse((T) (serviceVOResponse));

        return responseVO;
    }

    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成各类通用VO的JSON数组
     *
     * @param serviceVOJSONString 调用课程原子服务返回的JSON串
     * @return ResponseVO封装的响应体及JSON数组
     */
    public static ResponseVO<JSONArray> transformJSONStringIntoVOArray(String serviceVOJSONString) throws Exception
    {
        if(serviceVOJSONString == null || serviceVOJSONString.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceVOJSONString");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_JSON_VO_CONVERTION);

        ServiceVO serviceVO = JSON.parseObject(serviceVOJSONString, ServiceVO.class);

        if (serviceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "serviceVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        Object serviceVOResponse = serviceVO.getResponse();

        if (serviceVOResponse == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "serviceVOResponse");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        ResponseVO<JSONArray> responseVO = new ResponseVO<>();
        responseVO.setResponseCode(serviceVO.getResponseCode());
        responseVO.setResponseDesc(serviceVO.getResponseDesc());

        if(serviceVOResponse.getClass() == JSONArray.class)
            responseVO.setResponse(JSONArray.class.cast(serviceVOResponse));
        else
            throw new ClassCastException();

        return responseVO;
    }

    /**
     * 方法：将原子服务返回的ServiceVO的JSON串转换成通用的值对象，由调用者进行强制转换
     *
     * @param serviceVOJSONString 调用原子服务返回的JSON串
     * @param clazz 被转换对象的类型
     * @return 被转换的对象
     * @throws Exception 异常
     */
    public static <T> ResponseVO<T> transformJSONStringIntoValueObject(String serviceVOJSONString, Class<T> clazz) throws Exception
    {
        if(serviceVOJSONString == null || serviceVOJSONString.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceVOJSONString");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_JSON_VO_CONVERTION);

        ServiceVO serviceVO = JSON.parseObject(serviceVOJSONString, ServiceVO.class);

        if (serviceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "serviceVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        Object serviceVOResponse = serviceVO.getResponse();

        if (serviceVOResponse == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "serviceVOResponse");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        ResponseVO<Object> responseVO = new ResponseVO<>();
        responseVO.setResponseCode(serviceVO.getResponseCode());
        responseVO.setResponseDesc(serviceVO.getResponseDesc());

        responseVO.setResponse(JSONObject.toJavaObject((JSON) serviceVOResponse, clazz.getClass()));

        return (ResponseVO<T>) responseVO;
    }

    /**
     * 方法：将对象转换成通用的值对象，由引用者进行强制转换。
     *
     * @param object 待转换对象
     * @param clazz 被转换对象的类型
     * @return 被转换的值对象
     * @throws Exception 异常
     */
    public static <T> T transformPlainObjectIntoValueObject(Object object, Class<T> clazz) throws Exception
    {
        if(object == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "object");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_JSON_VO_CONVERTION);

        return JSONObject.toJavaObject((JSON) object, (Class<T>) clazz.getClass());
    }
}
