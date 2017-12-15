package com.northbrain.product.basic.dao;

import com.northbrain.product.basic.model.po.ProductDetailPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="productDetailPOMapper")
public interface ProductDetailPOMapper 
{
    int deleteByPrimaryKey(Integer productDetailId) throws Exception;

    int insert(ProductDetailPO record) throws Exception;

    int insertSelective(ProductDetailPO record) throws Exception;

    ProductDetailPO selectByPrimaryKey(Integer productDetailId) throws Exception;

    int updateByPrimaryKeySelective(ProductDetailPO record) throws Exception;

    int updateByPrimaryKey(ProductDetailPO record) throws Exception;
}