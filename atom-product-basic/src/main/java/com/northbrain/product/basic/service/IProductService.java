package com.northbrain.product.basic.service;

import com.northbrain.base.common.model.vo.basic.ServiceVO;
import com.northbrain.base.common.model.vo.atom.ProductVO;

/**
 * 类名：产品服务接口
 * 用途：封装产品等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
public interface IProductService
{
    /**
     * 方法：创建一条产品
     * @param productVO 产品值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO createProduct(ProductVO productVO);

    /**
     * 方法：更新一条产品
     * @param productVO 产品值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO updateProduct(ProductVO productVO);

    /**
     * 方法：删除一条产品
     * @param productVO 产品值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO deleteProduct(ProductVO productVO);
}
