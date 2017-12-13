package com.northbrain.party.basic.domain.impl;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.CollectionEmptyException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.OrganizationVO;
import com.northbrain.base.common.model.vo.PartyVO;
import com.northbrain.base.common.model.vo.RoleVO;
import com.northbrain.base.common.model.vo.SubjectionVO;
import com.northbrain.party.basic.dao.*;
import com.northbrain.party.basic.domain.IPartyDomain;
import com.northbrain.party.basic.dto.IPartyDTO;
import com.northbrain.party.basic.exception.OrganizationException;
import com.northbrain.party.basic.exception.PartyException;
import com.northbrain.party.basic.exception.RoleException;
import com.northbrain.party.basic.exception.SubjectionException;
import com.northbrain.party.basic.model.po.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：参与者域接口的实现类
 * 用途：实现参与者的增删改查等功能。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class PartyDomain implements IPartyDomain
{
    private static Logger logger = Logger.getLogger(PartyDomain.class);

    private final RolePOMapper rolePOMapper;
    private final RoleHisPOMapper roleHisPOMapper;
    private final PartyPOMapper partyPOMapper;
    private final PartyHisPOMapper partyHisPOMapper;
    private final PartyDetailPOMapper partyDetailPOMapper;
    private final PartyDetailHisPOMapper partyDetailHisPOMapper;
    private final OrganizationPOMapper organizationPOMapper;
    private final OrganizationHisPOMapper organizationHisPOMapper;
    private final SubjectionPOMapper subjectionPOMapper;
    private final SubjectionHisPOMapper subjectionHisPOMapper;
    private final IPartyDTO partyDTO;

    @Autowired
    public PartyDomain(RolePOMapper rolePOMapper, RoleHisPOMapper roleHisPOMapper, PartyPOMapper partyPOMapper,
                       PartyHisPOMapper partyHisPOMapper, PartyDetailPOMapper partyDetailPOMapper,
                       PartyDetailHisPOMapper partyDetailHisPOMapper, OrganizationPOMapper organizationPOMapper,
                       OrganizationHisPOMapper organizationHisPOMapper, SubjectionPOMapper subjectionPOMapper,
                       SubjectionHisPOMapper subjectionHisPOMapper, IPartyDTO partyDTO)
    {
        this.rolePOMapper = rolePOMapper;
        this.roleHisPOMapper = roleHisPOMapper;
        this.partyPOMapper = partyPOMapper;
        this.partyHisPOMapper = partyHisPOMapper;
        this.partyDetailPOMapper = partyDetailPOMapper;
        this.partyDetailHisPOMapper = partyDetailHisPOMapper;
        this.organizationPOMapper = organizationPOMapper;
        this.organizationHisPOMapper = organizationHisPOMapper;
        this.subjectionPOMapper = subjectionPOMapper;
        this.subjectionHisPOMapper = subjectionHisPOMapper;
        this.partyDTO = partyDTO;
    }

    /**
     * 方法：根据名称获取角色信息
     *
     * @param name 名称
     * @return 角色值对象
     * @throws Exception 异常
     */
    @Override
    public List<RoleVO> readRolesByName(String name) throws Exception
    {
        if(name == null || name.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + name);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(rolePOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "rolePOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        List<RolePO> rolePOS = rolePOMapper.selectByName(name);

        if(rolePOS == null || rolePOS.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "rolePOS");
            throw new CollectionEmptyException(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }

        List<RoleVO> roleVOS = new ArrayList<>();
        RoleVO roleVO;

        for(RolePO rolePO: rolePOS)
        {
            roleVO = partyDTO.convertToRoleVO(rolePO);

            if(roleVO == null)
                continue;

            roleVOS.add(roleVO);
        }

        return roleVOS;
    }

    /**
     * 方法：新建一条角色，根据RoleVO再转换成相应的PO
     *
     * @param roleVO 角色值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    @Override
    public boolean createRole(RoleVO roleVO) throws Exception
    {
        if(roleVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "roleVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(rolePOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "rolePOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(roleHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "roleHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(roleVO);

        RolePO rolePO = partyDTO.convertToRolePO(roleVO);

        if(rolePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "rolePO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(rolePOMapper.selectByPrimaryKey(rolePO.getRoleId()) == null)
        {
            if(rolePOMapper.insert(rolePO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_ROLE_INSERT + String.valueOf(rolePO.getRoleId()));
                throw new RoleException(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
            }

            RoleHisPO roleHisPO = partyDTO.convertToRoleHisPO(roleVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), rolePO);

            if(roleHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "roleHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(roleHisPOMapper.insert(roleHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_ROLE_INSERT + String.valueOf(roleHisPO.getRoleId()));
                throw new RoleException(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            }

            return true;
        }

        return false;
    }

    /**
     * 方法：更新一条角色，根据RoleVO再转换成相应的PO
     *
     * @param roleVO 角色值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public boolean updateRole(RoleVO roleVO) throws Exception
    {
        if(roleVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "roleVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(rolePOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "rolePOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(roleHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "roleHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(roleVO);

        RolePO rolePO = partyDTO.convertToRolePO(roleVO);

        if(rolePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "rolePO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(rolePOMapper.selectByPrimaryKey(rolePO.getRoleId()) == null)
        {
            if(rolePOMapper.updateByPrimaryKey(rolePO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_ROLE_UPDATE + String.valueOf(rolePO.getRoleId()));
                throw new RoleException(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
            }

            RoleHisPO roleHisPO = partyDTO.convertToRoleHisPO(roleVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), rolePO);

            if(roleHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "roleHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(roleHisPOMapper.insert(roleHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_ROLE_INSERT + String.valueOf(roleHisPO.getRoleId()));
                throw new RoleException(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            }

            return true;
        }

        return false;
    }

    /**
     * 方法：删除一条角色，根据RoleVO再转换成相应的PO
     *
     * @param roleVO 角色值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    @Override
    public boolean deleteRole(RoleVO roleVO) throws Exception
    {
        if(roleVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "roleVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(rolePOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "rolePOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(roleHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "roleHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(roleVO);

        RolePO rolePO = partyDTO.convertToRolePO(roleVO);

        if(rolePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "rolePO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(rolePOMapper.selectByPrimaryKey(rolePO.getRoleId()) == null)
        {
            if(rolePOMapper.deleteByPrimaryKey(rolePO.getRoleId()) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_ROLE_DELETE + String.valueOf(rolePO.getRoleId()));
                throw new RoleException(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
            }

            RoleHisPO roleHisPO = partyDTO.convertToRoleHisPO(roleVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), rolePO);

            if(roleHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "roleHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(roleHisPOMapper.insert(roleHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_ROLE_INSERT + String.valueOf(roleHisPO.getRoleId()));
                throw new RoleException(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            }

            return true;
        }

        return false;
    }

    /**
     * 方法：新建一条参与者，根据PartyVO再转换成相应的PO
     *
     * @param partyVO 参与者值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    @Override
    public boolean createParty(PartyVO partyVO) throws Exception
    {
        if(partyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(partyPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(partyVO);

        PartyPO partyPO = partyDTO.convertToPartyPO(partyVO);

        if(partyPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(partyPOMapper.selectByPrimaryKey(partyPO.getPartyId()) == null)
        {
            if(partyPOMapper.insert(partyPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(partyPO.getPartyId()));
                throw new PartyException(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
            }

            PartyHisPO partyHisPO = partyDTO.convertToPartyHisPO(partyVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), partyPO);

            if(partyHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(partyHisPOMapper.insert(partyHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(partyHisPO.getPartyId()));
                throw new PartyException(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
            }
        }

        if(partyVO.getPartyDetailVOS() != null && partyVO.getPartyDetailVOS().size() > 0)
        {
            List<PartyDetailPO> partyDetailPOS = partyDTO.convertToPartyDetailPOS(partyVO);

            if (partyDetailPOS == null || partyDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(PartyDetailPO partyDetailPO: partyDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(partyDetailPOMapper.selectByPrimaryKey(partyDetailPO.getPartyDetailId()) == null)
                {
                    if (partyDetailPOMapper.insert(partyDetailPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(partyDetailPO.getPartyDetailId()));
                        throw new PartyException(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
                    }

                    PartyDetailHisPO partyDetailHisPO = partyDTO.convertToPartyDetailHisPO(partyVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), partyDetailPO);

                    if(partyDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    if (partyDetailHisPOMapper.insert(partyDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(partyDetailHisPO.getPartyDetailId()));
                        throw new PartyException(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }

    /**
     * 方法：更新一条参与者，根据PartyVO再转换成相应的PO
     *
     * @param partyVO 参与者值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public boolean updateParty(PartyVO partyVO) throws Exception
    {
        if(partyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(partyPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(partyVO);

        PartyPO partyPO = partyDTO.convertToPartyPO(partyVO);

        if(partyPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(partyPOMapper.selectByPrimaryKey(partyPO.getPartyId()) == null)
        {
            if(partyPOMapper.updateByPrimaryKey(partyPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_UPDATE + String.valueOf(partyPO.getPartyId()));
                throw new PartyException(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
            }

            PartyHisPO partyHisPO = partyDTO.convertToPartyHisPO(partyVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), partyPO);

            if(partyHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(partyHisPOMapper.insert(partyHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(partyHisPO.getPartyId()));
                throw new PartyException(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
            }
        }

        if(partyVO.getPartyDetailVOS() != null && partyVO.getPartyDetailVOS().size() > 0)
        {
            List<PartyDetailPO> partyDetailPOS = partyDTO.convertToPartyDetailPOS(partyVO);

            if (partyDetailPOS == null || partyDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(PartyDetailPO partyDetailPO: partyDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(partyDetailPOMapper.selectByPrimaryKey(partyDetailPO.getPartyDetailId()) == null)
                {
                    if (partyDetailPOMapper.updateByPrimaryKey(partyDetailPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_UPDATE + String.valueOf(partyDetailPO.getPartyDetailId()));
                        throw new PartyException(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
                    }

                    PartyDetailHisPO partyDetailHisPO = partyDTO.convertToPartyDetailHisPO(partyVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), partyDetailPO);

                    if(partyDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    if (partyDetailHisPOMapper.insert(partyDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(partyDetailHisPO.getPartyDetailId()));
                        throw new PartyException(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }

    /**
     * 方法：删除一条参与者，根据PartyVO再转换成相应的PO
     *
     * @param partyVO 参与者值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    @Override
    public boolean deleteParty(PartyVO partyVO) throws Exception
    {
        if(partyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(partyPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(partyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(partyVO);

        PartyPO partyPO = partyDTO.convertToPartyPO(partyVO);

        if(partyPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(partyPOMapper.selectByPrimaryKey(partyPO.getPartyId()) == null)
        {
            if(partyPOMapper.deleteByPrimaryKey(partyPO.getPartyId()) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_DELETE + String.valueOf(partyPO.getPartyId()));
                throw new PartyException(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
            }

            PartyHisPO partyHisPO = partyDTO.convertToPartyHisPO(partyVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), partyPO);

            if(partyHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(partyHisPOMapper.insert(partyHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(partyHisPO.getPartyId()));
                throw new PartyException(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
            }
        }

        if(partyVO.getPartyDetailVOS() != null && partyVO.getPartyDetailVOS().size() > 0)
        {
            List<PartyDetailPO> partyDetailPOS = partyDTO.convertToPartyDetailPOS(partyVO);

            if (partyDetailPOS == null || partyDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(PartyDetailPO partyDetailPO: partyDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(partyDetailPOMapper.selectByPrimaryKey(partyDetailPO.getPartyDetailId()) == null)
                {
                    if (partyDetailPOMapper.deleteByPrimaryKey(partyDetailPO.getPartyDetailId()) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_DELETE + String.valueOf(partyDetailPO.getPartyDetailId()));
                        throw new PartyException(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
                    }

                    PartyDetailHisPO partyDetailHisPO = partyDTO.convertToPartyDetailHisPO(partyVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), partyDetailPO);

                    if(partyDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    if (partyDetailHisPOMapper.insert(partyDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_INSERT + String.valueOf(partyDetailHisPO.getPartyDetailId()));
                        throw new PartyException(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
                    }
                }
            }
        }

        return true;
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

        if(partyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(organizationVO);

        OrganizationPO organizationPO = partyDTO.convertToOrganizationPO(organizationVO);

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

            OrganizationHisPO organizationHisPO = partyDTO.convertToOrganizationHisPO(organizationVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), organizationPO);

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

        if(partyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(organizationVO);

        OrganizationPO organizationPO = partyDTO.convertToOrganizationPO(organizationVO);

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

            OrganizationHisPO organizationHisPO = partyDTO.convertToOrganizationHisPO(organizationVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), organizationPO);

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

        if(partyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(organizationVO);

        OrganizationPO organizationPO = partyDTO.convertToOrganizationPO(organizationVO);

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

            OrganizationHisPO organizationHisPO = partyDTO.convertToOrganizationHisPO(organizationVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), organizationPO);

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

        if(partyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(subjectionVO);

        SubjectionPO subjectionPO = partyDTO.convertToSubjectionPO(subjectionVO);

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

            SubjectionHisPO subjectionHisPO = partyDTO.convertToSubjectionHisPO(subjectionVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), subjectionPO);

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

        if(partyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(subjectionVO);

        SubjectionPO subjectionPO = partyDTO.convertToSubjectionPO(subjectionVO);

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

            SubjectionHisPO subjectionHisPO = partyDTO.convertToSubjectionHisPO(subjectionVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), subjectionPO);

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

        if(partyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(subjectionVO);

        SubjectionPO subjectionPO = partyDTO.convertToSubjectionPO(subjectionVO);

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

            SubjectionHisPO subjectionHisPO = partyDTO.convertToSubjectionHisPO(subjectionVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), subjectionPO);

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
