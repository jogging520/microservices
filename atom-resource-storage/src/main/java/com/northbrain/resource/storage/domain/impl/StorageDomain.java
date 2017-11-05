package com.northbrain.resource.storage.domain.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.CollectionEmptyException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.StorageVO;
import com.northbrain.resource.storage.dao.StoragePOMapper;
import com.northbrain.resource.storage.domain.IStorageDomain;
import com.northbrain.resource.storage.dto.IStorageDTO;
import com.northbrain.resource.storage.model.po.StoragePO;

/**
 * 类名：存储域接口的实现类
 * 用途：实现各类资源的存储，记录详细的存储信息。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class StorageDomain implements IStorageDomain
{
    private static Logger logger = Logger.getLogger(StorageDomain.class);
    private final StoragePOMapper storagePOMapper;
    private final IStorageDTO storageDTO;

    @Autowired
    public StorageDomain(StoragePOMapper storagePOMapper, IStorageDTO storageDTO)
    {
        this.storagePOMapper = storagePOMapper;
        this.storageDTO = storageDTO;
    }

    /**
     * 方法：获取特定的资源信息
     *
     * @param storageId 资源存储编号
     * @return 课程
     */
    @Override
    public StorageVO readStorage(int storageId) throws Exception
    {
        if(storagePOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storagePOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(storageDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        StoragePO storagePO = storagePOMapper.selectByPrimaryKey(storageId);

        if(storagePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "storagePO");
            throw new CollectionEmptyException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return storageDTO.convertToCourseVO(storagePO);
    }
}
