package com.northbrain.resource.storage.dto;

import com.northbrain.base.common.model.vo.CourseVO;
import com.northbrain.base.common.model.vo.StorageVO;
import com.northbrain.resource.storage.model.po.StoragePO;

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
     * 方法：将持久化对象StoragePO转换成值对象StorageVO
     * @param storagePO 持久化对象
     * @return CourseVO值对象
     */
    StorageVO convertToCourseVO(StoragePO storagePO) throws Exception;

    /**
     * 方法：将值对象StorageVO转换成持久化对象StoragePO
     * @param storageVO VO值对象
     * @return StoragePO持久化对象
     */
    StoragePO convertToCoursePO(StorageVO storageVO) throws Exception;
}
