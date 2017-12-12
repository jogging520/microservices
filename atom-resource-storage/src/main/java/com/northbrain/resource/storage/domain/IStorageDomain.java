package com.northbrain.resource.storage.domain;

import java.util.List;

import com.northbrain.base.common.model.vo.StorageVO;
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
     * @return 存储资源
     * @throws Exception 异常
     */
    StorageVO readStorage(int storageId) throws Exception;

    /**
     * 方法：获取一组指定资源ID的资源信息
     * @param storageIds 一组资源的ID集合
     * @return 一组指定资源ID的资源信息
     * @throws Exception 异常
     */
    List<StorageVO> readStorages(List<Integer> storageIds) throws Exception;

    /**
     * 方法：新建一条存储资源，根据StorageVO再转换成相应的PO
     * @param storageVO 存储资源值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    boolean createStorage(StorageVO storageVO) throws Exception;

    /**
     * 方法：更新一条存储资源，根据StorageVO再转换成相应的PO
     * @param storageVO 存储资源值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    boolean updateStorage(StorageVO storageVO) throws Exception;

    /**
     * 方法：删除一条存储资源，根据StorageVO再转换成相应的PO
     * @param storageVO 存储资源值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    boolean deleteStorage(StorageVO storageVO) throws Exception;
}
