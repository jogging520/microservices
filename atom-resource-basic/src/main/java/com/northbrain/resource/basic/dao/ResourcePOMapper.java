package com.northbrain.resource.basic.dao;

import com.northbrain.resource.basic.model.po.ResourcePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="resourcePOMapper")
public interface ResourcePOMapper 
{
    int deleteByPrimaryKey(Integer resourceId) throws Exception;

    int insert(ResourcePO record) throws Exception;

    int insertSelective(ResourcePO record) throws Exception;

    ResourcePO selectByPrimaryKey(Integer resourceId) throws Exception;

    int updateByPrimaryKeySelective(ResourcePO record) throws Exception;

    int updateByPrimaryKey(ResourcePO record) throws Exception;
}