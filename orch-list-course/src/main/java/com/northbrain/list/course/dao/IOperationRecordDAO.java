package com.northbrain.list.course.dao;

import com.alibaba.fastjson.JSON;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.ServiceVO;
import org.apache.log4j.Logger;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.vo.OperationRecordVO;

/**
 * 类名：产品域操作记录DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@FeignClient(name = Constants.BUSINESS_RELATION_OPERATION_RECORD_ATOM_MICROSERVICE, fallback = IOperationRecordDAO.HystrixOperationRecord.class)
public interface IOperationRecordDAO
{
    /**
     * 方法：通过原子服务新增一条产品域操作记录
     * @param operationRecordVO 操作记录值对象
     * @return 是否增加成功
     */
    @RequestMapping(value = Constants.URI_ATOM_RELATION_OPERATION_RECORD_REQUEST_MAPPING, method = RequestMethod.POST, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String createAtomOperationRecord(@RequestBody OperationRecordVO operationRecordVO) throws Exception;

    /**
     * 类名：操作记录DAO接口的熔断器实现类
     * 用途：用于Hystrix熔断时fallback调用
     */
    @Component
    class HystrixOperationRecord implements IOperationRecordDAO
    {
        private static Logger logger = Logger.getLogger(HystrixOperationRecord.class);

        /**
         * 方法：通过原子服务新增一条产品域操作记录
         *
         * @param operationRecordVO 操作记录值对象
         * @return 是否增加成功
         */
        @Override
        public String createAtomOperationRecord(OperationRecordVO operationRecordVO) throws Exception
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "createAtomOperationRecord");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }
    }
}
