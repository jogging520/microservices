package com.northbrain.resource.storage.domain;

import com.northbrain.base.common.model.vo.StorageVO;

/**
 * 类名：存储域接口
 * 用途：实现各类资源的存储，记录详细的存储信息。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IStorageDomain
{
    /**
     * 方法：获取特定的资源信息
     * @param storageId 资源存储编号
     * @return 课程
     */
    StorageVO readStorage(int storageId) throws Exception;
}
