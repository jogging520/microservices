package com.northbrain.list.course.domain;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.vo.OperationRecordVO;
import com.northbrain.base.common.model.vo.ServiceVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类名：产品域操作记录DOMAIN接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@FeignClient(name = Constants.BUSINESS_PRODUCT_OPERATION_ATOM_MICROSERVICE)
public interface IOperationRecordDomain
{
    /**
     * 方法：新增一条产品域操作记录
     * @param operationRecordVO 操作记录值对象
     * @return 是否增加成功
     */
    @PostMapping(value= Constants.URI_ATOM_PRODUCT_POST_OPERATION_RECORD, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    ServiceVO createOperationRecord(@RequestBody OperationRecordVO operationRecordVO);
}
