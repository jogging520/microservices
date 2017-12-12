package com.northbrain.resource.storage.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 存储资源插入或更新Exception
 * @author Jiakun
 * @version 1.0
 */
public class StorageException extends Exception
{
    private static final long serialVersionUID = 0L;

    public StorageException(Errors exception)
    {
        super(exception + "");
    }
}
