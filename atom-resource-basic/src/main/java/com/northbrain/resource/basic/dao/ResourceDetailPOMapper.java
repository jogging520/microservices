package com.northbrain.resource.basic.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.resource.basic.model.po.ResourceDetailPO;

@Mapper
@Component(value="resourceDetailPOMapper")
public interface ResourceDetailPOMapper
{
    int deleteByPrimaryKey(Integer resourceDetailId) throws Exception;

    int insert(ResourceDetailPO record) throws Exception;

    int insertSelective(ResourceDetailPO record) throws Exception;

    ResourceDetailPO selectByPrimaryKey(Integer resourceDetailId) throws Exception;

    int updateByPrimaryKeySelective(ResourceDetailPO record) throws Exception;

    int updateByPrimaryKey(ResourceDetailPO record) throws Exception;
}