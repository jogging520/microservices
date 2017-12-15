package com.northbrain.product.basic.dao;

import com.northbrain.product.basic.model.po.ProductHisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="productHisPOMapper")
public interface ProductHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(ProductHisPO record) throws Exception;

    int insertSelective(ProductHisPO record) throws Exception;

    ProductHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(ProductHisPO record) throws Exception;

    int updateByPrimaryKey(ProductHisPO record) throws Exception;
}