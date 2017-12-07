package com.northbrain.common.security.dao;

import com.northbrain.common.security.model.po.AccessControlPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value="accessControlPOMapper")
public interface AccessControlPOMapper
{
    int deleteByPrimaryKey(Integer accessControlId) throws Exception;

    int insert(AccessControlPO record) throws Exception;

    int insertSelective(AccessControlPO record) throws Exception;

    AccessControlPO selectByPrimaryKey(Integer accessControlId) throws Exception;

    List<AccessControlPO> selectByRole(@Param("roleId")Integer roleId) throws Exception;

    int updateByPrimaryKeySelective(AccessControlPO record) throws Exception;

    int updateByPrimaryKey(AccessControlPO record) throws Exception;
}