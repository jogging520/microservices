package com.northbrain.product.operation.dto.impl;

import com.northbrain.base.common.model.vo.OperationRecordVO;
import com.northbrain.product.operation.dto.IOperationRecordDTO;
import com.northbrain.product.operation.model.po.OperationRecordPO;
import org.springframework.stereotype.Component;

/**
 * 类名：操作记录传输对象接口的实现类
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class OperationRecordDTO implements IOperationRecordDTO
{
    /**
     * 方法：将持久化对象OperationRecordPO转换成值对象OperationRecordVO
     * @param operationRecordPO 持久化对象
     * @return OperationRecordVO 值对象
     */
    @Override
    public OperationRecordVO convertToOperationRecordVO(OperationRecordPO operationRecordPO)
    {
        OperationRecordVO operationRecordVO = new OperationRecordVO();

        operationRecordVO.setRecordId(operationRecordPO.getRecordId());
        operationRecordVO.setOperateType(operationRecordPO.getOperateType());
        operationRecordVO.setOperatorId(operationRecordPO.getOperatorId());
        operationRecordVO.setEntity(operationRecordPO.getEntity());
        operationRecordVO.setStatus(operationRecordPO.getStatus());
        operationRecordVO.setStartTime(operationRecordPO.getStartTime());
        operationRecordVO.setFinishTime(operationRecordPO.getFinishTime());
        operationRecordVO.setDesciption(operationRecordPO.getDesciption());

        return operationRecordVO;
    }

    /**
     * 方法：将值对象OperationRecordVO转换成持久化对象OperationRecordPO
     * @param operationRecordVO 值对象
     * @return OperationRecordPO 持久化对象
     */
    @Override
    public OperationRecordPO convertToOperationRecordPO(OperationRecordVO operationRecordVO)
    {
        OperationRecordPO operationRecordPO = new OperationRecordPO();

        operationRecordPO.setRecordId(operationRecordVO.getRecordId());
        operationRecordPO.setOperateType(operationRecordVO.getOperateType());
        operationRecordPO.setOperatorId(operationRecordVO.getOperatorId());
        operationRecordPO.setEntity(operationRecordVO.getEntity());
        operationRecordPO.setStatus(operationRecordVO.getStatus());
        operationRecordPO.setStartTime(operationRecordVO.getStartTime());
        operationRecordPO.setFinishTime(operationRecordVO.getFinishTime());
        operationRecordPO.setDesciption(operationRecordVO.getDesciption());

        return operationRecordPO;
    }
}
