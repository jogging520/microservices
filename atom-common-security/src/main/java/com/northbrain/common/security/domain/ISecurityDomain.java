package com.northbrain.common.security.domain;

import com.northbrain.base.common.model.vo.atom.AccessControlVO;
import com.northbrain.base.common.model.vo.atom.LoginVO;
import com.northbrain.base.common.model.vo.atom.PrivilegeVO;
import com.northbrain.base.common.model.vo.atom.RegistryVO;

import java.util.List;

/**
 * 类名：安全域接口
 * 用途：实现权限、访问控制、登录、注册等功能。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface ISecurityDomain
{
    /**
     * 方法：获取特定的权限
     * @param privilegeId 权限编号
     * @return 权限值对象
     * @throws Exception 异常
     */
    PrivilegeVO readPrivilege(Integer privilegeId) throws Exception;

    /**
     * 方法：获取特定的访问控制
     * @param roleId 角色编号
     * @return 访问控制值对象列表
     * @throws Exception 异常
     */
    List<AccessControlVO> readAccessControlsByRole(Integer roleId) throws Exception;

    /**
     * 方法：获取登录信息
     * @param partyId 参与者编号
     * @return 登录信息的值对象列表
     * @throws Exception 异常
     */
    List<LoginVO> readLoginsByParty(Integer partyId) throws Exception;

    /**
     * 方法：新增一条注册信息（注册）
     * @param registryVO 注册信息值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    boolean createRegistry(RegistryVO registryVO) throws Exception;

    /**
     * 方法：创建一条登录信息（登录）
     * @param loginVO 登录信息值对象
     * @return 是否创建成功
     * @throws Exception 异常
     */
    boolean createLogin(LoginVO loginVO) throws Exception;

    /**
     * 方法：更新一条登录信息（登出）
     * @param loginVO 登录信息值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    boolean updateLogin(LoginVO loginVO) throws Exception;
}
