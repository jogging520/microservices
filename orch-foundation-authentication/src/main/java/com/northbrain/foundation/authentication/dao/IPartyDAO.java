package com.northbrain.foundation.authentication.dao;

import org.apache.log4j.Logger;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.atom.OrganizationVO;
import com.northbrain.base.common.model.vo.atom.PartyVO;
import com.northbrain.base.common.model.vo.atom.RoleVO;
import com.northbrain.base.common.model.vo.atom.SubjectionVO;
import com.northbrain.base.common.model.vo.basic.ServiceVO;

/**
 * 类名：参与者DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@Component(value="partyDAO")
@FeignClient(name = Constants.BUSINESS_PARTY_BASIC_ATOM_MICROSERVICE, fallback = IPartyDAO.HystrixParty.class)
public interface IPartyDAO
{
    /**
     * 方法：根据名称获取角色信息列表
     * @param name 角色
     * @return 角色实体列表的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_PARTY_ROLE_SPECIFIED_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readAtomRolesByName(@PathVariable("name")String name);

    /**
     * 方法：新增一条角色
     * @param  roleVO 角色值对象
     * @return 操作角色实体是否成功的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_PARTY_ROLE_REQUEST_MAPPING, method = RequestMethod.PUT, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String createAtomRole(@RequestBody RoleVO roleVO);

    /**
     * 方法：新增一条角色
     * @param  roleVO 角色值对象
     * @return 操作角色实体是否成功的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_PARTY_ROLE_REQUEST_MAPPING, method = RequestMethod.POST, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String updateAtomRole(@RequestBody RoleVO roleVO);

    /**
     * 方法：删除一条角色
     * @param  roleVO 角色值对象
     * @return 操作角色实体是否成功的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_PARTY_ROLE_REQUEST_MAPPING, method = RequestMethod.DELETE, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String deleteAtomRole(@RequestBody RoleVO roleVO);

    /**
     * 方法：新增一条参与者
     * @param  partyVO 参与者值对象
     * @return 操作参与者实体是否成功的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_PARTY_BASIC_REQUEST_MAPPING, method = RequestMethod.PUT, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String createAtomParty(@RequestBody PartyVO partyVO);

    /**
     * 方法：新增一条参与者
     * @param  partyVO 参与者值对象
     * @return 操作参与者实体是否成功的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_PARTY_BASIC_REQUEST_MAPPING, method = RequestMethod.POST, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String updateAtomParty(@RequestBody PartyVO partyVO);

    /**
     * 方法：删除一条参与者
     * @param  partyVO 参与者值对象
     * @return 操作参与者实体是否成功的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_PARTY_BASIC_REQUEST_MAPPING, method = RequestMethod.DELETE, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String deleteAtomParty(@RequestBody PartyVO partyVO);

    /**
     * 方法：新增一条组织架构
     * @param  organizationVO 组织架构值对象
     * @return 操作组织架构实体是否成功的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_PARTY_ORGANIZATION_REQUEST_MAPPING, method = RequestMethod.PUT, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String createAtomOrganization(@RequestBody OrganizationVO organizationVO);

    /**
     * 方法：更新一条组织架构
     * @param  organizationVO 组织架构值对象
     * @return 操作组织架构实体是否成功的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_PARTY_ORGANIZATION_REQUEST_MAPPING, method = RequestMethod.POST, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String updateAtomOrganization(@RequestBody OrganizationVO organizationVO);

    /**
     * 方法：删除一条组织架构
     * @param  organizationVO 组织架构值对象
     * @return 操作组织架构实体是否成功的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_PARTY_ORGANIZATION_REQUEST_MAPPING, method = RequestMethod.DELETE, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String deleteAtomOrganization(@RequestBody OrganizationVO organizationVO);

    /**
     * 方法：新增一条隶属关系
     * @param  subjectionVO 隶属关系值对象
     * @return 操作隶属关系实体是否成功的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_PARTY_SUBJECTION_REQUEST_MAPPING, method = RequestMethod.PUT, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String createAtomSubjection(@RequestBody SubjectionVO subjectionVO);

    /**
     * 方法：新增一条隶属关系
     * @param  subjectionVO 隶属关系值对象
     * @return 操作隶属关系实体是否成功的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_PARTY_SUBJECTION_REQUEST_MAPPING, method = RequestMethod.POST, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String updateAtomSubjection(@RequestBody SubjectionVO subjectionVO);

    /**
     * 方法：删除一条隶属关系
     * @param  subjectionVO 隶属关系值对象
     * @return 操作隶属关系实体是否成功的JSON串
     */
    @RequestMapping(value = Constants.URI_ATOM_PARTY_SUBJECTION_REQUEST_MAPPING, method = RequestMethod.DELETE, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String deleteAtomSubjection(@RequestBody SubjectionVO subjectionVO);

    /**
     * 类名：参与者DAO接口的熔断器实现类
     * 用途：用于Hystrix熔断时fallback调用
     */
    @Component(value="hystrixParty")
    class HystrixParty implements IPartyDAO
    {
        private static Logger logger = Logger.getLogger(IPartyDAO.HystrixParty.class);
        /**
         * 方法：根据名称获取角色信息列表
         *
         * @param name 角色
         * @return 角色实体列表的JSON串
         */
        @Override
        public String readAtomRolesByName(String name)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readAtomRolesByName");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：新增一条角色
         *
         * @param roleVO 角色值对象
         * @return 操作角色实体是否成功的JSON串
         */
        @Override
        public String createAtomRole(RoleVO roleVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "createAtomRole");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：新增一条角色
         *
         * @param roleVO 角色值对象
         * @return 操作角色实体是否成功的JSON串
         */
        @Override
        public String updateAtomRole(RoleVO roleVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "updateAtomRole");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：删除一条角色
         *
         * @param roleVO 角色值对象
         * @return 操作角色实体是否成功的JSON串
         */
        @Override
        public String deleteAtomRole(RoleVO roleVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "deleteAtomRole");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：新增一条参与者
         *
         * @param partyVO 参与者值对象
         * @return 操作参与者实体是否成功的JSON串
         */
        @Override
        public String createAtomParty(PartyVO partyVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "createAtomParty");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：新增一条参与者
         *
         * @param partyVO 参与者值对象
         * @return 操作参与者实体是否成功的JSON串
         */
        @Override
        public String updateAtomParty(PartyVO partyVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "updateAtomParty");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：删除一条参与者
         *
         * @param partyVO 参与者值对象
         * @return 操作参与者实体是否成功的JSON串
         */
        @Override
        public String deleteAtomParty(PartyVO partyVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "deleteAtomParty");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：新增一条组织架构
         *
         * @param organizationVO 组织架构值对象
         * @return 操作组织架构实体是否成功的JSON串
         */
        @Override
        public String createAtomOrganization(OrganizationVO organizationVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "createAtomOrganization");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：更新一条组织架构
         *
         * @param organizationVO 组织架构值对象
         * @return 操作组织架构实体是否成功的JSON串
         */
        @Override
        public String updateAtomOrganization(OrganizationVO organizationVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "updateAtomOrganization");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：删除一条组织架构
         *
         * @param organizationVO 组织架构值对象
         * @return 操作组织架构实体是否成功的JSON串
         */
        @Override
        public String deleteAtomOrganization(OrganizationVO organizationVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "deleteAtomOrganization");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：新增一条隶属关系
         *
         * @param subjectionVO 隶属关系值对象
         * @return 操作隶属关系实体是否成功的JSON串
         */
        @Override
        public String createAtomSubjection(SubjectionVO subjectionVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "createAtomSubjection");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：新增一条隶属关系
         *
         * @param subjectionVO 隶属关系值对象
         * @return 操作隶属关系实体是否成功的JSON串
         */
        @Override
        public String updateAtomSubjection(SubjectionVO subjectionVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "updateAtomSubjection");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：删除一条隶属关系
         *
         * @param subjectionVO 隶属关系值对象
         * @return 操作隶属关系实体是否成功的JSON串
         */
        @Override
        public String deleteAtomSubjection(SubjectionVO subjectionVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "deleteAtomSubjection");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }
    }
}
