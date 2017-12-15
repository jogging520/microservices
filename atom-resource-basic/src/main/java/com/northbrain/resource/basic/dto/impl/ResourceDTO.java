package com.northbrain.resource.basic.dto.impl;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.ResourceVO;
import com.northbrain.resource.basic.dto.IResourceDTO;
import com.northbrain.resource.basic.model.po.ResourceDetailHisPO;
import com.northbrain.resource.basic.model.po.ResourceDetailPO;
import com.northbrain.resource.basic.model.po.ResourceHisPO;
import com.northbrain.resource.basic.model.po.ResourcePO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：资源传输对象接口的实现类
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class ResourceDTO implements IResourceDTO
{
    private static Logger logger = Logger.getLogger(ResourceDTO.class);
    /**
     * 方法：将资源持久化对象转换成值对象
     *
     * @param resourcePO        资源持久化对象
     * @param resourceDetailPOS 资源明细持久化对象
     * @return 资源值对象
     * @throws Exception 异常
     */
    @Override
    public ResourceVO convertToResourceVO(ResourcePO resourcePO, List<ResourceDetailPO> resourceDetailPOS) throws Exception
    {
        if(resourcePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "resourcePO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        ResourceVO resourceVO = new ResourceVO();

        resourceVO.setRecordId(Constants.BUSINESS_COMMON_OPERATION_RECORD_DEFAULT_ID);
        resourceVO.setResourceId(resourcePO.getResourceId());
        resourceVO.setOrganizationId(resourcePO.getOrganizationId());
        resourceVO.setEntityId(resourcePO.getEntityId());
        resourceVO.setDomain(resourcePO.getDomain());
        resourceVO.setCategory(resourcePO.getCategory());
        resourceVO.setType(resourcePO.getType());
        resourceVO.setStatus(resourcePO.getStatus());
        resourceVO.setCreateTime(resourcePO.getCreateTime());
        resourceVO.setStatusTime(resourcePO.getStatusTime());
        resourceVO.setDescription(resourcePO.getDescription());

        if(resourceDetailPOS == null || resourceDetailPOS.size() == 0)
        {
            return resourceVO;
        }

        List<ResourceVO.ResourceDetailVO> resourceDetailVOS = new ArrayList<>();

        for(ResourceDetailPO resourceDetailPO: resourceDetailPOS)
        {
            ResourceVO.ResourceDetailVO resourceDetailVO = new ResourceVO.ResourceDetailVO();

            resourceDetailVO.setResourceDetailId(resourceDetailPO.getResourceDetailId());
            resourceDetailVO.setAttribute(resourceDetailPO.getAttribute());
            resourceDetailVO.setValue(resourceDetailPO.getValue());
            resourceDetailVO.setStatus(resourceDetailPO.getStatus());
            resourceDetailVO.setCreateTime(resourceDetailPO.getCreateTime());
            resourceDetailVO.setStatusTime(resourceDetailPO.getStatusTime());
            resourceDetailVO.setDescription(resourceDetailPO.getDescription());

            resourceDetailVOS.add(resourceDetailVO);
        }

        resourceVO.setResourceDetailVOS(resourceDetailVOS);

        return resourceVO;
    }

    /**
     * 方法：将资源值对象转换成资源持久化对象
     *
     * @param resourceVO 资源值对象
     * @return 资源持久化对象
     * @throws Exception 异常
     */
    @Override
    public ResourcePO convertToResourcePO(ResourceVO resourceVO) throws Exception
    {
        if(resourceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "resourceVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        ResourcePO resourcePO = new ResourcePO();

        resourcePO.setResourceId(resourceVO.getResourceId());
        resourcePO.setOrganizationId(resourceVO.getOrganizationId());
        resourcePO.setEntityId(resourceVO.getEntityId());
        resourcePO.setDomain(resourceVO.getDomain());
        resourcePO.setCategory(resourceVO.getCategory());
        resourcePO.setType(resourceVO.getType());
        resourcePO.setStatus(resourceVO.getStatus());
        resourcePO.setCreateTime(resourceVO.getCreateTime());
        resourcePO.setStatusTime(resourceVO.getStatusTime());
        resourcePO.setDescription(resourceVO.getDescription());

        return resourcePO;
    }

    /**
     * 方法：将资源值对象转换成资源明细持久化对象列表
     *
     * @param resourceVO 资源值对象
     * @return 资源明细持久化对象列表
     * @throws Exception 异常
     */
    @Override
    public List<ResourceDetailPO> convertToResourceDetailPOS(ResourceVO resourceVO) throws Exception
    {
        if(resourceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "resourceVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(resourceVO.getResourceDetailVOS() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "resourceVO.getResourceDetailVOS()");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        List<ResourceDetailPO> resourceDetailPOS = new ArrayList<>();

        for(ResourceVO.ResourceDetailVO resourceDetailVO: resourceVO.getResourceDetailVOS())
        {
            ResourceDetailPO resourceDetailPO = new ResourceDetailPO();

            resourceDetailPO.setResourceDetailId(resourceDetailVO.getResourceDetailId());
            resourceDetailPO.setResourceId(resourceVO.getResourceId());
            resourceDetailPO.setAttribute(resourceDetailVO.getAttribute());
            resourceDetailPO.setValue(resourceDetailVO.getValue());
            resourceDetailPO.setStatus(resourceDetailVO.getStatus());
            resourceDetailPO.setCreateTime(resourceDetailVO.getCreateTime());
            resourceDetailPO.setStatusTime(resourceDetailVO.getStatusTime());
            resourceDetailPO.setDescription(resourceDetailVO.getDescription());

            resourceDetailPOS.add(resourceDetailPO);
        }

        return resourceDetailPOS;
    }

    /**
     * 方法：将资源持久化对象转换成资源历史持久化对象
     *
     * @param recordId    操作记录编号
     * @param operateType 操作类型
     * @param resourcePO  资源持久化对象
     * @return 资源历史持久化对象
     * @throws Exception 异常
     */
    @Override
    public ResourceHisPO convertToResourceHisPO(Integer recordId, String operateType, ResourcePO resourcePO) throws Exception
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

        if(resourcePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "resourcePO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        ResourceHisPO resourceHisPO = new ResourceHisPO();
        resourceHisPO.setRecordId(recordId);
        resourceHisPO.setOperateType(operateType);
        resourceHisPO.setResourceId(resourcePO.getResourceId());
        resourceHisPO.setOrganizationId(resourcePO.getOrganizationId());
        resourceHisPO.setEntityId(resourcePO.getEntityId());
        resourceHisPO.setDomain(resourcePO.getDomain());
        resourceHisPO.setCategory(resourcePO.getCategory());
        resourceHisPO.setType(resourcePO.getType());
        resourceHisPO.setStatus(resourcePO.getStatus());
        resourceHisPO.setCreateTime(resourcePO.getCreateTime());
        resourceHisPO.setStatusTime(resourcePO.getStatusTime());
        resourceHisPO.setDescription(resourcePO.getDescription());

        return resourceHisPO;
    }

    /**
     * 方法：将资源明细持久化对象转换成资源明细历史持久化对象
     *
     * @param recordId         操作记录编号
     * @param operateType      操作类型
     * @param resourceDetailPO 资源明细持久化对象
     * @return 资源明细历史持久化对象
     * @throws Exception 异常
     */
    @Override
    public ResourceDetailHisPO convertToResourceDetailHisPO(Integer recordId, String operateType, ResourceDetailPO resourceDetailPO) throws Exception
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

        if(resourceDetailPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "resourceDetailPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        ResourceDetailHisPO resourceDetailHisPO = new ResourceDetailHisPO();
        resourceDetailHisPO.setRecordId(recordId);
        resourceDetailHisPO.setOperateType(operateType);
        resourceDetailHisPO.setResourceDetailId(resourceDetailPO.getResourceDetailId());
        resourceDetailHisPO.setResourceId(resourceDetailPO.getResourceId());
        resourceDetailHisPO.setAttribute(resourceDetailPO.getAttribute());
        resourceDetailHisPO.setValue(resourceDetailPO.getValue());
        resourceDetailHisPO.setStatus(resourceDetailPO.getStatus());
        resourceDetailHisPO.setCreateTime(resourceDetailPO.getCreateTime());
        resourceDetailHisPO.setStatusTime(resourceDetailPO.getStatusTime());
        resourceDetailHisPO.setDescription(resourceDetailPO.getDescription());

        return resourceDetailHisPO;
    }
}
