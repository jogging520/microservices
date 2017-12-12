package com.northbrain.party.organization.domain.impl;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.OrganizationVO;
import com.northbrain.base.common.model.vo.SubjectionVO;
import com.northbrain.party.organization.dao.OrganizationHisPOMapper;
import com.northbrain.party.organization.dao.OrganizationPOMapper;
import com.northbrain.party.organization.dao.SubjectionHisPOMapper;
import com.northbrain.party.organization.dao.SubjectionPOMapper;
import com.northbrain.party.organization.domain.IOrganizationDomain;
import com.northbrain.party.organization.dto.IOrganizationDTO;
import com.northbrain.party.organization.exception.OrganizationException;
import com.northbrain.party.organization.exception.SubjectionException;
import com.northbrain.party.organization.model.po.OrganizationHisPO;
import com.northbrain.party.organization.model.po.OrganizationPO;
import com.northbrain.party.organization.model.po.SubjectionHisPO;
import com.northbrain.party.organization.model.po.SubjectionPO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类名：组织机构和隶属关系DOMAIN接口的实现类
 * 用途：实现组织机构的增删改查，对DAO进行封装。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class OrganizationDomain implements IOrganizationDomain
{
    private static Logger logger = Logger.getLogger(OrganizationDomain.class);
    private final OrganizationPOMapper organizationPOMapper;
    private final OrganizationHisPOMapper organizationHisPOMapper;
    private final SubjectionPOMapper subjectionPOMapper;
    private final SubjectionHisPOMapper subjectionHisPOMapper;
    private final IOrganizationDTO organizationDTO;

    @Autowired
    public OrganizationDomain(OrganizationPOMapper organizationPOMapper, OrganizationHisPOMapper organizationHisPOMapper,
                              SubjectionPOMapper subjectionPOMapper, SubjectionHisPOMapper subjectionHisPOMapper,
                              IOrganizationDTO organizationDTO)
    {
        this.organizationPOMapper = organizationPOMapper;
        this.organizationHisPOMapper = organizationHisPOMapper;
        this.subjectionPOMapper = subjectionPOMapper;
        this.subjectionHisPOMapper = subjectionHisPOMapper;
        this.organizationDTO = organizationDTO;
    }

    /**
     * 方法：新建一条组织机构，根据OrganizationVO再转换成相应的PO
     *
     * @param organizationVO 组织机构值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    @Override
    public boolean createOrganization(OrganizationVO organizationVO) throws Exception
    {
        if(organizationVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "organizationVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(organizationPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(organizationHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(organizationDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(organizationVO);

        OrganizationPO organizationPO = organizationDTO.convertToOrganizationPO(organizationVO);

        if(organizationPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(organizationPOMapper.selectByPrimaryKey(organizationPO.getOrganizationId()) == null)
        {
            if(organizationPOMapper.insert(organizationPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_INSERT + String.valueOf(organizationPO.getOrganizationId()));
                throw new OrganizationException(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            }

            OrganizationHisPO organizationHisPO = organizationDTO.convertToOrganizationHisPO(organizationVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), organizationPO);

            if(organizationHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(organizationHisPOMapper.insert(organizationHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_INSERT + String.valueOf(organizationHisPO.getOrganizationId()));
                throw new OrganizationException(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            }

            return true;
        }

        return false;
    }

    /**
     * 方法：更新一条组织机构，根据OrganizationVO再转换成相应的PO
     *
     * @param organizationVO 组织机构值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public boolean updateOrganization(OrganizationVO organizationVO) throws Exception
    {
        if(organizationVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "organizationVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(organizationPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(organizationHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(organizationDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(organizationVO);

        OrganizationPO organizationPO = organizationDTO.convertToOrganizationPO(organizationVO);

        if(organizationPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(organizationPOMapper.selectByPrimaryKey(organizationPO.getOrganizationId()) == null)
        {
            if(organizationPOMapper.updateByPrimaryKey(organizationPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_UPDATE + String.valueOf(organizationPO.getOrganizationId()));
                throw new OrganizationException(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            }

            OrganizationHisPO organizationHisPO = organizationDTO.convertToOrganizationHisPO(organizationVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), organizationPO);

            if(organizationHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(organizationHisPOMapper.insert(organizationHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_INSERT + String.valueOf(organizationHisPO.getOrganizationId()));
                throw new OrganizationException(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            }

            return true;
        }

        return false;
    }

    /**
     * 方法：删除一条组织机构，根据OrganizationVO再转换成相应的PO
     *
     * @param organizationVO 组织机构值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    @Override
    public boolean deleteOrganization(OrganizationVO organizationVO) throws Exception
    {
        if(organizationVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "organizationVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(organizationPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(organizationHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(organizationDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(organizationVO);

        OrganizationPO organizationPO = organizationDTO.convertToOrganizationPO(organizationVO);

        if(organizationPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(organizationPOMapper.selectByPrimaryKey(organizationPO.getOrganizationId()) == null)
        {
            if(organizationPOMapper.deleteByPrimaryKey(organizationPO.getOrganizationId()) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_DELETE + String.valueOf(organizationPO.getOrganizationId()));
                throw new OrganizationException(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            }

            OrganizationHisPO organizationHisPO = organizationDTO.convertToOrganizationHisPO(organizationVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), organizationPO);

            if(organizationHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(organizationHisPOMapper.insert(organizationHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_INSERT + String.valueOf(organizationHisPO.getOrganizationId()));
                throw new OrganizationException(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            }

            return true;
        }

        return false;
    }

    /**
     * 方法：新建一条隶属关系，根据SubjectionVO再转换成相应的PO
     *
     * @param subjectionVO 隶属关系值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    @Override
    public boolean createSubjection(SubjectionVO subjectionVO) throws Exception
    {
        if(subjectionVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "subjectionVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(subjectionPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "subjectionPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(subjectionHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "subjectionHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(organizationDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(subjectionVO);

        SubjectionPO subjectionPO = organizationDTO.convertToSubjectionPO(subjectionVO);

        if(subjectionPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "subjectionPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(subjectionPOMapper.selectByPrimaryKey(subjectionPO.getSubjectionId()) == null)
        {
            if(subjectionPOMapper.insert(subjectionPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_INSERT + String.valueOf(subjectionPO.getSubjectionId()));
                throw new SubjectionException(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
            }

            SubjectionHisPO subjectionHisPO = organizationDTO.convertToSubjectionHisPO(subjectionVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), subjectionPO);

            if(subjectionHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "subjectionHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(subjectionHisPOMapper.insert(subjectionHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_INSERT + String.valueOf(subjectionHisPO.getSubjectionId()));
                throw new SubjectionException(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
            }

            return true;
        }

        return false;
    }

    /**
     * 方法：更新一条隶属关系，根据SubjectionVO再转换成相应的PO
     *
     * @param subjectionVO 隶属关系值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public boolean updateSubjection(SubjectionVO subjectionVO) throws Exception
    {
        if(subjectionVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "subjectionVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(subjectionPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "subjectionPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(subjectionHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "subjectionHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(organizationDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(subjectionVO);

        SubjectionPO subjectionPO = organizationDTO.convertToSubjectionPO(subjectionVO);

        if(subjectionPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "subjectionPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(subjectionPOMapper.selectByPrimaryKey(subjectionPO.getSubjectionId()) == null)
        {
            if(subjectionPOMapper.updateByPrimaryKey(subjectionPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_UPDATE + String.valueOf(subjectionPO.getSubjectionId()));
                throw new SubjectionException(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
            }

            SubjectionHisPO subjectionHisPO = organizationDTO.convertToSubjectionHisPO(subjectionVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), subjectionPO);

            if(subjectionHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "subjectionHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(subjectionHisPOMapper.insert(subjectionHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_INSERT + String.valueOf(subjectionHisPO.getSubjectionId()));
                throw new SubjectionException(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
            }

            return true;
        }

        return false;
    }

    /**
     * 方法：删除一条隶属关系，根据SubjectionVO再转换成相应的PO
     *
     * @param subjectionVO 隶属关系值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    @Override
    public boolean deleteSubjection(SubjectionVO subjectionVO) throws Exception
    {
        if(subjectionVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "subjectionVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(subjectionPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "subjectionPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(subjectionHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "subjectionHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(organizationDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(subjectionVO);

        SubjectionPO subjectionPO = organizationDTO.convertToSubjectionPO(subjectionVO);

        if(subjectionPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "subjectionPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(subjectionPOMapper.selectByPrimaryKey(subjectionPO.getSubjectionId()) == null)
        {
            if(subjectionPOMapper.deleteByPrimaryKey(subjectionPO.getSubjectionId()) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_DELETE + String.valueOf(subjectionPO.getSubjectionId()));
                throw new SubjectionException(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
            }

            SubjectionHisPO subjectionHisPO = organizationDTO.convertToSubjectionHisPO(subjectionVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), subjectionPO);

            if(subjectionHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "subjectionHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(subjectionHisPOMapper.insert(subjectionHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_INSERT + String.valueOf(subjectionHisPO.getSubjectionId()));
                throw new SubjectionException(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
            }

            return true;
        }

        return false;
    }
}
