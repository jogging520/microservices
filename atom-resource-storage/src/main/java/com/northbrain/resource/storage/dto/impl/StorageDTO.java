package com.northbrain.resource.storage.dto.impl;

import com.northbrain.base.common.model.bo.Hints;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.StorageVO;
import com.northbrain.resource.storage.dto.IStorageDTO;
import com.northbrain.resource.storage.model.po.StoragePO;

/**
 * 类名：存储数据传输对象接口的实现类
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class StorageDTO implements IStorageDTO
{
    private static Logger logger = Logger.getLogger(StorageDTO.class);

    /**
     * 方法：将持久化对象StoragePO转换成值对象StorageVO
     *
     * @param storagePO 持久化对象
     * @return CourseVO值对象
     */
    @Override
    public StorageVO convertToCourseVO(StoragePO storagePO) throws Exception
    {
        if(storagePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "storagePO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        StorageVO storageVO = new StorageVO();

        storageVO.setStorageId(storagePO.getStorageId());
        storageVO.setCategory(storagePO.getCategory());
        storageVO.setType(storagePO.getType());
        storageVO.setUri(storagePO.getUri());
        storageVO.setStatus(storagePO.getStatus());
        storageVO.setCreateTime(storagePO.getCreateTime());
        storageVO.setStatusTime(storagePO.getStatusTime());
        storageVO.setDesciption(storagePO.getDesciption());

        return storageVO;
    }

    /**
     * 方法：将值对象StorageVO转换成持久化对象StoragePO
     *
     * @param storageVO VO值对象
     * @return StoragePO持久化对象
     */
    @Override
    public StoragePO convertToCoursePO(StorageVO storageVO) throws Exception
    {
        if(storageVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "storageVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        StoragePO storagePO = new StoragePO();

        storagePO.setStorageId(storageVO.getStorageId());
        storagePO.setCategory(storageVO.getCategory());
        storagePO.setType(storageVO.getType());
        storagePO.setUri(storageVO.getUri());
        storagePO.setStatus(storageVO.getStatus());
        storagePO.setCreateTime(storageVO.getCreateTime());
        storagePO.setStatusTime(storageVO.getStatusTime());
        storagePO.setDesciption(storageVO.getDesciption());

        return storagePO;
    }
}
