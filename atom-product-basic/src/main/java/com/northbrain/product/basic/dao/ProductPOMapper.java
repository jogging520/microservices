package com.northbrain.product.basic.dao;

import com.northbrain.product.basic.model.po.ProductPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="productPOMapper")
public interface ProductPOMapper 
{
    int deleteByPrimaryKey(Integer productId) throws Exception;

    int insert(ProductPO record) throws Exception;

    int insertSelective(ProductPO record) throws Exception;

    ProductPO selectByPrimaryKey(Integer productId) throws Exception;

    int updateByPrimaryKeySelective(ProductPO record) throws Exception;

    int updateByPrimaryKey(ProductPO record) throws Exception;
}