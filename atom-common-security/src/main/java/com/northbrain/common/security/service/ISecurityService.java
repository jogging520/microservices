package com.northbrain.common.security.service;

import com.northbrain.base.common.model.vo.atom.LoginVO;
import com.northbrain.base.common.model.vo.atom.RegistryVO;
import com.northbrain.base.common.model.vo.basic.ServiceVO;

/**
 * 类名：安全服务接口
 * 用途：封装登录、注册、权限、访问控制等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
public interface ISecurityService
{
    /**
     * 方法：获取特定的权限
     * @param privilegeId 权限编号
     * @return ServiceVO封装类
     */
    ServiceVO readPrivilege(Integer privilegeId);

    /**
     * 方法：获取特定的访问控制列表
     * @param roleId 角色编号
     * @return ServiceVO封装类
     */
    ServiceVO readAccessControlsByRole(Integer roleId);

    /**
     * 方法：获取特定的参与者登录信息列表
     * @param partyId 参与者编号
     * @return ServiceVO封装类
     */
    ServiceVO readLoginsByParty(Integer partyId);

    /**
     * 方法：新增一条注册信息（注册）
     * @param registryVO registryVO 注册信息值对象
     * @return ServiceVO封装类
     */
    ServiceVO createRegistry(RegistryVO registryVO);

    /**
     * 方法：创建一条登录信息（登录）
     * @param loginVO 登录信息值对象
     * @return ServiceVO封装类
     */
    ServiceVO createLogin(LoginVO loginVO);

    /**
     * 方法：更新一条登录信息（登出）
     * @param loginVO 登录信息值对象
     * @return ServiceVO封装类
     */
    ServiceVO updateLogin(LoginVO loginVO);
}
