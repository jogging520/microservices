package com.northbrain.foundation.authentication.dao;

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
import com.northbrain.base.common.model.vo.atom.FlowControlVO;
import com.northbrain.base.common.model.vo.basic.ServiceVO;

/**
 * 类名：流量控制DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@Component(value="flowControlDAO")
@FeignClient(name = Constants.BUSINESS_COMMON_FLOW_CONTROL_ATOM_MICROSERVICE, fallback = IFlowControlDAO.HystrixFlowControl.class)
public interface IFlowControlDAO
{
    /**
     * 方法：获取特定的流量管控权限
     * @param flowControlVO 流控值对象
     * @return 是否进行流控
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_FLOW_CONTROL_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String readAtomFlowControl(@RequestBody FlowControlVO flowControlVO);

    /**
     * 类名：流量管控DAO接口的熔断器实现类
     * 用途：用于Hystrix熔断时fallback调用
     */
    @Component(value="hystrixFlowControl")
    class HystrixFlowControl implements IFlowControlDAO
    {
        private static Logger logger = Logger.getLogger(IFlowControlDAO.HystrixFlowControl.class);

        /**
         * 方法：获取特定的流量管控权限
         *
         * @param flowControlVO 流控值对象
         * @return 是否进行流控
         */
        @Override
        public String readAtomFlowControl(FlowControlVO flowControlVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readAtomFlowControl");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }
    }
}
