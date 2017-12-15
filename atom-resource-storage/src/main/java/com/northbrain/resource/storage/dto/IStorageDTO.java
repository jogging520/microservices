package com.northbrain.resource.storage.dto;

import com.northbrain.base.common.model.vo.atom.StorageVO;
import com.northbrain.resource.storage.model.po.StorageDetailHisPO;
import com.northbrain.resource.storage.model.po.StorageDetailPO;
import com.northbrain.resource.storage.model.po.StorageHisPO;
import com.northbrain.resource.storage.model.po.StoragePO;

import java.util.List;

/**
 * 类名：存储数据传输对象接口
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IStorageDTO
{
    /**
     * 方法：将存储数据持久化对象StoragePO转换成值对象StorageVO
     * @param storagePO 存储数据持久化对象
     * @param storageDetailPOS 存储数据明细持久化对象列表
     * @return StorageVO 存储数据值对象
     * @throws Exception 异常
     */
    StorageVO convertToStorageVO(StoragePO storagePO, List<StorageDetailPO> storageDetailPOS) throws Exception;

    /**
     * 方法：将存储数据传值对象StorageVO转换成持久化对象StoragePO
     * @param storageVO 存储数据VO值对象
     * @return StoragePO 存储数据持久化对象
     * @throws Exception 异常
     */
    StoragePO convertToStoragePO(StorageVO storageVO) throws Exception;

    /**
     * 方法：将存储数据值对象转换成存储数据明细持久化对象列表
     * @param storageVO 存储数据值对象
     * @return 存储数据明细持久化对象列表
     * @throws Exception 异常
     */
    List<StorageDetailPO> convertToStorageDetailPOS(StorageVO storageVO) throws Exception;

    /**
     * 方法：将存储数据持久化对象转换成存储数据历史持久化对象
     * @param recordId 操作记录编号
     * @param operateType 操作类型
     * @param storagePO 存储数据持久化对象
     * @return 存储数据历史持久化对象
     * @throws Exception 异常
     */
    StorageHisPO convertToStorageHisPO(Integer recordId, String operateType, StoragePO storagePO) throws Exception;

    /**
     * 方法：将存储数据明细持久化对象转换成存储数据明细历史持久化对象
     * @param recordId 操作记录编号
     * @param operateType 操作类型
     * @param storageDetailPO 存储数据明细持久化对象
     * @return 存储数据明细历史持久化对象
     * @throws Exception 异常
     */
    StorageDetailHisPO convertToStorageDetailHisPO(Integer recordId, String operateType, StorageDetailPO storageDetailPO) throws Exception;
}
