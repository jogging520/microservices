package com.northbrain.base.gateway.domain;

import com.northbrain.base.common.model.vo.orch.OrchAccessControlVO;

/**
 * 类名：网关域接口
 * 用途：实现网管相关业务逻辑
 * @author Jiakun
 * @version 1.0
 */
public interface IGatewayDomain
{
    /**
     * 方法：访问控制
     * @param orchAccessControlVO 编排层访问控制值对象
     * @return 是否允许访问
     * @throws Exception 异常
     */
    boolean readAccessControl(OrchAccessControlVO orchAccessControlVO) throws Exception;
}
