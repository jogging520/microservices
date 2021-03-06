package com.northbrain.relation.operationrecord.dto.impl;

import java.util.ArrayList;
import java.util.List;

import com.northbrain.base.common.model.bo.Hints;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.atom.OperationRecordVO;
import com.northbrain.relation.operationrecord.dto.IOperationRecordDTO;
import com.northbrain.relation.operationrecord.model.po.OperationRecordDetailPO;
import com.northbrain.relation.operationrecord.model.po.OperationRecordPO;

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
    private static Logger logger = Logger.getLogger(OperationRecordDTO.class);

    /**
     * 方法：将操作记录PO转换成VO
     *
     * @param operationRecordPO        操作记录持久化对象
     * @param operationRecordDetailPOS 操作记录详细信息持久化对象
     * @return 操作记录值对象
     * @throws Exception 异常
     */
    @Override
    public OperationRecordVO convertToOperationRecordVO(OperationRecordPO operationRecordPO, List<OperationRecordDetailPO> operationRecordDetailPOS) throws Exception
    {
        if(operationRecordPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        OperationRecordVO operationRecordVO = new OperationRecordVO();
        operationRecordVO.setRecordId(operationRecordPO.getRecordId());
        operationRecordVO.setOperateType(operationRecordPO.getOperateType());
        operationRecordVO.setOperatorId(operationRecordPO.getOperatorId());
        operationRecordVO.setDomain(operationRecordPO.getDomain());
        operationRecordVO.setServiceName(operationRecordPO.getServiceName());
        operationRecordVO.setStatus(operationRecordPO.getStatus());
        operationRecordVO.setStartTime(operationRecordPO.getStartTime());
        operationRecordVO.setFinishTime(operationRecordPO.getFinishTime());
        operationRecordVO.setDescription(operationRecordPO.getDescription());

        if(operationRecordDetailPOS == null || operationRecordDetailPOS.size() == 0)
        {
            return operationRecordVO;
        }

        List<OperationRecordVO.OperationRecordDetailVO> operationRecordDetailVOS = new ArrayList<>();

        for(OperationRecordDetailPO operationRecordDetailPO: operationRecordDetailPOS)
        {
            OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO = new OperationRecordVO.OperationRecordDetailVO();

            operationRecordDetailVO.setRecordDetailId(operationRecordDetailPO.getRecordDetailId());
            operationRecordDetailVO.setRank(operationRecordDetailPO.getRank());
            operationRecordDetailVO.setOperateType(operationRecordDetailPO.getOperateType());
            operationRecordDetailVO.setDomain(operationRecordDetailPO.getDomain());
            operationRecordDetailVO.setServiceName(operationRecordDetailPO.getServiceName());
            operationRecordDetailVO.setStatus(operationRecordDetailPO.getStatus());
            operationRecordDetailVO.setStartTime(operationRecordDetailPO.getStartTime());
            operationRecordDetailVO.setFinishTime(operationRecordDetailPO.getFinishTime());
            operationRecordDetailVO.setDescription(operationRecordDetailPO.getDescription());

            operationRecordDetailVOS.add(operationRecordDetailVO);
        }

        operationRecordVO.setOperationRecordDetailVOS(operationRecordDetailVOS);

        return operationRecordVO;
    }

    /**
     * 方法：将操作记录VO转换成操作记录VO
     *
     * @return 操作记录VO
     * @throws Exception 异常
     */
    @Override
    public OperationRecordPO convertToOperationRecordPO(OperationRecordVO operationRecordVO) throws Exception
    {
        if(operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        OperationRecordPO operationRecordPO = new OperationRecordPO();
        operationRecordPO.setRecordId(operationRecordVO.getRecordId());
        operationRecordPO.setOperateType(operationRecordVO.getOperateType());
        operationRecordPO.setOperatorId(operationRecordVO.getOperatorId());
        operationRecordPO.setDomain(operationRecordVO.getDomain());
        operationRecordPO.setServiceName(operationRecordVO.getServiceName());
        operationRecordPO.setStatus(operationRecordVO.getStatus());
        operationRecordPO.setStartTime(operationRecordVO.getStartTime());
        operationRecordPO.setFinishTime(operationRecordVO.getFinishTime());
        operationRecordPO.setDescription(operationRecordVO.getDescription());

        return operationRecordPO;
    }

    /**
     * 方法：将操作记录VO转换成操作记录详细信息VO列表
     *
     * @return 操作记录VO
     * @throws Exception 异常
     */
    @Override
    public List<OperationRecordDetailPO> convertToOperationRecordDetailPOS(OperationRecordVO operationRecordVO) throws Exception
    {
        if(operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(operationRecordVO.getOperationRecordDetailVOS() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO.getOperationRecordDetailVOS()");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        List<OperationRecordDetailPO> operationRecordDetailPOS = new ArrayList<>();

        for(OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO : operationRecordVO.getOperationRecordDetailVOS())
        {

            OperationRecordDetailPO operationRecordDetailPO = new OperationRecordDetailPO();

            operationRecordDetailPO.setRecordDetailId(operationRecordDetailVO.getRecordDetailId());
            operationRecordDetailPO.setRecordId(operationRecordVO.getRecordId());
            operationRecordDetailPO.setRank(operationRecordDetailVO.getRank());
            operationRecordDetailPO.setOperateType(operationRecordDetailVO.getOperateType());
            operationRecordDetailPO.setDomain(operationRecordDetailVO.getDomain());
            operationRecordDetailPO.setServiceName(operationRecordDetailVO.getServiceName());
            operationRecordDetailPO.setStatus(operationRecordDetailVO.getStatus());
            operationRecordDetailPO.setStartTime(operationRecordDetailVO.getStartTime());
            operationRecordDetailPO.setFinishTime(operationRecordDetailVO.getFinishTime());
            operationRecordDetailPO.setDescription(operationRecordDetailVO.getDescription());

            operationRecordDetailPOS.add(operationRecordDetailPO);
        }

        return operationRecordDetailPOS;
    }
}
