package com.northbrain.resource.basic.domain.impl;

import java.util.List;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.atom.ResourceVO;
import com.northbrain.resource.basic.dao.ResourceDetailHisPOMapper;
import com.northbrain.resource.basic.dao.ResourceDetailPOMapper;
import com.northbrain.resource.basic.dao.ResourceHisPOMapper;
import com.northbrain.resource.basic.dao.ResourcePOMapper;
import com.northbrain.resource.basic.domain.IResourceDomain;
import com.northbrain.resource.basic.dto.IResourceDTO;
import com.northbrain.resource.basic.exception.ResourceException;
import com.northbrain.resource.basic.model.po.ResourceDetailHisPO;
import com.northbrain.resource.basic.model.po.ResourceDetailPO;
import com.northbrain.resource.basic.model.po.ResourceHisPO;
import com.northbrain.resource.basic.model.po.ResourcePO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类名：资源DOMAIN接口的实现类
 * 用途：实现资源的增删改查，对DAO进行封装。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class ResourceDomain implements IResourceDomain
{
    private static Logger logger = Logger.getLogger(ResourceDomain.class);
    private final ResourcePOMapper resourcePOMapper;
    private final ResourceHisPOMapper resourceHisPOMapper;
    private final ResourceDetailPOMapper resourceDetailPOMapper;
    private final ResourceDetailHisPOMapper resourceDetailHisPOMapper;
    private final IResourceDTO resourceDTO;

    @Autowired
    public ResourceDomain(ResourcePOMapper resourcePOMapper, ResourceHisPOMapper resourceHisPOMapper,
                          ResourceDetailPOMapper resourceDetailPOMapper, ResourceDetailHisPOMapper resourceDetailHisPOMapper,
                          IResourceDTO resourceDTO)
    {
        this.resourcePOMapper = resourcePOMapper;
        this.resourceHisPOMapper = resourceHisPOMapper;
        this.resourceDetailPOMapper = resourceDetailPOMapper;
        this.resourceDetailHisPOMapper = resourceDetailHisPOMapper;
        this.resourceDTO = resourceDTO;
    }

    /**
     * 方法：新建一条资源，根据ResourceVO再转换成相应的PO
     *
     * @param resourceVO 资源值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    @Override
    public boolean createResource(ResourceVO resourceVO) throws Exception
    {
        if(resourceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "resourceVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(resourcePOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourcePOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(resourceHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(resourceDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(resourceDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(resourceDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(resourceVO);

        ResourcePO resourcePO = resourceDTO.convertToResourcePO(resourceVO);

        if(resourcePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourcePO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(resourcePOMapper.selectByPrimaryKey(resourcePO.getResourceId()) == null)
        {
            if(resourcePOMapper.insert(resourcePO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(resourcePO.getResourceId()));
                throw new ResourceException(Errors.ERROR_BUSINESS_RESOURCE_BASIC_EXCEPTION);
            }

            ResourceHisPO resourceHisPO = resourceDTO.convertToResourceHisPO(resourceVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), resourcePO);

            if(resourceHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(resourceHisPOMapper.insert(resourceHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(resourceHisPO.getResourceId()));
                throw new ResourceException(Errors.ERROR_BUSINESS_RESOURCE_BASIC_EXCEPTION);
            }
        }

        if(resourceVO.getResourceDetailVOS() != null && resourceVO.getResourceDetailVOS().size() > 0)
        {
            List<ResourceDetailPO> resourceDetailPOS = resourceDTO.convertToResourceDetailPOS(resourceVO);

            if (resourceDetailPOS == null || resourceDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(ResourceDetailPO resourceDetailPO: resourceDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(resourceDetailPOMapper.selectByPrimaryKey(resourceDetailPO.getResourceDetailId()) == null)
                {
                    if (resourceDetailPOMapper.insert(resourceDetailPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(resourceDetailPO.getResourceDetailId()));
                        throw new ResourceException(Errors.ERROR_BUSINESS_RESOURCE_BASIC_EXCEPTION);
                    }

                    ResourceDetailHisPO resourceDetailHisPO = resourceDTO.convertToResourceDetailHisPO(resourceVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), resourceDetailPO);

                    if(resourceDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    if (resourceDetailHisPOMapper.insert(resourceDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(resourceDetailHisPO.getResourceDetailId()));
                        throw new ResourceException(Errors.ERROR_BUSINESS_RESOURCE_BASIC_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }

    /**
     * 方法：更新一条资源，根据ResourceVO再转换成相应的PO
     *
     * @param resourceVO 资源值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public boolean updateResource(ResourceVO resourceVO) throws Exception
    {
        if(resourceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "resourceVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(resourcePOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourcePOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(resourceHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(resourceDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(resourceDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(resourceDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(resourceVO);

        ResourcePO resourcePO = resourceDTO.convertToResourcePO(resourceVO);

        if(resourcePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourcePO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(resourcePOMapper.selectByPrimaryKey(resourcePO.getResourceId()) == null)
        {
            if(resourcePOMapper.updateByPrimaryKey(resourcePO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_UPDATE + String.valueOf(resourcePO.getResourceId()));
                throw new ResourceException(Errors.ERROR_BUSINESS_RESOURCE_BASIC_EXCEPTION);
            }

            ResourceHisPO resourceHisPO = resourceDTO.convertToResourceHisPO(resourceVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), resourcePO);

            if(resourceHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(resourceHisPOMapper.insert(resourceHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(resourceHisPO.getResourceId()));
                throw new ResourceException(Errors.ERROR_BUSINESS_RESOURCE_BASIC_EXCEPTION);
            }
        }

        if(resourceVO.getResourceDetailVOS() != null && resourceVO.getResourceDetailVOS().size() > 0)
        {
            List<ResourceDetailPO> resourceDetailPOS = resourceDTO.convertToResourceDetailPOS(resourceVO);

            if (resourceDetailPOS == null || resourceDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(ResourceDetailPO resourceDetailPO: resourceDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(resourceDetailPOMapper.selectByPrimaryKey(resourceDetailPO.getResourceDetailId()) == null)
                {
                    if (resourceDetailPOMapper.updateByPrimaryKey(resourceDetailPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_UPDATE + String.valueOf(resourceDetailPO.getResourceDetailId()));
                        throw new ResourceException(Errors.ERROR_BUSINESS_RESOURCE_BASIC_EXCEPTION);
                    }

                    ResourceDetailHisPO resourceDetailHisPO = resourceDTO.convertToResourceDetailHisPO(resourceVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), resourceDetailPO);

                    if(resourceDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    if (resourceDetailHisPOMapper.insert(resourceDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(resourceDetailHisPO.getResourceDetailId()));
                        throw new ResourceException(Errors.ERROR_BUSINESS_RESOURCE_BASIC_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }

    /**
     * 方法：删除一条资源，根据ResourceVO再转换成相应的PO
     *
     * @param resourceVO 资源值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    @Override
    public boolean deleteResource(ResourceVO resourceVO) throws Exception
    {
        if(resourceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "resourceVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(resourcePOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourcePOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(resourceHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(resourceDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(resourceDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(resourceDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(resourceVO);

        ResourcePO resourcePO = resourceDTO.convertToResourcePO(resourceVO);

        if(resourcePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourcePO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(resourcePOMapper.selectByPrimaryKey(resourcePO.getResourceId()) == null)
        {
            if(resourcePOMapper.deleteByPrimaryKey(resourcePO.getResourceId()) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_DELETE + String.valueOf(resourcePO.getResourceId()));
                throw new ResourceException(Errors.ERROR_BUSINESS_RESOURCE_BASIC_EXCEPTION);
            }

            ResourceHisPO resourceHisPO = resourceDTO.convertToResourceHisPO(resourceVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), resourcePO);

            if(resourceHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(resourceHisPOMapper.insert(resourceHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(resourceHisPO.getResourceId()));
                throw new ResourceException(Errors.ERROR_BUSINESS_RESOURCE_BASIC_EXCEPTION);
            }
        }

        if(resourceVO.getResourceDetailVOS() != null && resourceVO.getResourceDetailVOS().size() > 0)
        {
            List<ResourceDetailPO> resourceDetailPOS = resourceDTO.convertToResourceDetailPOS(resourceVO);

            if (resourceDetailPOS == null || resourceDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(ResourceDetailPO resourceDetailPO: resourceDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(resourceDetailPOMapper.selectByPrimaryKey(resourceDetailPO.getResourceDetailId()) == null)
                {
                    if (resourceDetailPOMapper.deleteByPrimaryKey(resourceDetailPO.getResourceDetailId()) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_DELETE + String.valueOf(resourceDetailPO.getResourceDetailId()));
                        throw new ResourceException(Errors.ERROR_BUSINESS_RESOURCE_BASIC_EXCEPTION);
                    }

                    ResourceDetailHisPO resourceDetailHisPO = resourceDTO.convertToResourceDetailHisPO(resourceVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), resourceDetailPO);

                    if(resourceDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "resourceDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    if (resourceDetailHisPOMapper.insert(resourceDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(resourceDetailHisPO.getResourceDetailId()));
                        throw new ResourceException(Errors.ERROR_BUSINESS_RESOURCE_BASIC_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }
}
