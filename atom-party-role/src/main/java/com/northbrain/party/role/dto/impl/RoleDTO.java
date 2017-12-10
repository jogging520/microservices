package com.northbrain.party.role.dto.impl;

import java.util.ArrayList;
import java.util.List;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.PartyVO;
import com.northbrain.base.common.model.vo.RoleVO;
import com.northbrain.party.role.dto.IRoleDTO;
import com.northbrain.party.role.model.po.*;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 类名：角色传输对象接口的实现类
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class RoleDTO implements IRoleDTO
{
    private static Logger logger = Logger.getLogger(RoleDTO.class);
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
    public PartyPO convertToStrategyPO(PartyVO partyVO) throws Exception
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
}
