package com.northbrain.product.basic.domain.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.ProductVO;
import com.northbrain.product.basic.dao.ProductDetailHisPOMapper;
import com.northbrain.product.basic.dao.ProductDetailPOMapper;
import com.northbrain.product.basic.dao.ProductHisPOMapper;
import com.northbrain.product.basic.dao.ProductPOMapper;
import com.northbrain.product.basic.domain.IProductDomain;
import com.northbrain.product.basic.dto.IProductDTO;
import com.northbrain.product.basic.exception.ProductException;
import com.northbrain.product.basic.model.po.ProductDetailHisPO;
import com.northbrain.product.basic.model.po.ProductDetailPO;
import com.northbrain.product.basic.model.po.ProductHisPO;
import com.northbrain.product.basic.model.po.ProductPO;

/**
 * 类名：产品DOMAIN接口的实现类
 * 用途：实现产品的增删改查，对DAO进行封装。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class ProductDomain implements IProductDomain
{
    private static Logger logger = Logger.getLogger(ProductDomain.class);
    private final ProductPOMapper productPOMapper;
    private final ProductHisPOMapper productHisPOMapper;
    private final ProductDetailPOMapper productDetailPOMapper;
    private final ProductDetailHisPOMapper productDetailHisPOMapper;
    private final IProductDTO productDTO;

    @Autowired
    public ProductDomain(ProductPOMapper productPOMapper, ProductHisPOMapper productHisPOMapper,
                         ProductDetailPOMapper productDetailPOMapper,
                         ProductDetailHisPOMapper productDetailHisPOMapper,
                         IProductDTO productDTO)
    {
        this.productPOMapper = productPOMapper;
        this.productHisPOMapper = productHisPOMapper;
        this.productDetailPOMapper = productDetailPOMapper;
        this.productDetailHisPOMapper = productDetailHisPOMapper;
        this.productDTO = productDTO;
    }

    /**
     * 方法：新建一条产品，根据ProductVO再转换成相应的PO
     *
     * @param productVO 产品值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    @Override
    public boolean createProduct(ProductVO productVO) throws Exception
    {
        if(productVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "productVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(productPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(productHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(productDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(productDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(productDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(productVO);

        ProductPO productPO = productDTO.convertToProductPO(productVO);

        if(productPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(productPOMapper.selectByPrimaryKey(productPO.getProductId()) == null)
        {
            if(productPOMapper.insert(productPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PRODUCT_BASIC_INSERT + String.valueOf(productPO.getProductId()));
                throw new ProductException(Errors.ERROR_BUSINESS_PRODUCT_BASIC_EXCEPTION);
            }

            ProductHisPO productHisPO = productDTO.convertToProductHisPO(productVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), productPO);

            if(productHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(productHisPOMapper.insert(productHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PRODUCT_BASIC_INSERT + String.valueOf(productHisPO.getProductId()));
                throw new ProductException(Errors.ERROR_BUSINESS_PRODUCT_BASIC_EXCEPTION);
            }
        }

        if(productVO.getProductDetailVOS() != null && productVO.getProductDetailVOS().size() > 0)
        {
            List<ProductDetailPO> productDetailPOS = productDTO.convertToProductDetailPOS(productVO);

            if (productDetailPOS == null || productDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(ProductDetailPO productDetailPO: productDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(productDetailPOMapper.selectByPrimaryKey(productDetailPO.getProductDetailId()) == null)
                {
                    if (productDetailPOMapper.insert(productDetailPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PRODUCT_BASIC_INSERT + String.valueOf(productDetailPO.getProductDetailId()));
                        throw new ProductException(Errors.ERROR_BUSINESS_PRODUCT_BASIC_EXCEPTION);
                    }

                    ProductDetailHisPO productDetailHisPO = productDTO.convertToProductDetailHisPO(productVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), productDetailPO);

                    if(productDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    if (productDetailHisPOMapper.insert(productDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PRODUCT_BASIC_INSERT + String.valueOf(productDetailHisPO.getProductDetailId()));
                        throw new ProductException(Errors.ERROR_BUSINESS_PRODUCT_BASIC_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }

    /**
     * 方法：更新一条产品，根据ProductVO再转换成相应的PO
     *
     * @param productVO 产品值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public boolean updateProduct(ProductVO productVO) throws Exception
    {
        if(productVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "productVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(productPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(productHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(productDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(productDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(productDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(productVO);

        ProductPO productPO = productDTO.convertToProductPO(productVO);

        if(productPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //更新在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(productPOMapper.selectByPrimaryKey(productPO.getProductId()) == null)
        {
            if(productPOMapper.updateByPrimaryKey(productPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PRODUCT_BASIC_UPDATE + String.valueOf(productPO.getProductId()));
                throw new ProductException(Errors.ERROR_BUSINESS_PRODUCT_BASIC_EXCEPTION);
            }

            ProductHisPO productHisPO = productDTO.convertToProductHisPO(productVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), productPO);

            if(productHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            //对于更新的也是插入历史表
            if(productHisPOMapper.insert(productHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PRODUCT_BASIC_INSERT + String.valueOf(productHisPO.getProductId()));
                throw new ProductException(Errors.ERROR_BUSINESS_PRODUCT_BASIC_EXCEPTION);
            }
        }

        if(productVO.getProductDetailVOS() != null && productVO.getProductDetailVOS().size() > 0)
        {
            List<ProductDetailPO> productDetailPOS = productDTO.convertToProductDetailPOS(productVO);

            if (productDetailPOS == null || productDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(ProductDetailPO productDetailPO: productDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(productDetailPOMapper.selectByPrimaryKey(productDetailPO.getProductDetailId()) == null)
                {
                    if (productDetailPOMapper.updateByPrimaryKey(productDetailPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PRODUCT_BASIC_UPDATE + String.valueOf(productDetailPO.getProductDetailId()));
                        throw new ProductException(Errors.ERROR_BUSINESS_PRODUCT_BASIC_EXCEPTION);
                    }

                    ProductDetailHisPO productDetailHisPO = productDTO.convertToProductDetailHisPO(productVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), productDetailPO);

                    if(productDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    //对于更新的也是插入历史表
                    if (productDetailHisPOMapper.insert(productDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PRODUCT_BASIC_INSERT + String.valueOf(productDetailHisPO.getProductDetailId()));
                        throw new ProductException(Errors.ERROR_BUSINESS_PRODUCT_BASIC_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }

    /**
     * 方法：删除一条产品，根据ProductVO再转换成相应的PO
     *
     * @param productVO 产品值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    @Override
    public boolean deleteProduct(ProductVO productVO) throws Exception
    {
        if(productVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "productVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(productPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(productHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(productDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(productDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(productDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(productVO);

        ProductPO productPO = productDTO.convertToProductPO(productVO);

        if(productPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(productPOMapper.selectByPrimaryKey(productPO.getProductId()) == null)
        {
            if(productPOMapper.deleteByPrimaryKey(productPO.getProductId()) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PRODUCT_BASIC_DELETE + String.valueOf(productPO.getProductId()));
                throw new ProductException(Errors.ERROR_BUSINESS_PRODUCT_BASIC_EXCEPTION);
            }

            ProductHisPO productHisPO = productDTO.convertToProductHisPO(productVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), productPO);

            if(productHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            //对于删除的也是插入历史表
            if(productHisPOMapper.insert(productHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_PRODUCT_BASIC_INSERT + String.valueOf(productHisPO.getProductId()));
                throw new ProductException(Errors.ERROR_BUSINESS_PRODUCT_BASIC_EXCEPTION);
            }
        }

        if(productVO.getProductDetailVOS() != null && productVO.getProductDetailVOS().size() > 0)
        {
            List<ProductDetailPO> productDetailPOS = productDTO.convertToProductDetailPOS(productVO);

            if (productDetailPOS == null || productDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(ProductDetailPO productDetailPO: productDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(productDetailPOMapper.selectByPrimaryKey(productDetailPO.getProductDetailId()) == null)
                {
                    if (productDetailPOMapper.deleteByPrimaryKey(productDetailPO.getProductDetailId()) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PRODUCT_BASIC_DELETE + String.valueOf(productDetailPO.getProductDetailId()));
                        throw new ProductException(Errors.ERROR_BUSINESS_PRODUCT_BASIC_EXCEPTION);
                    }

                    ProductDetailHisPO productDetailHisPO = productDTO.convertToProductDetailHisPO(productVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), productDetailPO);

                    if(productDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "productDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    //对于删除的也是插入历史表
                    if (productDetailHisPOMapper.insert(productDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_PRODUCT_BASIC_INSERT + String.valueOf(productDetailHisPO.getProductDetailId()));
                        throw new ProductException(Errors.ERROR_BUSINESS_PRODUCT_BASIC_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }
}
