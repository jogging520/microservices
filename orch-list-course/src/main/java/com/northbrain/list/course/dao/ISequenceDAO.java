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
     * 方法：读取全局操作记录序列号
     * @return 以ServiceVO封装的全局操作记录序列号
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_OPERATION_RECORD_SEQUENCE_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readAtomOperationRecordId() throws Exception;

    /**
     * 方法：读取全局操作记录序列号
     * @return 以ServiceVO封装的全局操作记录序列号
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_REGISTRY_SEQUENCE_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readAtomRegistryId() throws Exception;

    /**
     * 方法：读取全局操作记录序列号
     * @return 以ServiceVO封装的全局操作记录序列号
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_PARTY_SEQUENCE_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readAtomPartyId() throws Exception;

    /**
     * 类名：序列号DAO接口的熔断器实现类
     * 用途：用于Hystrix熔断时fallback调用
     */
    @Component(value="hystrixSequenceDAO")
    class HystrixSequenceDAO implements ISequenceDAO
    {
        private static Logger logger = Logger.getLogger(HystrixSequenceDAO.class);

        /**
         * 方法：读取全局操作记录序列号
         *
         * @return 以ServiceVO封装的全局操作记录序列号
         */
        @Override
        public String readAtomOperationRecordId() throws Exception
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readAtomNextGlobalValue");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：读取全局操作记录序列号
         *
         * @return 以ServiceVO封装的全局操作记录序列号
         */
        @Override
        public String readAtomRegistryId() throws Exception
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readAtomNextGlobalValue");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：读取全局操作记录序列号
         *
         * @return 以ServiceVO封装的全局操作记录序列号
         */
        @Override
        public String readAtomPartyId() throws Exception
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readAtomNextGlobalValue");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }
    }
}
