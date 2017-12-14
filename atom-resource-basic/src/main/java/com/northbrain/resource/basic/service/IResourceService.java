package com.northbrain.resource.basic.service;

import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.base.common.model.vo.ResourceVO;

/**
 * 类名：资源服务接口
 * 用途：封装资源等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
public interface IResourceService
{
    /**
     * 方法：创建一条资源
     * @param resourceVO 资源值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO createResource(ResourceVO resourceVO);

    /**
     * 方法：更新一条资源
     * @param resourceVO 资源值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO updateResource(ResourceVO resourceVO);

    /**
     * 方法：删除一条资源
     * @param resourceVO 资源值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO deleteResource(ResourceVO resourceVO);
}
