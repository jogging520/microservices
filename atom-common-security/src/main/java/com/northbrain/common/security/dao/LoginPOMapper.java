package com.northbrain.common.security.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.common.security.model.po.LoginPO;

@Mapper
@Component(value="loginPOMapper")
public interface LoginPOMapper {
    int deleteByPrimaryKey(Integer loginId);

    int insert(LoginPO record);

    int insertSelective(LoginPO record);

    LoginPO selectByPrimaryKey(Integer loginId);

    int updateByPrimaryKeySelective(LoginPO record);

    int updateByPrimaryKey(LoginPO record);
}