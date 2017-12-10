package com.northbrain.relation.operationrecord.domain.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.OperationRecordVO;
import com.northbrain.relation.operationrecord.dao.OperationRecordDetailPOMapper;
import com.northbrain.relation.operationrecord.dao.OperationRecordPOMapper;
import com.northbrain.relation.operationrecord.domain.IOperationRecordDomain;
import com.northbrain.relation.operationrecord.dto.IOperationRecordDTO;
import com.northbrain.relation.operationrecord.exception.OperationRecordException;
import com.northbrain.relation.operationrecord.model.po.OperationRecordDetailPO;
import com.northbrain.relation.operationrecord.model.po.OperationRecordPO;

/**
 * 类名：操作记录DOMAIN接口的实现类
 * 用途：实现操作记录的新增、更新，操作记录既是业务记录的流水，也是微服务架构的辅助实现手段。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class OperationRecordDomain implements IOperationRecordDomain
{
    private static Logger logger = Logger.getLogger(OperationRecordDomain.class);
    private final OperationRecordPOMapper operationRecordPOMapper;
    private final OperationRecordDetailPOMapper operationRecordDetailPOMapper;
    private final IOperationRecordDTO operationRecordDTO;

    @Autowired
    public OperationRecordDomain(OperationRecordPOMapper operationRecordPOMapper, OperationRecordDetailPOMapper operationRecordDetailPOMapper, IOperationRecordDTO operationRecordDTO)
    {
        this.operationRecordPOMapper = operationRecordPOMapper;
        this.operationRecordDetailPOMapper = operationRecordDetailPOMapper;
        this.operationRecordDTO = operationRecordDTO;
    }


    /**
     * 方法：新建一条操作记录，根据OperationRecordVO再转换成相应的PO
     * 如果已经存在，那么更新该操作记录及明细。
     * @param operationRecordVO 操作记录值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    @Override
    public boolean createOperationRecord(OperationRecordVO operationRecordVO) throws Exception
    {
        if(operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(operationRecordPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(operationRecordDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(operationRecordDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(operationRecordVO);

        OperationRecordPO operationRecordPO = operationRecordDTO.convertToOperationRecordPO(operationRecordVO);

        //对于操作记录本身，如果没有记录，那么插入一条记录，否则更新当前记录。
        if(operationRecordPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(operationRecordPOMapper.selectByPrimaryKey(operationRecordPO.getRecordId()) == null)
        {
            if(operationRecordPOMapper.insert(operationRecordPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_RELATION_OPERATION_RECORD_INSERT + String.valueOf(operationRecordPO.getRecordId()));
                throw new OperationRecordException(Errors.ERROR_BUSINESS_RELATION_OPERATION_RECORD_EXCEPTION);
            }
        }
        else
        {
            if(operationRecordPOMapper.updateByPrimaryKey(operationRecordPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_RELATION_OPERATION_RECORD_UPDATE+ String.valueOf(operationRecordPO.getRecordId()));
                throw new OperationRecordException(Errors.ERROR_BUSINESS_RELATION_OPERATION_RECORD_EXCEPTION);
            }
        }

        //对于操作记录明细，只有插入、没有更新。如果有其中一条记录插入失败，为保证业务的完整性，整个事务失败。
        if (operationRecordVO.getOperationRecordDetailVOS() != null && operationRecordVO.getOperationRecordDetailVOS().size() > 0)
        {
            List<OperationRecordDetailPO> operationRecordDetailPOs = operationRecordDTO.convertToOperationRecordDetailPOS(operationRecordVO);

            if (operationRecordDetailPOs == null || operationRecordDetailPOs.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDetailPOs");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for (OperationRecordDetailPO operationRecordDetailPO : operationRecordDetailPOs)
            {
                if(operationRecordDetailPOMapper.selectByPrimaryKey(operationRecordDetailPO.getRecordDetailId()) == null)
                {
                    if (operationRecordDetailPOMapper.insert(operationRecordDetailPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_RELATION_OPERATION_RECORD_INSERT + String.valueOf(operationRecordDetailPO.getRecordId()));
                        throw new OperationRecordException(Errors.ERROR_BUSINESS_RELATION_OPERATION_RECORD_EXCEPTION);
                    }
                }
                else
                {
                    if (operationRecordDetailPOMapper.updateByPrimaryKey(operationRecordDetailPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_RELATION_OPERATION_RECORD_INSERT + String.valueOf(operationRecordDetailPO.getRecordId()));
                        throw new OperationRecordException(Errors.ERROR_BUSINESS_RELATION_OPERATION_RECORD_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }

    /**
     * 方法：更新操作记录，根据OperationRecordVO再转换成相应的PO
     * 只更新record，不更新detail
     * @param operationRecordVO 操作记录值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public boolean updateOperationRecord(OperationRecordVO operationRecordVO) throws Exception
    {
        if(operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

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

        logger.debug(operationRecordVO);

        OperationRecordPO operationRecordPO = operationRecordDTO.convertToOperationRecordPO(operationRecordVO);

        //对于操作记录本身，如果没有记录，那么插入一条记录，否则更新当前记录。
        if(operationRecordPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(operationRecordPOMapper.selectByPrimaryKey(operationRecordPO.getRecordId()) == null)
        {
            throw new OperationRecordException(Errors.ERROR_BUSINESS_RELATION_OPERATION_RECORD_EXCEPTION);
        }
        else
        {
            if(operationRecordPOMapper.updateByPrimaryKey(operationRecordPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_RELATION_OPERATION_RECORD_UPDATE+ String.valueOf(operationRecordPO.getRecordId()));
                throw new OperationRecordException(Errors.ERROR_BUSINESS_RELATION_OPERATION_RECORD_EXCEPTION);
            }
        }

        return true;
    }
}
