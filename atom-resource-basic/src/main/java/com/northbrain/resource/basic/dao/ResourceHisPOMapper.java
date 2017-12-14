package com.northbrain.resource.basic.dao;

import com.northbrain.resource.basic.model.po.ResourceHisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="resourceHisPOMapper")
public interface ResourceHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(ResourceHisPO record) throws Exception;

    int insertSelective(ResourceHisPO record) throws Exception;

    ResourceHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(ResourceHisPO record) throws Exception;

    int updateByPrimaryKey(ResourceHisPO record) throws Exception;
}