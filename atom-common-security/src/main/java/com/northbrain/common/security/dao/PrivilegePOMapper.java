package com.northbrain.common.security.dao;

import java.util.List;

import com.northbrain.common.security.model.po.PrivilegePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="privilegePOMapper")
public interface PrivilegePOMapper
{
    int deleteByPrimaryKey(Integer privilegeId) throws Exception;

    int insert(PrivilegePO record) throws Exception;

    int insertSelective(PrivilegePO record) throws Exception;

    PrivilegePO selectByPrimaryKey(Integer privilegeId) throws Exception;

    List<PrivilegePO> selectByName(@Param("domain") String domain,
                                   @Param("name") String name) throws Exception;

    int updateByPrimaryKeySelective(PrivilegePO record) throws Exception;

    int updateByPrimaryKey(PrivilegePO record) throws Exception;
}