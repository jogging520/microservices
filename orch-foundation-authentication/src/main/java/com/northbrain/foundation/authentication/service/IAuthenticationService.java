package com.northbrain.foundation.authentication.service;

import com.northbrain.base.common.model.vo.basic.ServiceVO;
import com.northbrain.base.common.model.vo.orch.OrchRegistryVO;

/**
 * 类名：鉴权服务接口
 * 用途：操作鉴权、权限、注册、登录等相关操作。
 * @author Jiakun
 * @version 1.0
 */
public interface IAuthenticationService
{
    /**
     * 方法：注册
     *
     * @param orchRegistryVO 编排层注册值对象
     * @return 是否注册成功的ServiceVO封装对象
     */
    ServiceVO createRegistry(OrchRegistryVO orchRegistryVO);
}
