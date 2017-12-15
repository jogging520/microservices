package com.northbrain.base.common.model.vo.atom;

import java.util.List;

import com.northbrain.base.common.model.vo.basic.BasicDetailVO;
import com.northbrain.base.common.model.vo.basic.BasicVO;

/**
 * 类名：产品值对象类
 * 用途：用于持久层以上的角色对象传递
 * @author Jiakun
 * @version 1.0
 */
public class ProductVO extends BasicVO
{
    //产品编号
    private Integer productId;

    //实体编号
    private Integer entityId;

    private List<ProductDetailVO> productDetailVOS;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public List<ProductDetailVO> getProductDetailVOS() {
        return productDetailVOS;
    }

    public void setProductDetailVOS(List<ProductDetailVO> productDetailVOS) {
        this.productDetailVOS = productDetailVOS;
    }

    //产品明细值对象类
    public static class ProductDetailVO extends BasicDetailVO
    {
        //产品明细编号
        private Integer productDetailId;

        public Integer getProductDetailId() {
            return productDetailId;
        }

        public void setProductDetailId(Integer productDetailId) {
            this.productDetailId = productDetailId;
        }
    }
}
