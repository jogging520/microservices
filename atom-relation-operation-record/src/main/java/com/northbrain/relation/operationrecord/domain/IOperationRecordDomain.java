package com.northbrain.relation.operationrecord.domain;

import com.northbrain.base.common.model.vo.atom.OperationRecordVO;

/**
 * 类名：操作记录DOMAIN接口
 * 用途：实现操作记录的新增、更新，操作记录既是业务记录的流水，也是微服务架构的辅助实现手段。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IOperationRecordDomain
{
    /**
     * 方法：新建一条操作记录，根据OperationRecordVO再转换成相应的PO
     * @param operationRecordVO 操作记录值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    boolean createOperationRecord(OperationRecordVO operationRecordVO) throws Exception;

    /**
     * 方法：更新操作记录，根据OperationRecordVO再转换成相应的PO
     * @param operationRecordVO 操作记录值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    boolean updateOperationRecord(OperationRecordVO operationRecordVO) throws Exception;
}
