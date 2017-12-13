package com.northbrain.party.role.domain.impl;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.CollectionEmptyException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.PartyVO;
import com.northbrain.base.common.model.vo.RoleVO;
import com.northbrain.party.role.dao.*;
import com.northbrain.party.role.domain.IRoleDomain;
import com.northbrain.party.role.dto.IRoleDTO;
import com.northbrain.party.role.exception.RoleException;
import com.northbrain.party.role.model.po.RoleHisPO;
import com.northbrain.party.role.model.po.RolePO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：角色域接口的实现类
 * 用途：实现角色的增删改查等功能。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class RoleDomain implements IRoleDomain
{
    private static Logger logger = Logger.getLogger(RoleDomain.class);

    private final RolePOMapper rolePOMapper;
    private final RoleHisPOMapper roleHisPOMapper;
    private final PartyPOMapper partyPOMapper;
    private final PartyHisPOMapper partyHisPOMapper;
    private final PartyDetailPOMapper partyDetailPOMapper;
    private final PartyDetailHisPOMapper partyDetailHisPOMapper;
    private final IRoleDTO roleDTO;

    @Autowired
    public RoleDomain(RolePOMapper rolePOMapper, RoleHisPOMapper roleHisPOMapper,
                      PartyPOMapper partyPOMapper, PartyHisPOMapper partyHisPOMapper,
                      PartyDetailPOMapper partyDetailPOMapper, PartyDetailHisPOMapper partyDetailHisPOMapper,
                      IRoleDTO roleDTO)
    {
        this.rolePOMapper = rolePOMapper;
        this.roleHisPOMapper = roleHisPOMapper;
        this.partyPOMapper = partyPOMapper;
        this.partyHisPOMapper = partyHisPOMapper;
        this.partyDetailPOMapper = partyDetailPOMapper;
        this.partyDetailHisPOMapper = partyDetailHisPOMapper;
        this.roleDTO = roleDTO;
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

        if(roleDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "roleDTO");
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
            roleVO = roleDTO.convertToRoleVO(rolePO);

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

        if(roleDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "roleDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(roleVO);

        RolePO rolePO = roleDTO.convertToRolePO(roleVO);

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

            RoleHisPO roleHisPO = roleDTO.convertToRoleHisPO(roleVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), rolePO);

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

        if(roleDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "roleDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(roleVO);

        RolePO rolePO = roleDTO.convertToRolePO(roleVO);

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

            RoleHisPO roleHisPO = roleDTO.convertToRoleHisPO(roleVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), rolePO);

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

        if(roleDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "roleDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(roleVO);

        RolePO rolePO = roleDTO.convertToRolePO(roleVO);

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

            RoleHisPO roleHisPO = roleDTO.convertToRoleHisPO(roleVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), rolePO);

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

        StrategyPO partyPO = partyDTO.convertToStrategyPO(partyVO);

        if(partyPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(partyPOMapper.selectByPrimaryKey(partyPO.getStrategyId()) == null)
        {
            if(partyPOMapper.insert(partyPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_INSERT + String.valueOf(partyPO.getStrategyId()));
                throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
            }

            StrategyHisPO partyHisPO = partyDTO.convertToStrategyHisPO(partyVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), partyPO);

            if(partyHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(partyHisPOMapper.insert(partyHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_INSERT + String.valueOf(partyHisPO.getStrategyId()));
                throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
            }
        }

        if(partyVO.getStrategyDetailVOS() != null && partyVO.getStrategyDetailVOS().size() > 0)
        {
            List<StrategyDetailPO> partyDetailPOS = partyDTO.convertToStrategyDetailPOS(partyVO);

            if (partyDetailPOS == null || partyDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(StrategyDetailPO partyDetailPO: partyDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(partyDetailPOMapper.selectByPrimaryKey(partyDetailPO.getStrategyDetailId()) == null)
                {
                    if (partyDetailPOMapper.insert(partyDetailPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_INSERT + String.valueOf(partyDetailPO.getStrategyDetailId()));
                        throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
                    }

                    StrategyDetailHisPO partyDetailHisPO = partyDTO.convertToStrategyDetailHisPO(partyVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), partyDetailPO);

                    if(partyDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    if (partyDetailHisPOMapper.insert(partyDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_INSERT + String.valueOf(partyDetailHisPO.getStrategyDetailId()));
                        throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
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
        return false;
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
        return false;
    }
}
