package com.northbrain.product.operation.domain.impl;

import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.OperationRecordVO;
import com.northbrain.product.operation.dao.OperationRecordPOMapper;
import com.northbrain.product.operation.domain.IOperationRecordDomain;
import com.northbrain.product.operation.dto.IOperationRecordDTO;
import com.northbrain.product.operation.model.po.OperationRecordPO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类名：操作记录域接口的实现类
 * 用途：实现各类实体增删改查的操作记录，用于溯源、统计及部分的一致性管理。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class OperationRecordDomain implements IOperationRecordDomain
{
    private static Logger logger = Logger.getLogger(OperationRecordDomain.class);
    private final OperationRecordPOMapper operationRecordPOMapper;
    private final IOperationRecordDTO operationRecordDTO;

    @Autowired
    public OperationRecordDomain(OperationRecordPOMapper operationRecordPOMapper, IOperationRecordDTO operationRecordDTO)
    {
        this.operationRecordPOMapper = operationRecordPOMapper;
        this.operationRecordDTO = operationRecordDTO;
    }

    /**
     * 方法：新增一条操作记录
     * @param operationRecordVO 操作记录
     * @return 是否增加成功
     */
    @Override
    public boolean createOperationRecord(OperationRecordVO operationRecordVO) throws Exception
    {
        if(operationRecordPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(operationRecordDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        OperationRecordPO operationRecordPO = operationRecordDTO.convertToOperationRecordPO(operationRecordVO);

        return operationRecordPOMapper.insert(operationRecordPO) > 0;
    }
}
