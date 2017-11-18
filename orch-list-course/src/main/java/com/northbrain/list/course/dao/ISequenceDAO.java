package com.northbrain.list.course.dao;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.list.course.dao.hystrix.HystrixSequenceDAO;

/**
 * 类名：序列号DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@FeignClient(name = Constants.BUSINESS_COMMON_SEQUENCE_ATOM_MICROSERVICE, fallback = HystrixSequenceDAO.class)
@RequestMapping(Constants.URI_ATOM_COMMON_GLOBAL_SEQUENCE_REQUEST_MAPPING)
public interface ISequenceDAO
{
    /**
     * 方法：通过原子服务获取在用的序列号
     * @return 全局唯一的序列号
     */
    @RequestMapping(method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readAtomNextGlobalValue() throws Exception;
}
