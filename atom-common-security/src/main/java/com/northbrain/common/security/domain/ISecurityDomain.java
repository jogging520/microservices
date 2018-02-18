package com.northbrain.common.security.domain;

import com.northbrain.base.common.model.vo.atom.*;

import java.util.List;
import java.util.Map;

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
     * 方法：获取特定的权限
     * @param domain 权限归属域
     * @param name 权限名称
     * @return ServiceVO封装类
     */
    List<PrivilegeVO> readPrivilegeByName(String domain, String name) throws Exception;

    /**
     * 方法：获取特定的访问控制
     * @param roleId 角色编号
     * @param organizationId 组织机构编码
     * @param domain 角色归属域
     * @param privilegeId 权限编号
     * @return 访问控制值对象列表
     * @throws Exception 异常
     */
    List<AccessControlVO> readAccessControlsByRole(Integer roleId, Integer organizationId, String domain, int privilegeId) throws Exception;

    /**
     * 方法：获取登录信息
     * @param partyId 参与者编号
     * @return 登录信息的值对象列表
     * @throws Exception 异常
     */
    List<LoginVO> readLoginsByParty(Integer partyId) throws Exception;

    /**
     * 方法：根据token中的属性判断当前的登录状态
     * @param tokenVO 令牌值对象
     * @return 登录信息的值对象列表
     * @throws Exception 异常
     */
    LoginVO readLoginByToken(TokenVO tokenVO) throws Exception;

    /**
     * 方法：获取注册信息
     * @param partyIdS 参与者编号列表
     * @return 注册信息的值对象列表
     * @throws Exception 异常
     */
    List<RegistryVO> readRegistryByParty(Integer[] partyIdS) throws Exception;

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

    /**
     * 方法：根据ID创建一条Token
     * @param tokenVO 令牌值对象
     * @return Token
     * @throws Exception 异常
     */
    String createToken(TokenVO tokenVO) throws Exception;

    /**
     * 方法：通过token信息解析并返回partyId
     * @param jsonWebToken 令牌
     * @return token令牌值对象
     * @throws Exception
     */
    TokenVO readToken(String jsonWebToken) throws Exception;
}
