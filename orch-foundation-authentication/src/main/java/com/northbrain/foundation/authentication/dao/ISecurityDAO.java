package com.northbrain.foundation.authentication.dao;

import org.apache.log4j.Logger;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.atom.LoginVO;
import com.northbrain.base.common.model.vo.atom.RegistryVO;
import com.northbrain.base.common.model.vo.basic.ServiceVO;

/**
 * 类名：安全DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@Component(value="securityDAO")
@FeignClient(name = Constants.BUSINESS_COMMON_SECURITY_ATOM_MICROSERVICE, fallback = ISecurityDAO.HystrixSecurity.class)
public interface ISecurityDAO
{
    /**
     * 方法：获取特定的权限实体
     * @param privilegeId 权限编号
     * @return 权限实体的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_SECURITY_PRIVILEGE_SPECIFIED_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readAtomPrivilege(@PathVariable("privilegeId")int privilegeId);

    /**
     * 方法：获取特定的权限实体列表
     * @param roleId 权限编号
     * @return 权限实体的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_SECURITY_ACCESS_CONTROL_SPECIFIED_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readAtomAccessControlsByRole(@PathVariable("roleId")int roleId);

    /**
     * 方法：获取特定的参与者登录信息列表
     * @param partyId 参与者编号
     * @return 参与者列表的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_SECURITY_LOGIN_SPECIFIED_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readAtomLoginsByParty(@PathVariable("partyId")int partyId);

    /**
     * 方法：新增一条注册信息（注册）
     * @param registryVO 注册信息值对象
     * @return 参与者列表的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_SECURITY_REGISTRY_REQUEST_MAPPING, method = RequestMethod.PUT, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String createAtomRegistry(@RequestBody RegistryVO registryVO);

    /**
     * 方法：创建一条登录信息（登录）
     * @param loginVO 登录信息值对象
     * @return 参与者列表的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_SECURITY_LOGIN_REQUEST_MAPPING, method = RequestMethod.PUT, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String createAtomLogin(@RequestBody LoginVO loginVO);

    /**
     * 方法：更新一条登录信息（登出）
     * @param loginVO 登录信息值对象
     * @return 参与者列表的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_SECURITY_LOGIN_REQUEST_MAPPING, method = RequestMethod.POST, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String updateAtomLogin(@RequestBody LoginVO loginVO);

    /**
     * 类名：安全DAO接口的熔断器实现类
     * 用途：用于Hystrix熔断时fallback调用
     */
    @Component(value="hystrixSecurity")
    class HystrixSecurity implements ISecurityDAO
    {
        private static Logger logger = Logger.getLogger(ISecurityDAO.HystrixSecurity.class);
        /**
         * 方法：获取特定的权限实体
         *
         * @param privilegeId 权限编号
         * @return 权限实体的JSON串
         */
        @Override
        public String readAtomPrivilege(int privilegeId)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readAtomPrivilege");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：获取特定的权限实体列表
         *
         * @param roleId 权限编号
         * @return 权限实体的JSON串
         */
        @Override
        public String readAtomAccessControlsByRole(int roleId)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readAtomAccessControlsByRole");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：获取特定的参与者登录信息列表
         *
         * @param partyId 参与者编号
         * @return 参与者列表的JSON串
         */
        @Override
        public String readAtomLoginsByParty(int partyId)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readAtomLoginsByParty");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：新增一条注册信息（注册）
         *
         * @param registryVO 注册信息值对象
         * @return 参与者列表的JSON串
         */
        @Override
        public String createAtomRegistry(RegistryVO registryVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "createAtomRegistry");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：创建一条登录信息（登录）
         *
         * @param loginVO 登录信息值对象
         * @return 参与者列表的JSON串
         */
        @Override
        public String createAtomLogin(LoginVO loginVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "createAtomLogin");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：更新一条登录信息（登出）
         *
         * @param loginVO 登录信息值对象
         * @return 参与者列表的JSON串
         */
        @Override
        public String updateAtomLogin(LoginVO loginVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "updateAtomLogin");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }
    }
}
