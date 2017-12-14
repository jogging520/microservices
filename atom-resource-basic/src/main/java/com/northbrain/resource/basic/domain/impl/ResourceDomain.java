package com.northbrain.resource.basic.domain.impl;

import com.northbrain.base.common.model.vo.ResourceVO;
import com.northbrain.resource.basic.domain.IResourceDomain;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 类名：资源DOMAIN接口的实现类
 * 用途：实现资源的增删改查，对DAO进行封装。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class ResourceDomain implements IResourceDomain
{
    private static Logger logger = Logger.getLogger(ResourceDomain.class);
    /**
     * 方法：新建一条资源，根据ResourceVO再转换成相应的PO
     *
     * @param resourceVO 资源值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    @Override
    public boolean createResource(ResourceVO resourceVO) throws Exception
    {
        return false;
    }

    /**
     * 方法：更新一条资源，根据ResourceVO再转换成相应的PO
     *
     * @param resourceVO 资源值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public boolean updateResource(ResourceVO resourceVO) throws Exception
    {
        return false;
    }

    /**
     * 方法：删除一条资源，根据ResourceVO再转换成相应的PO
     *
     * @param resourceVO 资源值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    @Override
    public boolean deleteResource(ResourceVO resourceVO) throws Exception
    {
        return false;
    }
}
