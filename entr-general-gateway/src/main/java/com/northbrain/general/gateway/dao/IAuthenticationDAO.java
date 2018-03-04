package com.northbrain.general.gateway.dao;

import org.apache.log4j.Logger;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.basic.ServiceVO;
import com.northbrain.base.common.model.vo.orch.OrchAccessControlVO;

/**
 * 类名：鉴权DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@Component(value="authenticationDAO")
@FeignClient(name = Constants.BUSINESS_FOUNDATION_AUTHENTICATION_ORCH_MICROSERVICE, fallback = IAuthenticationDAO.HystrixAuthentication.class)
public interface IAuthenticationDAO
{
    /**
     * 方法：访问控制
     * @param orchAccessControlVO 编排层访问控制值对象
     * @return 是否允许访问
     */
    @RequestMapping(value = Constants.URI_ORCH_FOUNDATION_AUTHENTICATION_ACCESS_CONTROL_REQUEST_MAPPING, method = RequestMethod.PUT, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String readOrchAccessControl(@RequestBody OrchAccessControlVO orchAccessControlVO);

    /**
     * 类名：鉴权DAO接口的熔断器实现类
     * 用途：用于Hystrix熔断时fallback调用
     */
    @Component(value="hystrixAuthentication")
    class HystrixAuthentication implements IAuthenticationDAO
    {
        private static Logger logger = Logger.getLogger(IAuthenticationDAO.HystrixAuthentication.class);

        /**
         * 方法：访问控制
         *
         * @param orchAccessControlVO 编排层访问控制值对象
         * @return 是否允许访问
         */
        @Override
        public String readOrchAccessControl(OrchAccessControlVO orchAccessControlVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readOrchAccessControl");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }
    }
}