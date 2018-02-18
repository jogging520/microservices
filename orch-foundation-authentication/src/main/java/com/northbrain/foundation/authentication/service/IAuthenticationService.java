package com.northbrain.foundation.authentication.service;

import com.northbrain.base.common.model.vo.basic.ServiceVO;
import com.northbrain.base.common.model.vo.orch.OrchAccessControlVO;
import com.northbrain.base.common.model.vo.orch.OrchLoginVO;
import com.northbrain.base.common.model.vo.orch.OrchRegistryVO;
import com.northbrain.base.common.model.vo.orch.OrchStrategyVO;

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

    /**
     * 方法：登录
     *
     * @param orchLoginVO 编排层登录值对象
     * @return token的ServiceVO封装对象
     */
    ServiceVO createLogin(OrchLoginVO orchLoginVO);

    /**
     * 方法：访问控制
     *
     * @param orchAccessControlVO 编排层访问控制值对象
     * @return 是否允许访问的ServiceVO封装对象
     */
    ServiceVO readAccessControl(OrchAccessControlVO orchAccessControlVO);

    /**
     * 方法：读取策略
     * @param orchStrategyVO 编排层策略值对象
     * @return 策略清单的ServiceVO封装对象
     */
    ServiceVO readStrategiesByName(OrchStrategyVO orchStrategyVO);
}
