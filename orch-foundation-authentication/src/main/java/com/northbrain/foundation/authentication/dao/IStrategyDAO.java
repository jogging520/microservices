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
import com.northbrain.base.common.model.vo.basic.ServiceVO;
import com.northbrain.base.common.model.vo.atom.StrategyVO;

/**
 * 类名：策略DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@Component(value="strategyDAO")
@FeignClient(name = Constants.BUSINESS_COMMON_STRATEGY_ATOM_MICROSERVICE, fallback = IStrategyDAO.HystrixStrategy.class)
public interface IStrategyDAO
{
    /**
     * 方法：新增一条策略
     * @return 以ServiceVO封装的策略
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_STRATEGY_REQUEST_MAPPING, method = RequestMethod.PUT, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String createAtomStrategy(@RequestBody StrategyVO strategyVO);

    /**
     * 方法：更新一条策略
     * @return 以ServiceVO封装的策略
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_STRATEGY_REQUEST_MAPPING, method = RequestMethod.POST, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String updateAtomStrategy(@RequestBody StrategyVO strategyVO);

    /**
     * 方法：删除一条策略
     * @return 以ServiceVO封装的策略
     */
    @RequestMapping(value = Constants.URI_ATOM_COMMON_STRATEGY_REQUEST_MAPPING, method = RequestMethod.DELETE, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String deleteAtomStrategy(@RequestBody StrategyVO strategyVO);

    /**
     * 类名：策略DAO接口的熔断器实现类
     * 用途：用于Hystrix熔断时fallback调用
     */
    @Component(value="hystrixStrategy")
    class HystrixStrategy implements IStrategyDAO
    {
        private static Logger logger = Logger.getLogger(IStrategyDAO.HystrixStrategy.class);

        /**
         * 方法：新增一条策略
         *
         * @param strategyVO
         * @return 以ServiceVO封装的策略
         */
        @Override
        public String createAtomStrategy(StrategyVO strategyVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "createAtomStrategy");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：更新一条策略
         *
         * @param strategyVO
         * @return 以ServiceVO封装的策略
         */
        @Override
        public String updateAtomStrategy(StrategyVO strategyVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "updateAtomStrategy");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：删除一条策略
         *
         * @param strategyVO
         * @return 以ServiceVO封装的策略
         */
        @Override
        public String deleteAtomStrategy(StrategyVO strategyVO)
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "deleteAtomStrategy");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }
    }
}
