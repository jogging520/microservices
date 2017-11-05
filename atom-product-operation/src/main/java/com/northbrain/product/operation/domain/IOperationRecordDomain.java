package com.northbrain.product.operation.domain;

import com.northbrain.base.common.model.vo.OperationRecordVO;

/**
 * 类名：操作记录域接口
 * 用途：实现各类实体增删改查的操作记录，用于溯源、统计及部分的一致性管理。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IOperationRecordDomain
{
    /**
     * 方法：新增一条操作记录
     * @param operationRecordVO 操作记录
     * @return 是否增加成功
     */
    boolean createOperationRecord(OperationRecordVO operationRecordVO) throws Exception;
}
