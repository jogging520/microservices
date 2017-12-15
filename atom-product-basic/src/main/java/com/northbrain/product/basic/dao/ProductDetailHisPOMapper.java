package com.northbrain.product.basic.dao;

import com.northbrain.product.basic.model.po.ProductDetailHisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="productDetailHisPOMapper")
public interface ProductDetailHisPOMapper
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(ProductDetailHisPO record) throws Exception;

    int insertSelective(ProductDetailHisPO record) throws Exception;

    ProductDetailHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(ProductDetailHisPO record) throws Exception;

    int updateByPrimaryKey(ProductDetailHisPO record) throws Exception;
}