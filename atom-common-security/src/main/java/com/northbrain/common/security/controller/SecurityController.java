package com.northbrain.common.security.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.atom.LoginVO;
import com.northbrain.base.common.model.vo.atom.RegistryVO;
import com.northbrain.base.common.model.vo.basic.ServiceVO;
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
     * 方法：获取特定的参与者登录信息列表
     * @param partyId 参与者编号
     * @return 参与者列表的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_SECURITY_LOGIN_SPECIFIED_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    public String readLoginsByParty(@PathVariable("partyId")int partyId)
    {
        logger.debug(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "readAccessControl");
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(partyId <= 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "roleId:" + partyId);
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            if(securityService == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityService");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            return JSON.toJSONString(securityService.readLoginsByParty(partyId));
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
     * 方法：新增一条注册信息（注册）
     * @param registryVO 注册信息值对象
     * @return 参与者列表的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_SECURITY_REGISTRY_REQUEST_MAPPING, method = RequestMethod.PUT, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    public String createRegistry(@RequestBody RegistryVO registryVO)
    {
        logger.debug(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "createRegistry");
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(registryVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "registryVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            if(securityService == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityService");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            return JSON.toJSONString(securityService.createRegistry(registryVO));
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
     * 方法：创建一条登录信息（登录）
     * @param loginVO 登录信息值对象
     * @return 参与者列表的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_SECURITY_LOGIN_REQUEST_MAPPING, method = RequestMethod.PUT, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    public String createLogin(@RequestBody LoginVO loginVO)
    {
        logger.debug(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "createLogin");
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(loginVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "loginVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            if(securityService == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityService");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            return JSON.toJSONString(securityService.createLogin(loginVO));
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
     * 方法：更新一条登录信息（登出）
     * @param loginVO 登录信息值对象
     * @return 参与者列表的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_SECURITY_LOGIN_REQUEST_MAPPING, method = RequestMethod.POST, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    public String updateLogin(@RequestBody LoginVO loginVO)
    {
        logger.debug(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "updateLogin");
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(loginVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "loginVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            if(securityService == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityService");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return JSON.toJSONString(serviceVO);
            }

            return JSON.toJSONString(securityService.updateLogin(loginVO));
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
