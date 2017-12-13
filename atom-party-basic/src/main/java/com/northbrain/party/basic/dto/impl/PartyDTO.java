package com.northbrain.party.basic.dto.impl;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.OrganizationVO;
import com.northbrain.base.common.model.vo.PartyVO;
import com.northbrain.base.common.model.vo.RoleVO;
import com.northbrain.base.common.model.vo.SubjectionVO;
import com.northbrain.party.basic.dto.IPartyDTO;
import com.northbrain.party.basic.model.po.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：参与者传输对象接口的实现类
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class PartyDTO implements IPartyDTO
{
    private static Logger logger = Logger.getLogger(PartyDTO.class);
    /**
     * 方法：将角色PO转换成VO
     *
     * @param rolePO 角色持久化对象
     * @return 角色值对象
     */
    @Override
    public RoleVO convertToRoleVO(RolePO rolePO) throws Exception
    {
        if(rolePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "rolePO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        RoleVO roleVO = new RoleVO();

        roleVO.setRecordId(Constants.BUSINESS_COMMON_OPERATION_RECORD_DEFAULT_ID);
        roleVO.setRoleId(rolePO.getRoleId());
        roleVO.setName(rolePO.getName());
        roleVO.setAlias(rolePO.getAlias());
        roleVO.setDomain(rolePO.getDomain());
        roleVO.setCategory(rolePO.getCategory());
        roleVO.setType(rolePO.getType());
        roleVO.setStatus(rolePO.getStatus());
        roleVO.setCreateTime(rolePO.getCreateTime());
        roleVO.setStatusTime(rolePO.getStatusTime());
        roleVO.setDescription(rolePO.getDescription());

        return roleVO;
    }

    /**
     * 方法：将角色VO转换成PO
     *
     * @param roleVO 角色值对象
     * @return 角色持久化对象
     */
    @Override
    public RolePO convertToRolePO(RoleVO roleVO) throws Exception
    {
        if(roleVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "roleVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        RolePO rolePO = new RolePO();

        rolePO.setRoleId(roleVO.getRoleId());
        rolePO.setName(roleVO.getName());
        rolePO.setAlias(roleVO.getAlias());
        rolePO.setDomain(roleVO.getDomain());
        rolePO.setCategory(roleVO.getCategory());
        rolePO.setType(roleVO.getType());
        rolePO.setStatus(roleVO.getStatus());
        rolePO.setCreateTime(roleVO.getCreateTime());
        rolePO.setStatusTime(roleVO.getStatusTime());
        rolePO.setDescription(roleVO.getDescription());

        return rolePO;
    }

    /**
     * 方法：将角色持久化对象转换成角色历史持久化对象
     *
     * @param recordId    操作记录编号
     * @param operateType 操作类型
     * @param rolePO      角色持久化对象
     * @return 角色历史持久化对象
     * @throws Exception 异常
     */
    @Override
    public RoleHisPO convertToRoleHisPO(Integer recordId, String operateType, RolePO rolePO) throws Exception
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

        if(rolePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "rolePO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        RoleHisPO roleHisPO = new RoleHisPO();

        roleHisPO.setRecordId(recordId);
        roleHisPO.setOperateType(operateType);
        roleHisPO.setRoleId(rolePO.getRoleId());
        roleHisPO.setName(rolePO.getName());
        roleHisPO.setAlias(rolePO.getAlias());
        roleHisPO.setDomain(rolePO.getDomain());
        roleHisPO.setCategory(rolePO.getCategory());
        roleHisPO.setType(rolePO.getType());
        roleHisPO.setStatus(rolePO.getStatus());
        roleHisPO.setCreateTime(rolePO.getCreateTime());
        roleHisPO.setStatusTime(rolePO.getStatusTime());
        roleHisPO.setDescription(rolePO.getDescription());

        return roleHisPO;
    }

    /**
     * 方法：将参与者持久化对象转换成值对象
     *
     * @param partyPO        参与者持久化对象
     * @param partyDetailPOS 参与者明细持久化对象
     * @return 参与者值对象
     * @throws Exception 异常
     */
    @Override
    public PartyVO convertToPartyVO(PartyPO partyPO, List<PartyDetailPO> partyDetailPOS) throws Exception
    {
        if(partyPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        PartyVO partyVO = new PartyVO();
        partyVO.setRecordId(Constants.BUSINESS_COMMON_OPERATION_RECORD_DEFAULT_ID);
        partyVO.setPartyId(partyPO.getPartyId());
        partyVO.setAlias(partyPO.getAlias());
        partyVO.setPassword(partyPO.getPassword());
        partyVO.setEntityId(partyPO.getEntityId());
        partyVO.setRoleId(partyPO.getRoleId());
        partyVO.setDomain(partyPO.getDomain());
        partyVO.setCategory(partyPO.getCategory());
        partyVO.setType(partyPO.getType());
        partyVO.setStatus(partyPO.getStatus());
        partyVO.setCreateTime(partyPO.getCreateTime());
        partyVO.setStatusTime(partyPO.getStatusTime());
        partyVO.setDescription(partyPO.getDescription());

        if(partyDetailPOS == null || partyDetailPOS.size() == 0)
        {
            return partyVO;
        }

        List<PartyVO.PartyDetailVO> partyDetailVOS = new ArrayList<>();

        for(PartyDetailPO partyDetailPO: partyDetailPOS)
        {
            PartyVO.PartyDetailVO partyDetailVO = new PartyVO.PartyDetailVO();
            partyDetailVO.setPartyDetailId(partyDetailPO.getPartyDetailId());
            partyDetailVO.setAttribute(partyDetailPO.getAttribute());
            partyDetailVO.setValue(partyDetailPO.getValue());
            partyDetailVO.setStatus(partyDetailPO.getStatus());
            partyDetailVO.setCreateTime(partyDetailPO.getCreateTime());
            partyDetailVO.setStatusTime(partyDetailPO.getStatusTime());
            partyDetailVO.setDescription(partyDetailPO.getDescription());

            partyDetailVOS.add(partyDetailVO);
        }

        partyVO.setPartyDetailVOS(partyDetailVOS);

        return partyVO;
    }

    /**
     * 方法：将参与者对象转换成参与者持久化对象
     *
     * @param partyVO 参与者值对象
     * @return 参与者持久化对象
     * @throws Exception 异常
     */
    @Override
    public PartyPO convertToPartyPO(PartyVO partyVO) throws Exception
    {
        if(partyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        PartyPO partyPO = new PartyPO();

        partyPO.setPartyId(partyVO.getPartyId());
        partyPO.setAlias(partyVO.getAlias());
        partyPO.setPassword(partyVO.getPassword());
        partyPO.setEntityId(partyVO.getEntityId());
        partyPO.setRoleId(partyVO.getRoleId());
        partyPO.setDomain(partyVO.getDomain());
        partyPO.setCategory(partyVO.getCategory());
        partyPO.setType(partyVO.getType());
        partyPO.setStatus(partyVO.getStatus());
        partyPO.setCreateTime(partyVO.getCreateTime());
        partyPO.setStatusTime(partyVO.getStatusTime());
        partyPO.setDescription(partyVO.getDescription());

        return partyPO;
    }

    /**
     * 方法：将参与者值对象转换成参与者明细持久化对象列表
     *
     * @param partyVO 参与者值对象
     * @return 参与者明细持久化对象列表
     * @throws Exception 异常
     */
    @Override
    public List<PartyDetailPO> convertToPartyDetailPOS(PartyVO partyVO) throws Exception
    {
        if(partyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(partyVO.getPartyDetailVOS() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyVO.getPartyDetailVOS()");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        List<PartyDetailPO> partyDetailPOS = new ArrayList<>();

        for(PartyVO.PartyDetailVO partyDetailVO: partyVO.getPartyDetailVOS())
        {
            PartyDetailPO partyDetailPO = new PartyDetailPO();

            partyDetailPO.setPartyDetailId(partyDetailVO.getPartyDetailId());
            partyDetailPO.setPartyId(partyVO.getPartyId());
            partyDetailPO.setAttribute(partyDetailVO.getAttribute());
            partyDetailPO.setValue(partyDetailVO.getValue());
            partyDetailPO.setStatus(partyDetailVO.getStatus());
            partyDetailPO.setCreateTime(partyDetailVO.getCreateTime());
            partyDetailPO.setStatusTime(partyDetailVO.getStatusTime());
            partyDetailPO.setDescription(partyDetailVO.getDescription());

            partyDetailPOS.add(partyDetailPO);
        }

        return partyDetailPOS;
    }

    /**
     * 方法：将参与者持久化对象转换成策略历史持久化对象
     *
     * @param recordId    操作记录编号
     * @param operateType 操作类型
     * @param partyPO     参与者持久化对象
     * @return 参与者历史持久化对象
     * @throws Exception 异常
     */
    @Override
    public PartyHisPO convertToPartyHisPO(Integer recordId, String operateType, PartyPO partyPO) throws Exception
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

        if(partyPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        PartyHisPO partyHisPO = new PartyHisPO();

        partyHisPO.setRecordId(recordId);
        partyHisPO.setOperateType(operateType);
        partyHisPO.setPartyId(partyPO.getPartyId());
        partyHisPO.setAlias(partyPO.getAlias());
        partyHisPO.setPassword(partyPO.getPassword());
        partyHisPO.setEntityId(partyPO.getEntityId());
        partyHisPO.setRoleId(partyPO.getRoleId());
        partyHisPO.setDomain(partyPO.getDomain());
        partyHisPO.setCategory(partyPO.getCategory());
        partyHisPO.setType(partyPO.getType());
        partyHisPO.setStatus(partyPO.getStatus());
        partyHisPO.setCreateTime(partyPO.getCreateTime());
        partyHisPO.setStatusTime(partyPO.getStatusTime());
        partyHisPO.setDescription(partyPO.getDescription());

        return partyHisPO;
    }

    /**
     * 方法：将参与者明细持久化对象转换成参与者明细历史持久化对象
     *
     * @param recordId      操作记录编号
     * @param operateType   操作类型
     * @param partyDetailPO 参与者明细持久化对象
     * @return 参与者明细历史持久化对象
     * @throws Exception 异常
     */
    @Override
    public PartyDetailHisPO convertToPartyDetailHisPO(Integer recordId, String operateType, PartyDetailPO partyDetailPO) throws Exception
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

        if(partyDetailPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyDetailPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        PartyDetailHisPO partyDetailHisPO = new PartyDetailHisPO();

        partyDetailHisPO.setRecordId(recordId);
        partyDetailHisPO.setOperateType(operateType);
        partyDetailHisPO.setPartyDetailId(partyDetailPO.getPartyDetailId());
        partyDetailHisPO.setPartyId(partyDetailPO.getPartyId());
        partyDetailHisPO.setAttribute(partyDetailPO.getAttribute());
        partyDetailHisPO.setValue(partyDetailPO.getValue());
        partyDetailHisPO.setStatus(partyDetailPO.getStatus());
        partyDetailHisPO.setCreateTime(partyDetailPO.getCreateTime());
        partyDetailHisPO.setStatusTime(partyDetailPO.getStatusTime());
        partyDetailHisPO.setDescription(partyDetailPO.getDescription());

        return partyDetailHisPO;
    }

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
