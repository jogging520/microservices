package com.northbrain.resource.basic.domain;

import com.northbrain.base.common.model.vo.atom.ResourceVO;

/**
 * 类名：资源DOMAIN接口
 * 用途：实现资源的增删改查，对DAO进行封装。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IResourceDomain 
{
    /**
     * 方法：新建一条资源，根据ResourceVO再转换成相应的PO
     * @param resourceVO 资源值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    boolean createResource(ResourceVO resourceVO) throws Exception;

    /**
     * 方法：更新一条资源，根据ResourceVO再转换成相应的PO
     * @param resourceVO 资源值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    boolean updateResource(ResourceVO resourceVO) throws Exception;

    /**
     * 方法：删除一条资源，根据ResourceVO再转换成相应的PO
     * @param resourceVO 资源值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    boolean deleteResource(ResourceVO resourceVO) throws Exception;
}
