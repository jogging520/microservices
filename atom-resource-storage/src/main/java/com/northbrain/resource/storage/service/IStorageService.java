package com.northbrain.resource.storage.service;

import com.northbrain.base.common.model.vo.ServiceVO;

/**
 * 类名：存储服务接口
 * 用途：封装存储信息等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 * @version 1.0
 */
public interface IStorageService
{
    /**
     * 方法：获取特定的存储信息
     * @param storageId 存储编号
     * @return ServiceVO封装类
     */
    ServiceVO readStorage(int storageId);
}
