package com.northbrain.product.basic.dto.impl;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.atom.ProductVO;
import com.northbrain.product.basic.dto.IProductDTO;
import com.northbrain.product.basic.model.po.ProductDetailHisPO;
import com.northbrain.product.basic.model.po.ProductDetailPO;
import com.northbrain.product.basic.model.po.ProductHisPO;
import com.northbrain.product.basic.model.po.ProductPO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：产品传输对象接口的实现类
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class ProductDTO implements IProductDTO
{
    private static Logger logger = Logger.getLogger(ProductDTO.class);

    /**
     * 方法：将产品持久化对象转换成值对象
     *
     * @param productPO        产品持久化对象
     * @param productDetailPOS 产品明细持久化对象
     * @return 产品值对象
     * @throws Exception 异常
     */
    @Override
    public ProductVO convertToProductVO(ProductPO productPO, List<ProductDetailPO> productDetailPOS) throws Exception
    {
        if(productPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "productPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        ProductVO productVO = new ProductVO();
        productVO.setRecordId(Constants.BUSINESS_COMMON_OPERATION_RECORD_DEFAULT_ID);
        productVO.setProductId(productPO.getProductId());
        productVO.setEntityId(productPO.getEntityId());
        productVO.setDomain(productPO.getDomain());
        productVO.setCategory(productPO.getCategory());
        productVO.setType(productPO.getType());
        productVO.setStatus(productPO.getStatus());
        productVO.setCreateTime(productPO.getCreateTime());
        productVO.setStatusTime(productPO.getStatusTime());
        productVO.setDescription(productPO.getDescription());

        if(productDetailPOS == null || productDetailPOS.size() == 0)
        {
            return productVO;
        }

        List<ProductVO.ProductDetailVO> productDetailVOS = new ArrayList<>();

        for(ProductDetailPO productDetailPO: productDetailPOS)
        {
            ProductVO.ProductDetailVO productDetailVO = new ProductVO.ProductDetailVO();

            productDetailVO.setProductDetailId(productDetailPO.getProductDetailId());
            productDetailVO.setAttribute(productDetailPO.getAttribute());
            productDetailVO.setValue(productDetailPO.getValue());
            productDetailVO.setStatus(productDetailPO.getStatus());
            productDetailVO.setCreateTime(productDetailPO.getCreateTime());
            productDetailVO.setStatusTime(productDetailPO.getStatusTime());
            productDetailVO.setDescription(productDetailPO.getDescription());

            productDetailVOS.add(productDetailVO);
        }

        productVO.setProductDetailVOS(productDetailVOS);

        return productVO;
    }

    /**
     * 方法：将产品值对象转换成产品持久化对象
     *
     * @param productVO 产品值对象
     * @return 产品持久化对象
     * @throws Exception 异常
     */
    @Override
    public ProductPO convertToProductPO(ProductVO productVO) throws Exception
    {
        if(productVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "productVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        ProductPO productPO = new ProductPO();
        productPO.setProductId(productVO.getProductId());
        productPO.setEntityId(productVO.getEntityId());
        productPO.setDomain(productVO.getDomain());
        productPO.setCategory(productVO.getCategory());
        productPO.setType(productVO.getType());
        productPO.setStatus(productVO.getStatus());
        productPO.setCreateTime(productVO.getCreateTime());
        productPO.setStatusTime(productVO.getStatusTime());
        productPO.setDescription(productVO.getDescription());

        return productPO;
    }

    /**
     * 方法：将产品值对象转换成产品明细持久化对象列表
     *
     * @param productVO 产品值对象
     * @return 产品明细持久化对象列表
     * @throws Exception 异常
     */
    @Override
    public List<ProductDetailPO> convertToProductDetailPOS(ProductVO productVO) throws Exception
    {
        if(productVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "productVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(productVO.getProductDetailVOS() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "productVO.getProductDetailVOS()");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        List<ProductDetailPO> productDetailPOS = new ArrayList<>();

        for(ProductVO.ProductDetailVO productDetailVO: productVO.getProductDetailVOS())
        {
            ProductDetailPO productDetailPO = new ProductDetailPO();

            productDetailPO.setProductDetailId(productDetailVO.getProductDetailId());
            productDetailPO.setProductId(productVO.getProductId());
            productDetailPO.setAttribute(productDetailVO.getAttribute());
            productDetailPO.setValue(productDetailVO.getValue());
            productDetailPO.setStatus(productDetailVO.getStatus());
            productDetailPO.setCreateTime(productDetailVO.getCreateTime());
            productDetailPO.setStatusTime(productDetailVO.getStatusTime());
            productDetailPO.setDescription(productDetailVO.getDescription());

            productDetailPOS.add(productDetailPO);
        }

        return productDetailPOS;
    }

    /**
     * 方法：将产品持久化对象转换成产品历史持久化对象
     *
     * @param recordId    操作记录编号
     * @param operateType 操作类型
     * @param productPO  产品持久化对象
     * @return 产品历史持久化对象
     * @throws Exception 异常
     */
    @Override
    public ProductHisPO convertToProductHisPO(Integer recordId, String operateType,
                                                ProductPO productPO) throws Exception
    {
        if(recordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "recordId:" + recordId);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(operateType == null || operateType.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operateType");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(productPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "productPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        ProductHisPO productHisPO = new ProductHisPO();
        productHisPO.setRecordId(recordId);
        productHisPO.setOperateType(operateType);
        productHisPO.setProductId(productPO.getProductId());
        productHisPO.setEntityId(productPO.getEntityId());
        productHisPO.setDomain(productPO.getDomain());
        productHisPO.setCategory(productPO.getCategory());
        productHisPO.setType(productPO.getType());
        productHisPO.setStatus(productPO.getStatus());
        productHisPO.setCreateTime(productPO.getCreateTime());
        productHisPO.setStatusTime(productPO.getStatusTime());
        productHisPO.setDescription(productPO.getDescription());

        return productHisPO;
    }

    /**
     * 方法：将产品明细持久化对象转换成产品明细历史持久化对象
     *
     * @param recordId         操作记录编号
     * @param operateType      操作类型
     * @param productDetailPO 产品明细持久化对象
     * @return 产品明细历史持久化对象
     * @throws Exception 异常
     */
    @Override
    public ProductDetailHisPO convertToProductDetailHisPO(Integer recordId, String operateType,
                                                            ProductDetailPO productDetailPO) throws Exception
    {
        if(recordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "recordId:" + recordId);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(operateType == null || operateType.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operateType");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(productDetailPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "productDetailPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        ProductDetailHisPO productDetailHisPO = new ProductDetailHisPO();

        productDetailHisPO.setRecordId(recordId);
        productDetailHisPO.setOperateType(operateType);
        productDetailHisPO.setProductDetailId(productDetailPO.getProductDetailId());
        productDetailHisPO.setProductId(productDetailPO.getProductId());
        productDetailHisPO.setAttribute(productDetailPO.getAttribute());
        productDetailHisPO.setValue(productDetailPO.getValue());
        productDetailHisPO.setStatus(productDetailPO.getStatus());
        productDetailHisPO.setCreateTime(productDetailPO.getCreateTime());
        productDetailHisPO.setStatusTime(productDetailPO.getStatusTime());
        productDetailHisPO.setDescription(productDetailPO.getDescription());

        return productDetailHisPO;
    }
}
