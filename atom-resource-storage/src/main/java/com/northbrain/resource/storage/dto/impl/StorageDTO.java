package com.northbrain.resource.storage.dto.impl;

import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.resource.storage.model.po.StorageDetailHisPO;
import com.northbrain.resource.storage.model.po.StorageDetailPO;
import com.northbrain.resource.storage.model.po.StorageHisPO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.StorageVO;
import com.northbrain.resource.storage.dto.IStorageDTO;
import com.northbrain.resource.storage.model.po.StoragePO;

import java.util.ArrayList;
import java.util.List;

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
     * 方法：将存储数据持久化对象StoragePO转换成值对象StorageVO
     *
     * @param storagePO 存储数据持久化对象
     * @param storageDetailPOS 存储明细持久化对象列表
     * @return CourseVO 存储数据值对象
     * @throws Exception 异常
     */
    @Override
    public StorageVO convertToStorageVO(StoragePO storagePO, List<StorageDetailPO> storageDetailPOS) throws Exception
    {
        if(storagePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "storagePO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        StorageVO storageVO = new StorageVO();

        storageVO.setRecordId(Constants.BUSINESS_COMMON_OPERATION_RECORD_DEFAULT_ID);
        storageVO.setStorageId(storagePO.getStorageId());
        storageVO.setResourceId(storagePO.getResourceId());
        storageVO.setDomain(storagePO.getDomain());
        storageVO.setCategory(storagePO.getCategory());
        storageVO.setType(storagePO.getType());
        storageVO.setUri(storagePO.getUri());
        storageVO.setStatus(storagePO.getStatus());
        storageVO.setCreateTime(storagePO.getCreateTime());
        storageVO.setStatusTime(storagePO.getStatusTime());
        storageVO.setDescription(storagePO.getDescription());

        if(storageDetailPOS == null || storageDetailPOS.size() == 0)
        {
            return storageVO;
        }

        List<StorageVO.StorageDetailVO> storageDetailVOS = new ArrayList<>();

        for(StorageDetailPO storageDetailPO: storageDetailPOS)
        {
            StorageVO.StorageDetailVO storageDetailVO = new StorageVO.StorageDetailVO();

            storageDetailVO.setStorageDetailId(storageDetailPO.getStorageDetailId());
            storageDetailVO.setAttribute(storageDetailPO.getAttribute());
            storageDetailVO.setValue(storageDetailPO.getValue());
            storageDetailVO.setStatus(storageDetailPO.getStatus());
            storageDetailVO.setCreateTime(storageDetailPO.getCreateTime());
            storageDetailVO.setStatusTime(storageDetailPO.getStatusTime());
            storageDetailVO.setDescription(storageDetailPO.getDescription());

            storageDetailVOS.add(storageDetailVO);
        }

        storageVO.setStorageDetailVOS(storageDetailVOS);

        return storageVO;
    }

    /**
     * 方法：将存储数据传值对象StorageVO转换成持久化对象StoragePO
     *
     * @param storageVO 存储数据VO值对象
     * @return StoragePO 存储数据持久化对象
     * @throws Exception 异常
     */
    @Override
    public StoragePO convertToStoragePO(StorageVO storageVO) throws Exception
    {
        if(storageVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "storageVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        StoragePO storagePO = new StoragePO();

        storagePO.setStorageId(storageVO.getStorageId());
        storagePO.setResourceId(storageVO.getResourceId());
        storagePO.setDomain(storageVO.getDomain());
        storagePO.setCategory(storageVO.getCategory());
        storagePO.setType(storageVO.getType());
        storagePO.setUri(storageVO.getUri());
        storagePO.setStatus(storageVO.getStatus());
        storagePO.setCreateTime(storageVO.getCreateTime());
        storagePO.setStatusTime(storageVO.getStatusTime());
        storagePO.setDescription(storageVO.getDescription());

        return storagePO;
    }

    /**
     * 方法：将存储数据值对象转换成存储数据明细持久化对象列表
     *
     * @param storageVO 存储数据值对象
     * @return 存储数据明细持久化对象列表
     * @throws Exception 异常
     */
    @Override
    public List<StorageDetailPO> convertToStorageDetailPOS(StorageVO storageVO) throws Exception
    {
        if(storageVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "storageVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(storageVO.getStorageDetailVOS() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "storageVO.getStorageDetailVOS()");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        List<StorageDetailPO> storageDetailPOS = new ArrayList<>();

        for(StorageVO.StorageDetailVO storageDetailVO: storageVO.getStorageDetailVOS())
        {
            StorageDetailPO storageDetailPO = new StorageDetailPO();

            storageDetailPO.setStorageDetailId(storageDetailVO.getStorageDetailId());
            storageDetailPO.setStorageId(storageVO.getStorageId());
            storageDetailPO.setAttribute(storageDetailVO.getAttribute());
            storageDetailPO.setValue(storageDetailVO.getValue());
            storageDetailPO.setStatus(storageDetailVO.getStatus());
            storageDetailPO.setCreateTime(storageDetailVO.getCreateTime());
            storageDetailPO.setStatusTime(storageDetailVO.getStatusTime());
            storageDetailPO.setDescription(storageDetailVO.getDescription());

            storageDetailPOS.add(storageDetailPO);
        }

        return storageDetailPOS;
    }

    /**
     * 方法：将存储数据持久化对象转换成存储数据历史持久化对象
     *
     * @param recordId    操作记录编号
     * @param operateType 操作类型
     * @param storagePO   存储数据持久化对象
     * @return 存储数据历史持久化对象
     * @throws Exception 异常
     */
    @Override
    public StorageHisPO convertToStorageHisPO(Integer recordId, String operateType, StoragePO storagePO) throws Exception
    {
        if(recordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "recordId:" + recordId);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(operateType == null || operateType.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operateType");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(storagePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "storagePO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        StorageHisPO storageHisPO = new StorageHisPO();

        storageHisPO.setRecordId(recordId);
        storageHisPO.setOperateType(operateType);
        storageHisPO.setStorageId(storagePO.getStorageId());
        storageHisPO.setResourceId(storagePO.getResourceId());
        storageHisPO.setDomain(storagePO.getDomain());
        storageHisPO.setCategory(storagePO.getCategory());
        storageHisPO.setType(storagePO.getType());
        storageHisPO.setUri(storagePO.getUri());
        storageHisPO.setStatus(storagePO.getStatus());
        storageHisPO.setCreateTime(storagePO.getCreateTime());
        storageHisPO.setStatusTime(storagePO.getStatusTime());
        storageHisPO.setDescription(storagePO.getDescription());

        return storageHisPO;
    }

    /**
     * 方法：将存储数据明细持久化对象转换成存储数据明细历史持久化对象
     *
     * @param recordId        操作记录编号
     * @param operateType     操作类型
     * @param storageDetailPO 存储数据明细持久化对象
     * @return 存储数据明细历史持久化对象
     * @throws Exception 异常
     */
    @Override
    public StorageDetailHisPO convertToStorageDetailHisPO(Integer recordId, String operateType, StorageDetailPO storageDetailPO) throws Exception
    {
        if(recordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "recordId:" + recordId);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(operateType == null || operateType.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operateType");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(storageDetailPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "storageDetailPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        StorageDetailHisPO storageDetailHisPO = new StorageDetailHisPO();

        storageDetailHisPO.setRecordId(recordId);
        storageDetailHisPO.setOperateType(operateType);
        storageDetailHisPO.setStorageDetailId(storageDetailPO.getStorageDetailId());
        storageDetailHisPO.setStorageId(storageDetailPO.getStorageId());
        storageDetailHisPO.setAttribute(storageDetailPO.getAttribute());
        storageDetailHisPO.setValue(storageDetailPO.getValue());
        storageDetailHisPO.setStatus(storageDetailPO.getStatus());
        storageDetailHisPO.setCreateTime(storageDetailPO.getCreateTime());
        storageDetailHisPO.setStatusTime(storageDetailPO.getStatusTime());
        storageDetailHisPO.setDescription(storageDetailPO.getDescription());

        return storageDetailHisPO;
    }
}
