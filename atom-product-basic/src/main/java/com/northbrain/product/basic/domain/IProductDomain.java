package com.northbrain.product.basic.domain;

import com.northbrain.base.common.model.vo.atom.ProductVO;

/**
 * 类名：产品DOMAIN接口
 * 用途：实现产品的增删改查，对DAO进行封装。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IProductDomain
{
    /**
     * 方法：新建一条产品，根据ProductVO再转换成相应的PO
     * @param productVO 产品值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    boolean createProduct(ProductVO productVO) throws Exception;

    /**
     * 方法：更新一条产品，根据ProductVO再转换成相应的PO
     * @param productVO 产品值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    boolean updateProduct(ProductVO productVO) throws Exception;

    /**
     * 方法：删除一条产品，根据ProductVO再转换成相应的PO
     * @param productVO 产品值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    boolean deleteProduct(ProductVO productVO) throws Exception;
}