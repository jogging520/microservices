package com.northbrain.foundation.authentication.dao;

import com.northbrain.base.common.model.bo.Constants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类名：安全DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@Component(value="rolesecurityDAO")
@FeignClient(name = Constants.BUSINESS_COMMON_SECURITY_ATOM_MICROSERVICE, fallback = ISecurityDAO.HystrixSecurity.class)

public interface IRoleDAO
{
    @RequestMapping(value = Constants.URI_ATOM_PARTY_ROLE_SPECIFIED_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readRolesByName(@PathVariable("name")String name);
}
