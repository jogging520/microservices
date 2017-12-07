package com.northbrain.common.security.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.common.security.service.ISecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类名：安全控制层类（原子服务）
 * 用途：解析http servlet，调用service层服务，返回给服务编排层应答数据。
 */
@RestController
public class SecurityController
{
    private static Logger logger = Logger.getLogger(SecurityController.class);
    private final ISecurityService securityService;

    @Autowired
    public SecurityController(ISecurityService securityService)
    {
        this.securityService = securityService;
    }

    /**
     * 方法：获取特定的权限实体
     * @param privilegeId 权限编号
     * @return 权限实体的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_SECURITY_PRIVILEGE_SPECIFIED_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    public String readPrivilege(@PathVariable("privilegeId")int privilegeId)
    {
        logger.debug(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "readPrivilege");
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(privilegeId <= 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "privilegeId:" + privilegeId);
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            if(securityService == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityService");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            return JSON.toJSONString(securityService.readPrivilege(privilegeId));
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
     * 方法：获取特定的权限实体列表
     * @param roleId 权限编号
     * @return 权限实体的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_SECURITY_ACCESS_CONTROL_SPECIFIED_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    public String readAccessControlsByRole(@PathVariable("roleId")int roleId)
    {
        logger.debug(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "readAccessControl");
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(roleId <= 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "roleId:" + roleId);
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            if(securityService == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityService");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            return JSON.toJSONString(securityService.readAccessControlsByRole(roleId));
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
