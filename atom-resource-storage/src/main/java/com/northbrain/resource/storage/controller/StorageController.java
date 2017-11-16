package com.northbrain.resource.storage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.resource.storage.service.IStorageService;

import feign.FeignException;

/**
 * 类名：存储控制层类（原子服务）
 * 用途：解析http servlet，调用service层服务，返回给服务编排层应答数据。
 */
@RestController
@RequestMapping(Constants.URI_ATOM_RESOURCE_STORAGE_REQUEST_MAPPING)
public class StorageController
{
    private static Logger logger = Logger.getLogger(StorageController.class);
    private final IStorageService storageService;

    @Autowired
    public StorageController(IStorageService storageService)
    {
        this.storageService = storageService;
    }

    /**
     * 方法：读取存储信息
     * @return 以ServiceVO封装的存储信息
     */
    @RequestMapping(value= Constants.URI_ATOM_RESOURCE_STORAGE_SPECIFIED_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    public String readStorage(@PathVariable("storageId")int storageId)
    {
        logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "readStorage");
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(storageId <= 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "storageId:" + storageId);
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            if(storageService == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageService");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            return JSON.toJSONString(storageService.readStorage(storageId));
        }
        catch(IllegalStateException illegalStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_STATE_EXCEPTION);
        }
        catch (JSONException jSONException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(jSONException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_JSON_EXCEPTION);
        }
        catch (FeignException feignException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(feignException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FEIGN_EXCEPTION);
        }
        catch(Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        return JSON.toJSONString(serviceVO);
    }

    /**
     * 方法：读取一组存储信息
     * @return 以ServiceVO封装的存储信息
     */
    @RequestMapping(method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    public String readStorages(@RequestBody List<Integer> storageIds)
    {
        logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "readStorage");
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(storageIds == null || storageIds.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "storageIds");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            if(storageService == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageService");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            return JSON.toJSONString(storageService.readStorages(storageIds));
        }
        catch(IllegalStateException illegalStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_STATE_EXCEPTION);
        }
        catch (JSONException jSONException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(jSONException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_JSON_EXCEPTION);
        }
        catch (FeignException feignException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(feignException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FEIGN_EXCEPTION);
        }
        catch(Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        return JSON.toJSONString(serviceVO);
    }

    /**
     * 方法 ：Json日期格式转换
     * @param servletRequestDataBinder 前端属性在后台封装成一个对象
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder servletRequestDataBinder)
    {
        servletRequestDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(Constants.BUSINESS_COMMON_JSON_REQUEST_DATE_FORMART), false));
    }
}
