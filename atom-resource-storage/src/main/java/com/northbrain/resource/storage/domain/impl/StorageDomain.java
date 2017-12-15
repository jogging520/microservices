package com.northbrain.resource.storage.domain.impl;

import java.util.ArrayList;
import java.util.List;

import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.resource.storage.dao.StorageDetailHisPOMapper;
import com.northbrain.resource.storage.dao.StorageDetailPOMapper;
import com.northbrain.resource.storage.dao.StorageHisPOMapper;
import com.northbrain.resource.storage.exception.StorageException;
import com.northbrain.resource.storage.model.po.StorageDetailHisPO;
import com.northbrain.resource.storage.model.po.StorageDetailPO;
import com.northbrain.resource.storage.model.po.StorageHisPO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.CollectionEmptyException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.atom.StorageVO;
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
    private final StorageHisPOMapper storageHisPOMapper;
    private final StorageDetailPOMapper storageDetailPOMapper;
    private final StorageDetailHisPOMapper storageDetailHisPOMapper;
    private final IStorageDTO storageDTO;

    @Autowired
    public StorageDomain(StoragePOMapper storagePOMapper, StorageHisPOMapper storageHisPOMapper,
                         StorageDetailPOMapper storageDetailPOMapper, StorageDetailHisPOMapper storageDetailHisPOMapper,
                         IStorageDTO storageDTO)
    {
        this.storagePOMapper = storagePOMapper;
        this.storageHisPOMapper = storageHisPOMapper;
        this.storageDetailPOMapper = storageDetailPOMapper;
        this.storageDetailHisPOMapper = storageDetailHisPOMapper;
        this.storageDTO = storageDTO;
    }


    /**
     * 方法：获取特定的资源信息
     *
     * @param storageId 资源存储编号
     * @return 存储资源
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

        if(storageDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailPOMapper");
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

        List<StorageDetailPO> storageDetailPOS = storageDetailPOMapper.selectByStorage(storageId);

        return storageDTO.convertToStorageVO(storagePO, storageDetailPOS);
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

        if(storageDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailPOMapper");
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

            List<StorageDetailPO> storageDetailPOS = storageDetailPOMapper.selectByStorage(storageId);

            StorageVO storageVO = storageDTO.convertToStorageVO(storagePO, storageDetailPOS);

            if(storageVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "storageVO");
                continue;
            }

            storageVOS.add(storageVO);
        }

        return storageVOS;
    }

    /**
     * 方法：新建一条存储资源，根据StorageVO再转换成相应的PO
     *
     * @param storageVO 存储资源值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    @Override
    public boolean createStorage(StorageVO storageVO) throws Exception
    {
        if(storageVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "storageVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(storagePOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storagePOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(storageHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(storageDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(storageDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(storageDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(storageVO);

        StoragePO storagePO = storageDTO.convertToStoragePO(storageVO);

        if(storagePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storagePO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(storagePOMapper.selectByPrimaryKey(storagePO.getStorageId()) == null)
        {
            if(storagePOMapper.insert(storagePO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_INSERT + String.valueOf(storagePO.getStorageId()));
                throw new StorageException(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_EXCEPTION);
            }

            StorageHisPO storageHisPO = storageDTO.convertToStorageHisPO(storageVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), storagePO);

            if(storageHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(storageHisPOMapper.insert(storageHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_INSERT + String.valueOf(storageHisPO.getStorageId()));
                throw new StorageException(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_EXCEPTION);
            }
        }

        if(storageVO.getStorageDetailVOS() != null && storageVO.getStorageDetailVOS().size() > 0)
        {
            List<StorageDetailPO> storageDetailPOS = storageDTO.convertToStorageDetailPOS(storageVO);

            if (storageDetailPOS == null || storageDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(StorageDetailPO storageDetailPO: storageDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(storageDetailPOMapper.selectByPrimaryKey(storageDetailPO.getStorageDetailId()) == null)
                {
                    if (storageDetailPOMapper.insert(storageDetailPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_INSERT + String.valueOf(storageDetailPO.getStorageDetailId()));
                        throw new StorageException(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_EXCEPTION);
                    }

                    StorageDetailHisPO storageDetailHisPO = storageDTO.convertToStorageDetailHisPO(storageVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), storageDetailPO);

                    if(storageDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    if (storageDetailHisPOMapper.insert(storageDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_INSERT + String.valueOf(storageDetailHisPO.getStorageDetailId()));
                        throw new StorageException(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }

    /**
     * 方法：更新一条存储资源，根据StorageVO再转换成相应的PO
     *
     * @param storageVO 存储资源值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public boolean updateStorage(StorageVO storageVO) throws Exception
    {
        if(storageVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "storageVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(storagePOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storagePOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(storageHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(storageDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(storageDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(storageDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(storageVO);

        StoragePO storagePO = storageDTO.convertToStoragePO(storageVO);

        if(storagePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storagePO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(storagePOMapper.selectByPrimaryKey(storagePO.getStorageId()) == null)
        {
            if(storagePOMapper.updateByPrimaryKey(storagePO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_UPDATE + String.valueOf(storagePO.getStorageId()));
                throw new StorageException(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_EXCEPTION);
            }

            StorageHisPO storageHisPO = storageDTO.convertToStorageHisPO(storageVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), storagePO);

            if(storageHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(storageHisPOMapper.insert(storageHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_INSERT + String.valueOf(storageHisPO.getStorageId()));
                throw new StorageException(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_EXCEPTION);
            }
        }

        if(storageVO.getStorageDetailVOS() != null && storageVO.getStorageDetailVOS().size() > 0)
        {
            List<StorageDetailPO> storageDetailPOS = storageDTO.convertToStorageDetailPOS(storageVO);

            if (storageDetailPOS == null || storageDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(StorageDetailPO storageDetailPO: storageDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(storageDetailPOMapper.selectByPrimaryKey(storageDetailPO.getStorageDetailId()) == null)
                {
                    if (storageDetailPOMapper.updateByPrimaryKey(storageDetailPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_UPDATE + String.valueOf(storageDetailPO.getStorageDetailId()));
                        throw new StorageException(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_EXCEPTION);
                    }

                    StorageDetailHisPO storageDetailHisPO = storageDTO.convertToStorageDetailHisPO(storageVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), storageDetailPO);

                    if(storageDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    if (storageDetailHisPOMapper.insert(storageDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_INSERT + String.valueOf(storageDetailHisPO.getStorageDetailId()));
                        throw new StorageException(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }

    /**
     * 方法：删除一条存储资源，根据StorageVO再转换成相应的PO
     *
     * @param storageVO 存储资源值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    @Override
    public boolean deleteStorage(StorageVO storageVO) throws Exception
    {
        if(storageVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "storageVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(storagePOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storagePOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(storageHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(storageDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(storageDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(storageDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(storageVO);

        StoragePO storagePO = storageDTO.convertToStoragePO(storageVO);

        if(storagePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storagePO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(storagePOMapper.selectByPrimaryKey(storagePO.getStorageId()) == null)
        {
            if(storagePOMapper.deleteByPrimaryKey(storagePO.getStorageId()) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_DELETE + String.valueOf(storagePO.getStorageId()));
                throw new StorageException(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_EXCEPTION);
            }

            StorageHisPO storageHisPO = storageDTO.convertToStorageHisPO(storageVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), storagePO);

            if(storageHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(storageHisPOMapper.insert(storageHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_INSERT + String.valueOf(storageHisPO.getStorageId()));
                throw new StorageException(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_EXCEPTION);
            }
        }

        if(storageVO.getStorageDetailVOS() != null && storageVO.getStorageDetailVOS().size() > 0)
        {
            List<StorageDetailPO> storageDetailPOS = storageDTO.convertToStorageDetailPOS(storageVO);

            if (storageDetailPOS == null || storageDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(StorageDetailPO storageDetailPO: storageDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(storageDetailPOMapper.selectByPrimaryKey(storageDetailPO.getStorageDetailId()) == null)
                {
                    if (storageDetailPOMapper.deleteByPrimaryKey(storageDetailPO.getStorageDetailId()) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_DELETE + String.valueOf(storageDetailPO.getStorageDetailId()));
                        throw new StorageException(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_EXCEPTION);
                    }

                    StorageDetailHisPO storageDetailHisPO = storageDTO.convertToStorageDetailHisPO(storageVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), storageDetailPO);

                    if(storageDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    if (storageDetailHisPOMapper.insert(storageDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_INSERT + String.valueOf(storageDetailHisPO.getStorageDetailId()));
                        throw new StorageException(Errors.ERROR_BUSINESS_RESOURCE_STORAGE_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }
}
