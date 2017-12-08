package com.northbrain.common.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.northbrain.common.security.model.po.LoginPO;

@Mapper
@Component(value="loginPOMapper")
public interface LoginPOMapper
{
    int deleteByPrimaryKey(Integer loginId) throws Exception;

    int insert(LoginPO record) throws Exception;

    int insertSelective(LoginPO record) throws Exception;

    LoginPO selectByPrimaryKey(Integer loginId) throws Exception;

    List<LoginPO> selectByParty(@Param("partyId") Integer partyId) throws Exception;

    int updateByPrimaryKeySelective(LoginPO record) throws Exception;

    int updateByPrimaryKey(LoginPO record) throws Exception;
}