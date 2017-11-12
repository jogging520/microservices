package com.northbrain.relation.operationrecord.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 操作记录插入Exception
 * @author Jiakun
 * @version 1.0
 */
public class OperationRecordInsertException extends Exception
{
    private static final long serialVersionUID = 0L;

    public OperationRecordInsertException(Errors exception)
    {
        super(exception + "");
    }
}
