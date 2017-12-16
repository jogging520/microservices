package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 属性枚举取值范围异常Exception
 * @author Jiakun
 * @version 1.0
 */
public class PropertyEnumerationException  extends Exception
{
    private static final long serialVersionUID = 0L;

    public PropertyEnumerationException(Errors exception)
    {
        super(exception + "");
    }
}