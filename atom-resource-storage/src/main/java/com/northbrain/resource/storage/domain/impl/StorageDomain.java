package com.northbrain.resource.storage.domain.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.CollectionEmptyException;
import com.northbrain.base.common.exception.NumberScopeException;
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
     * @throws Exception 异常
     */
    @Override
    public StorageVO readStorage(int storageId) throws Exception
    {
        if(storageId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "storageId:" + storageId);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

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

    /**
     * 方法：获取一组指定资源ID的资源信息
     *
     * @param storageIds 一组资源的ID集合
     * @return 一组指定资源ID的资源信息
     * @throws Exception 异常
     */
    @Override
    public List<StorageVO> readStorages(List<Integer> storageIds) throws Exception
    {
        if(storageIds == null || storageIds.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "storageIds");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

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

        List<StorageVO> storageVOS = new ArrayList<>();

        for(Integer storageId: storageIds)
        {
            if(storageId <= 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "storageId:" + storageId);
                continue;
            }

            StoragePO storagePO = storagePOMapper.selectByPrimaryKey(storageId);

            if(storagePO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "storagePO");
                continue;
            }

            StorageVO storageVO = storageDTO.convertToCourseVO(storagePO);

            if(storageVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "storageVO");
                continue;
            }

            storageVOS.add(storageVO);
        }

        return storageVOS;
    }
}
