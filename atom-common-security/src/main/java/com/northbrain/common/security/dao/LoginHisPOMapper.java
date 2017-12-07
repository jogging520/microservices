package com.northbrain.common.security.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.common.security.model.po.LoginHisPO;

@Mapper
@Component(value="loginHisPOMapper")
public interface LoginHisPOMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(LoginHisPO record);

    int insertSelective(LoginHisPO record);

    LoginHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(LoginHisPO record);

    int updateByPrimaryKey(LoginHisPO record);
}