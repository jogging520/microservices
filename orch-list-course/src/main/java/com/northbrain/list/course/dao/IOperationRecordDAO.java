package com.northbrain.list.course.dao;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.vo.OperationRecordVO;
import com.northbrain.list.course.dao.hystrix.HystrixOperationRecord;

/**
 * 类名：产品域操作记录DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@FeignClient(name = Constants.BUSINESS_RELATION_OPERATION_RECORD_ATOM_MICROSERVICE, fallback = HystrixOperationRecord.class)
@RequestMapping(Constants.URI_ATOM_RELATION_OPERATION_RECORD_REQUEST_MAPPING)
public interface IOperationRecordDAO
{
    /**
     * 方法：通过原子服务新增一条产品域操作记录
     * @param operationRecordVO 操作记录值对象
     * @return 是否增加成功
     */
    @RequestMapping(method = RequestMethod.POST, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String createAtomOperationRecord(@RequestBody OperationRecordVO operationRecordVO) throws Exception;
}
