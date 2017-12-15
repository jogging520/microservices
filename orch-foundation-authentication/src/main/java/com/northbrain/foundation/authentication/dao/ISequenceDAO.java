package com.northbrain.foundation.authentication.dao;

import org.apache.log4j.Logger;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.basic.ServiceVO;

/**
 * 类名：序列号DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@Component(value="sequenceDAO")
@FeignClient(name = Constants.BUSINESS_COMMON_SEQUENCE_ATOM_MICROSERVICE, fallback = ISequenceDAO.HystrixSequenceDAO.class)
public interface ISequenceDAO
{
    /**
     * 方法：通过原子服务获取在用的序列号
     * @return 全局唯一的序列号
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_GLOBAL_SEQUENCE_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readAtomNextGlobalValue() throws Exception;

    /**
     * 类名：序列号DAO接口的熔断器实现类
     * 用途：用于Hystrix熔断时fallback调用
     */
    @Component(value="hystrixSequenceDAO")
    class HystrixSequenceDAO implements ISequenceDAO
    {
        private static Logger logger = Logger.getLogger(HystrixSequenceDAO.class);

        /**
         * 方法：Hystrix熔断时的fallback调用
         * @return 在用的课程列表
         */
        @Override
        public String readAtomNextGlobalValue() throws Exception
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readAtomNextGlobalValue");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }
    }
}
