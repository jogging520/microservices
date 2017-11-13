package com.northbrain.list.course.dao;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.vo.OperationRecordVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类名：产品域操作记录DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@FeignClient(name = Constants.BUSINESS_RELATION_OPERATION_RECORD_ATOM_MICROSERVICE)
public interface IOperationRecordDAO
{
    /**
     * 方法：通过原子服务新增一条产品域操作记录
     * @param operationRecordVO 操作记录值对象
     * @return 是否增加成功
     */
    @PostMapping(value= Constants.URI_ATOM_PRODUCT_POST_OPERATION_RECORD, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String createAtomOperationRecord(@RequestBody OperationRecordVO operationRecordVO) throws Exception;
}
