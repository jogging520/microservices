package com.northbrain.common.security.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.common.security.model.po.LoginHisPO;

@Mapper
@Component(value="loginHisPOMapper")
public interface LoginHisPOMapper
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(LoginHisPO record) throws Exception;

    int insertSelective(LoginHisPO record) throws Exception;

    LoginHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(LoginHisPO record) throws Exception;

    int updateByPrimaryKey(LoginHisPO record) throws Exception;
}