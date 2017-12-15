package com.northbrain.product.basic.dto;

import com.northbrain.base.common.model.vo.ProductVO;
import com.northbrain.product.basic.model.po.ProductDetailHisPO;
import com.northbrain.product.basic.model.po.ProductDetailPO;
import com.northbrain.product.basic.model.po.ProductHisPO;
import com.northbrain.product.basic.model.po.ProductPO;

import java.util.List;

/**
 * 类名：产品传输对象接口
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IProductDTO 
{
    /**
     * 方法：将产品持久化对象转换成值对象
     * @param productPO 产品持久化对象
     * @param productDetailPOS 产品明细持久化对象
     * @return 产品值对象
     * @throws Exception 异常
     */
    ProductVO convertToProductVO(ProductPO productPO, List<ProductDetailPO> productDetailPOS) throws Exception;

    /**
     * 方法：将产品值对象转换成产品持久化对象
     * @param productVO 产品值对象
     * @return 产品持久化对象
     * @throws Exception 异常
     */
    ProductPO convertToProductPO(ProductVO productVO) throws Exception;

    /**
     * 方法：将产品值对象转换成产品明细持久化对象列表
     * @param productVO 产品值对象
     * @return 产品明细持久化对象列表
     * @throws Exception 异常
     */
    List<ProductDetailPO> convertToProductDetailPOS(ProductVO productVO) throws Exception;

    /**
     * 方法：将产品持久化对象转换成产品历史持久化对象
     * @param recordId 操作记录编号
     * @param operateType 操作类型
     * @param productPO 产品持久化对象
     * @return 产品历史持久化对象
     * @throws Exception 异常
     */
    ProductHisPO convertToProductHisPO(Integer recordId, String operateType, ProductPO productPO) throws Exception;

    /**
     * 方法：将产品明细持久化对象转换成产品明细历史持久化对象
     * @param recordId 操作记录编号
     * @param operateType 操作类型
     * @param productDetailPO 产品明细持久化对象
     * @return 产品明细历史持久化对象
     * @throws Exception 异常
     */
    ProductDetailHisPO convertToProductDetailHisPO(Integer recordId, String operateType, ProductDetailPO productDetailPO) throws Exception;
}
