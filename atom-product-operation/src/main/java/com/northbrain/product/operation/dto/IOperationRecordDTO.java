package com.northbrain.product.operation.dto;

import com.northbrain.base.common.model.vo.OperationRecordVO;
import com.northbrain.product.operation.model.po.OperationRecordPO;

/**
 * 类名：操作记录传输对象接口
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IOperationRecordDTO
{
    /**
     * 方法：将持久化对象OperationRecordPO转换成值对象OperationRecordVO
     * @param operationRecordPO 持久化对象
     * @return OperationRecordVO 值对象
     */
    OperationRecordVO convertToOperationRecordVO(OperationRecordPO operationRecordPO);

    /**
     * 方法：将值对象OperationRecordVO转换成持久化对象OperationRecordPO
     * @param operationRecordVO 值对象
     * @return OperationRecordPO 持久化对象
     */
    OperationRecordPO convertToOperationRecordPO(OperationRecordVO operationRecordVO);
}
