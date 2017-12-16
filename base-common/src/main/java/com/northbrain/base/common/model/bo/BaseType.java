package com.northbrain.base.common.model.bo;

/**
 * 基础类型类，将常用的枚举类型及常量统一集中管理。
 * @author Jiakun
 * @version 1.0
 *
 */
public class BaseType
{
    /**
     *************************全局部分**********************
     */
    /**
     * 操作系统类型
     */
    public enum OPERATINGSYSTEM {LINUX, UNIX, WINDOWS, ZOOKEEPER}

    /**
     * 操作类型
     */
    public enum OPERATETYPE {READ, UPDATE, CREATE, DELETE}

    /**
     * 域
     */
    public enum DOMAIN {COMMON, PRODUCT, RESOURCE, CHARGE, SERVICE, PARTY, RELATION, FOUNDATION, LIST, GATEWAY}

    public enum SERVICETYPE {ATOM, ORCH, PROC}

    /**
     * 状态
     */
    public enum STATUS {INITIAL, INUSED, SUCCESS, FAILURE}
    
    /**
     * 阶段：有效、失效、同步
     */
    public enum STATE {VALID, INVALID, SYNCHING}

    /**
     * 存储结果
     */
    public enum CONTAIN {EXISTS, PARTEXISTS, NOTEXISTS, FAILURE}

    /**
     * 过滤器类型
     * pre：可以在请求被路由之前调用
     * route：在路由请求时候被调用
     * post：在route和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     */
    public enum FILTERTYPE {pre, route, post, error}

    /**
     *************************业务部分**********************
     */
    /**
     * 注册属性域
     */
    public enum REGISTRYDOMAIN {WEB, CAS, WEIXIN, APP}

    /**
     * 注册属性类别
     */
    public enum REGISTRYCATEGORY {WEB, CAS, WEIXIN, APP}

    /**
     * 注册属性类型
     */
    public enum REGISTRYTYPE {WEB, CAS, WEIXIN, APP}

    /**
     * 参与者属性域
     */
    public enum PARTYDOMAIN {WEB, CAS, WEIXIN, APP}
    /**
     * 服务类型：原子服务、编排服务、进程
     */

    /**
     * 参与者属性类别
     */
    public enum PARTYCATEGORY {WEB, CAS, WEIXIN, APP}

    /**
     * 参与者属性类型
     */
    public enum PARTYTYPE {WEB, CAS, WEIXIN, APP}

    /**
     * 匹配操作系统类型
     * @param operatingSystem
     * @return 操作系统枚举类型
     */
    public static OPERATINGSYSTEM matchOperatingSystem(String operatingSystem)
    {
        if(operatingSystem == null || operatingSystem.equals(""))
            return null;

        for(OPERATINGSYSTEM os: OPERATINGSYSTEM.values())
        {
            if(os.name().equalsIgnoreCase(operatingSystem))
                return os;
        }

        return null;
    }
}
