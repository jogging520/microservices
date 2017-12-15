package com.northbrain.resource.basic.dto;


import com.northbrain.base.common.model.vo.ResourceVO;
import com.northbrain.resource.basic.model.po.ResourceHisPO;
import com.northbrain.resource.basic.model.po.ResourcePO;

import java.util.List;

/**
 * 类名：资源传输对象接口
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IResourceDTO
{
    /**
     * 方法：将资源持久化对象转换成值对象
     * @param resourcePO 资源持久化对象
     * @param resourceDetailPOS 资源明细持久化对象
     * @return 资源值对象
     * @throws Exception 异常
     */
    ResourceVO convertToResourceVO(ResourcePO resourcePO, List<ResourceDetailPO> resourceDetailPOS) throws Exception;

    /**
     * 方法：将资源值对象转换成资源持久化对象
     * @param resourceVO 资源值对象
     * @return 资源持久化对象
     * @throws Exception 异常
     */
    ResourcePO convertToResourcePO(ResourceVO resourceVO) throws Exception;

    /**
     * 方法：将资源值对象转换成资源明细持久化对象列表
     * @param resourceVO 资源值对象
     * @return 资源明细持久化对象列表
     * @throws Exception 异常
     */
    List<ResourceDetailPO> convertToResourceDetailPOS(ResourceVO resourceVO) throws Exception;

    /**
     * 方法：将资源持久化对象转换成资源历史持久化对象
     * @param recordId 操作记录编号
     * @param operateType 操作类型
     * @param resourcePO 资源持久化对象
     * @return 资源历史持久化对象
     * @throws Exception 异常
     */
    ResourceHisPO convertToResourceHisPO(Integer recordId, String operateType, ResourcePO resourcePO) throws Exception;

    /**
     * 方法：将资源明细持久化对象转换成资源明细历史持久化对象
     * @param recordId 操作记录编号
     * @param operateType 操作类型
     * @param resourceDetailPO 资源明细持久化对象
     * @return 资源明细历史持久化对象
     * @throws Exception 异常
     */
    ResourceDetailHisPO convertToResourceDetailHisPO(Integer recordId, String operateType, ResourceDetailPO resourceDetailPO) throws Exception;
}
