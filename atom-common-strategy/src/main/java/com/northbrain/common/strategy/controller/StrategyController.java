package com.northbrain.common.strategy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.northbrain.base.common.model.vo.basic.ServiceVO;
import com.northbrain.base.common.model.vo.atom.StrategyVO;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.common.strategy.service.IStrategyService;

/**
 * 类名：策略控制层类（原子服务）
 * 用途：解析http servlet，调用service层服务，返回给服务编排层应答数据。
 */
@RestController
public class StrategyController
{
    private static Logger logger = Logger.getLogger(StrategyController.class);

    private final IStrategyService strategyService;

    @Autowired
    public StrategyController(IStrategyService strategyService)
    {
        this.strategyService = strategyService;
    }

    /**
     * 方法：新增一条策略
     * @return 以ServiceVO封装的策略
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_STRATEGY_REQUEST_MAPPING, method = RequestMethod.PUT, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    public String createStrategy(@RequestBody StrategyVO strategyVO)
    {
        logger.debug(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "createStrategy");
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(strategyVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "strategyVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            if(strategyService == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyService");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            return JSON.toJSONString(strategyService.createStrategy(strategyVO));
        }
        catch (IllegalStateException illegalStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_STATE_EXCEPTION);
        }
        catch (JSONException jSONException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(jSONException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_JSON_EXCEPTION);
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        return JSON.toJSONString(serviceVO);
    }

    /**
     * 方法：更新一条策略
     * @return 以ServiceVO封装的策略
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_STRATEGY_REQUEST_MAPPING, method = RequestMethod.POST, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    public String updateStrategy(@RequestBody StrategyVO strategyVO)
    {
        logger.debug(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "updateStrategy");
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(strategyVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "strategyVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            if(strategyService == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyService");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            return JSON.toJSONString(strategyService.updateStrategy(strategyVO));
        }
        catch (IllegalStateException illegalStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_STATE_EXCEPTION);
        }
        catch (JSONException jSONException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(jSONException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_JSON_EXCEPTION);
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        return JSON.toJSONString(serviceVO);
    }

    /**
     * 方法：删除一条策略
     * @return 以ServiceVO封装的策略
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_STRATEGY_REQUEST_MAPPING, method = RequestMethod.DELETE, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    public String deleteStrategy(@RequestBody StrategyVO strategyVO)
    {
        logger.debug(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "deleteStrategy");
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(strategyVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "strategyVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            if(strategyService == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyService");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            return JSON.toJSONString(strategyService.deleteStrategy(strategyVO));
        }
        catch (IllegalStateException illegalStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_STATE_EXCEPTION);
        }
        catch (JSONException jSONException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(jSONException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_JSON_EXCEPTION);
        }
        catch (Exception exception)
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
