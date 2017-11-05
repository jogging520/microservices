package com.northbrain.product.operation.service;

import com.northbrain.base.common.model.vo.OperationRecordVO;
import com.northbrain.base.common.model.vo.ServiceVO;

/**
 * 类名：操作记录服务接口
 * 用途：封装操作记录的等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 * @version 1.0
 */
public interface IOperationRecordService
{
    /**
     * 创建操作记录
     * @return ServiceVO封装类
     */
    ServiceVO createOperationRecord(OperationRecordVO operationRecordVO);
}
