package com.northbrain.foundation.authentication.domain;

import java.util.List;

import com.northbrain.base.common.model.vo.atom.StrategyVO;
import com.northbrain.base.common.model.vo.basic.ResponseVO;
import com.northbrain.base.common.model.vo.orch.OrchAccessControlVO;
import com.northbrain.base.common.model.vo.orch.OrchLoginVO;
import com.northbrain.base.common.model.vo.orch.OrchRegistryVO;
import com.northbrain.base.common.model.vo.orch.OrchStrategyVO;

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

    /**
     * 方法：登入
     * @param orchLoginVO   登录值对象
     * @return 如果登入成功，那么返回token，否则为空
     * @throws Exception 异常
     */
    ResponseVO<String> createLogin(OrchLoginVO orchLoginVO) throws Exception;

    /**
     * 方法：访问控制
     * @param orchAccessControlVO 编排层访问控制值对象
     * @return 是否运行访问控制
     * @throws Exception 异常
     */
    ResponseVO<Boolean> readAccessControl(OrchAccessControlVO orchAccessControlVO) throws Exception;

    /**
     * 方法：读取策略
     * @param orchStrategyVO 编排层策略值对象
     * @return 策略清单
     * @throws Exception 异常
     */
    ResponseVO<List<StrategyVO>> readStrategiesByName(OrchStrategyVO orchStrategyVO) throws Exception;
}
