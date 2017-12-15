package com.northbrain.relation.operationrecord.dto;

import java.util.List;

import com.northbrain.base.common.model.vo.atom.OperationRecordVO;
import com.northbrain.relation.operationrecord.model.po.OperationRecordDetailPO;
import com.northbrain.relation.operationrecord.model.po.OperationRecordPO;

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
     * 方法：将操作记录PO转换成VO
     * @param operationRecordPO 操作记录持久化对象
     * @param operationRecordDetailPOS 操作记录详细信息持久化对象
     * @return 操作记录值对象
     * @throws Exception 异常
     */
    OperationRecordVO convertToOperationRecordVO(OperationRecordPO operationRecordPO, List<OperationRecordDetailPO> operationRecordDetailPOS) throws Exception;

    /**
     * 方法：将操作记录VO转换成操作记录VO
     * @return 操作记录VO
     * @throws Exception 异常
     */
    OperationRecordPO convertToOperationRecordPO(OperationRecordVO operationRecordVO) throws Exception;

    /**
     * 方法：将操作记录VO转换成操作记录详细信息VO列表
     * @return 操作记录VO
     * @throws Exception 异常
     */
    List<OperationRecordDetailPO> convertToOperationRecordDetailPOS(OperationRecordVO operationRecordVO) throws Exception;
}
