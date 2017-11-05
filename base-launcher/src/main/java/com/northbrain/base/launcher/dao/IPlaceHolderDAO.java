package com.northbrain.base.launcher.dao;

import com.northbrain.base.launcher.model.po.PlaceHolderPO;

/**
 * 类名：占位符DAO接口
 * 用途：获取占位符，如数据库密码等。只读取，不写入。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IPlaceHolderDAO
{
    /**
     * 方法：获取占位符信息
     * @param placeHolderNamespace 占位符命名空间
     * @return 占位符信息
     */
    public PlaceHolderPO getPlaceholder(String placeHolderNamespace) throws Exception;
}

