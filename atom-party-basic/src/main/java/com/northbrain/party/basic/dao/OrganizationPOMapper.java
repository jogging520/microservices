package com.northbrain.party.basic.dao;

import com.northbrain.party.basic.model.po.OrganizationPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="organizationPOMapper")
public interface OrganizationPOMapper 
{
    int deleteByPrimaryKey(Integer organizationId) throws Exception;

    int insert(OrganizationPO record) throws Exception;

    int insertSelective(OrganizationPO record) throws Exception;

    OrganizationPO selectByPrimaryKey(Integer organizationId) throws Exception;

    int updateByPrimaryKeySelective(OrganizationPO record) throws Exception;

    int updateByPrimaryKey(OrganizationPO record) throws Exception;
}