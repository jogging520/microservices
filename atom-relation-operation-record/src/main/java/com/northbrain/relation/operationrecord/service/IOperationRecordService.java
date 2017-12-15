package com.northbrain.relation.operationrecord.service;

import com.northbrain.base.common.model.vo.atom.OperationRecordVO;
import com.northbrain.base.common.model.vo.basic.ServiceVO;

/**
 * 类名：操作记录服务接口
 * 用途：封装操作记录等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
public interface IOperationRecordService
{
    /**
     * 方法：创建一条操作记录
     * @param operationRecordVO 操作记录值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO createOperationRecord(OperationRecordVO operationRecordVO);

    /**
     * 方法：更新操作记录（最后一次操作完成之后）
     * @param operationRecordVO 操作记录值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO updateOperationRecord(OperationRecordVO operationRecordVO);
}
