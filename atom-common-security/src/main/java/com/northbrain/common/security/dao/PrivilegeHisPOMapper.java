package com.northbrain.common.security.dao;

import com.northbrain.common.security.model.po.PrivilegeHisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="privilegeHisPOMapper")
public interface PrivilegeHisPOMapper
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(PrivilegeHisPO record) throws Exception;

    int insertSelective(PrivilegeHisPO record) throws Exception;

    PrivilegeHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(PrivilegeHisPO record) throws Exception;

    int updateByPrimaryKey(PrivilegeHisPO record) throws Exception;
}