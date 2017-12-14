package com.northbrain.resource.basic.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.resource.basic.model.po.ResourceDetailHisPO;

@Mapper
@Component(value="resourceDetailHisPOMapper")
public interface ResourceDetailHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(ResourceDetailHisPO record) throws Exception;

    int insertSelective(ResourceDetailHisPO record) throws Exception;

    ResourceDetailHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(ResourceDetailHisPO record) throws Exception;

    int updateByPrimaryKey(ResourceDetailHisPO record) throws Exception;
}