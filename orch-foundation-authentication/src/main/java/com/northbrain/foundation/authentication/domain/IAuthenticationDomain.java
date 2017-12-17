package com.northbrain.foundation.authentication.domain;

import com.northbrain.base.common.model.vo.basic.ResponseVO;
import com.northbrain.base.common.model.vo.orch.OrchRegistryVO;

/**
 * 类名：鉴权DOMAIN接口
 * 用途：实现鉴权相关业务逻辑
 * @author Jiakun
 * @version 1.0
 */
public interface IAuthenticationDomain
{
    /**
     * 方法：注册
     * @param orchRegistryVO 编排层注册值对象
     * @return 是否注册成功
     */
    ResponseVO<Boolean> createRegistry(OrchRegistryVO orchRegistryVO) throws Exception;
}
