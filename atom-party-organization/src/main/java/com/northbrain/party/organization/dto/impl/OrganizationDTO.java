package com.northbrain.party.organization.dto.impl;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.OrganizationVO;
import com.northbrain.base.common.model.vo.SubjectionVO;
import com.northbrain.party.organization.dto.IOrganizationDTO;
import com.northbrain.party.organization.model.po.OrganizationHisPO;
import com.northbrain.party.organization.model.po.OrganizationPO;
import com.northbrain.party.organization.model.po.SubjectionHisPO;
import com.northbrain.party.organization.model.po.SubjectionPO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 类名：组织机构传输对象接口的实现类
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class OrganizationDTO implements IOrganizationDTO
{
    private static Logger logger = Logger.getLogger(OrganizationDTO.class);

    /**
     * 方法：将组织机构PO转换成VO
     *
     * @param organizationPO 组织机构持久化对象
     * @return 组织机构值对象
     */
    @Override
    public OrganizationVO convertToOrganizationVO(OrganizationPO organizationPO) throws Exception
    {
        if(organizationPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "organizationPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        OrganizationVO organizationVO = new OrganizationVO();

        organizationVO.setRecordId(Constants.BUSINESS_COMMON_OPERATION_RECORD_DEFAULT_ID);
        organizationVO.setOrganizationId(organizationPO.getOrganizationId());
        organizationVO.setName(organizationPO.getName());
        organizationVO.setCode(organizationPO.getCode());
        organizationVO.setDomain(organizationPO.getDomain());
        organizationVO.setCategory(organizationPO.getCategory());
        organizationVO.setType(organizationPO.getType());
        organizationVO.setParentOrganizationId(organizationPO.getParentOrganizationId());
        organizationVO.setStatus(organizationPO.getStatus());
        organizationVO.setCreateTime(organizationPO.getCreateTime());
        organizationVO.setStatusTime(organizationPO.getStatusTime());
        organizationVO.setDescription(organizationPO.getDescription());

        return organizationVO;
    }

    /**
     * 方法：将组织机构VO转换成PO
     *
     * @param organizationVO 组织机构值对象
     * @return 组织机构持久化对象
     */
    @Override
    public OrganizationPO convertToOrganizationPO(OrganizationVO organizationVO) throws Exception
    {
        if(organizationVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "organizationVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        OrganizationPO organizationPO = new OrganizationPO();

        organizationPO.setOrganizationId(organizationVO.getOrganizationId());
        organizationPO.setName(organizationVO.getName());
        organizationPO.setCode(organizationVO.getCode());
        organizationPO.setDomain(organizationVO.getDomain());
        organizationPO.setCategory(organizationVO.getCategory());
        organizationPO.setType(organizationVO.getType());
        organizationPO.setParentOrganizationId(organizationVO.getParentOrganizationId());
        organizationPO.setStatus(organizationVO.getStatus());
        organizationPO.setCreateTime(organizationVO.getCreateTime());
        organizationPO.setStatusTime(organizationVO.getStatusTime());
        organizationPO.setDescription(organizationVO.getDescription());

        return organizationPO;
    }

    /**
     * 方法：将组织机构PO转换成历史PO
     *
     * @param recordId       操作记录编号
     * @param operateType    操作类型
     * @param organizationPO 组织机构持久化对象
     * @return 组织机构历史持久化对象
     */
    @Override
    public OrganizationHisPO convertToOrganizationHisPO(Integer recordId, String operateType, OrganizationPO organizationPO) throws Exception
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

        if(organizationPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "organizationPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        OrganizationHisPO organizationHisPO = new OrganizationHisPO();

        organizationHisPO.setRecordId(recordId);
        organizationHisPO.setOperateType(operateType);
        organizationHisPO.setOrganizationId(organizationPO.getOrganizationId());
        organizationHisPO.setName(organizationPO.getName());
        organizationHisPO.setCode(organizationPO.getCode());
        organizationHisPO.setDomain(organizationPO.getDomain());
        organizationHisPO.setCategory(organizationPO.getCategory());
        organizationHisPO.setType(organizationPO.getType());
        organizationHisPO.setParentOrganizationId(organizationPO.getParentOrganizationId());
        organizationHisPO.setStatus(organizationPO.getStatus());
        organizationHisPO.setCreateTime(organizationPO.getCreateTime());
        organizationHisPO.setStatusTime(organizationPO.getStatusTime());
        organizationHisPO.setDescription(organizationPO.getDescription());

        return organizationHisPO;
    }

    /**
     * 方法：将隶属关系PO转换成VO
     *
     * @param subjectionPO 隶属关系持久化对象
     * @return 隶属关系值对象
     */
    @Override
    public SubjectionVO convertToSubjectionVO(SubjectionPO subjectionPO) throws Exception
    {
        if(subjectionPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "subjectionPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        SubjectionVO subjectionVO = new SubjectionVO();

        subjectionVO.setRecordId(Constants.BUSINESS_COMMON_OPERATION_RECORD_DEFAULT_ID);
        subjectionVO.setSubjectionId(subjectionPO.getSubjectionId());
        subjectionVO.setPartyId(subjectionPO.getPartyId());
        subjectionVO.setOrganizationId(subjectionPO.getOrganizationId());
        subjectionVO.setEntityId(subjectionPO.getEntityId());
        subjectionVO.setDomain(subjectionPO.getDomain());
        subjectionVO.setCategory(subjectionPO.getCategory());
        subjectionVO.setType(subjectionPO.getType());
        subjectionVO.setStatus(subjectionPO.getStatus());
        subjectionVO.setCreateTime(subjectionPO.getCreateTime());
        subjectionVO.setStatusTime(subjectionPO.getStatusTime());
        subjectionVO.setDescription(subjectionPO.getDescription());

        return subjectionVO;
    }

    /**
     * 方法：将隶属关系VO转换成PO
     *
     * @param subjectionVO 隶属关系值对象
     * @return 隶属关系持久化对象
     */
    @Override
    public SubjectionPO convertToSubjectionPO(SubjectionVO subjectionVO) throws Exception
    {
        if(subjectionVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "subjectionVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        SubjectionPO subjectionPO = new SubjectionPO();

        subjectionPO.setSubjectionId(subjectionVO.getSubjectionId());
        subjectionPO.setPartyId(subjectionVO.getPartyId());
        subjectionPO.setOrganizationId(subjectionVO.getOrganizationId());
        subjectionPO.setEntityId(subjectionVO.getEntityId());
        subjectionPO.setDomain(subjectionVO.getDomain());
        subjectionPO.setCategory(subjectionVO.getCategory());
        subjectionPO.setType(subjectionVO.getType());
        subjectionPO.setStatus(subjectionVO.getStatus());
        subjectionPO.setCreateTime(subjectionVO.getCreateTime());
        subjectionPO.setStatusTime(subjectionVO.getStatusTime());
        subjectionPO.setDescription(subjectionVO.getDescription());

        return subjectionPO;
    }

    /**
     * 方法：将组织机构PO转换成历史PO
     *
     * @param recordId     操作记录编号
     * @param operateType  操作类型
     * @param subjectionPO 隶属关系持久化对象
     * @return 隶属关系历史持久化对象
     */
    @Override
    public SubjectionHisPO convertToSubjectionHisPO(Integer recordId, String operateType, SubjectionPO subjectionPO) throws Exception
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

        if(subjectionPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "subjectionPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        SubjectionHisPO subjectionHisPO = new SubjectionHisPO();

        subjectionHisPO.setRecordId(recordId);
        subjectionHisPO.setOperateType(operateType);
        subjectionHisPO.setSubjectionId(subjectionPO.getSubjectionId());
        subjectionHisPO.setPartyId(subjectionPO.getPartyId());
        subjectionHisPO.setOrganizationId(subjectionPO.getOrganizationId());
        subjectionHisPO.setEntityId(subjectionPO.getEntityId());
        subjectionHisPO.setDomain(subjectionPO.getDomain());
        subjectionHisPO.setCategory(subjectionPO.getCategory());
        subjectionHisPO.setType(subjectionPO.getType());
        subjectionHisPO.setStatus(subjectionPO.getStatus());
        subjectionHisPO.setCreateTime(subjectionPO.getCreateTime());
        subjectionHisPO.setStatusTime(subjectionPO.getStatusTime());
        subjectionHisPO.setDescription(subjectionPO.getDescription());

        return subjectionHisPO;
    }
}
