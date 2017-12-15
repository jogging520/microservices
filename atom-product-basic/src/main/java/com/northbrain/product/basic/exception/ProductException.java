package com.northbrain.product.basic.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 产品插入或更新Exception
 * @author Jiakun
 * @version 1.0
 */
public class ProductException extends Exception
{
    private static final long serialVersionUID = 0L;

    public ProductException(Errors exception)
    {
        super(exception + "");
    }
}
