package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * ZooKeeper会话异常Exception
 * @author Jiakun
 * @version 1.0
 */
public class ZooKeeperSessionException extends Exception
{
    private static final long serialVersionUID = 0L;

    public ZooKeeperSessionException(Errors exception)
    {
        super(exception + "");
    }
}
