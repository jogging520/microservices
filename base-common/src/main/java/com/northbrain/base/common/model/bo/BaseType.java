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
    public enum DOMAIN {COMMON, PRODUCT, RESOURCE, CHARGE, SERVICE, PARTY, RELATION, SECURITY, FOUNDATION, LIST, GATEWAY}

    /**
     * 服务类型
     */
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
     * 可登录的ID类型
     */
    public enum IDTYPE {NAME, EMAIL}

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
     * 权限域
     */
    public enum PRIVILEGEDOMAIN {ORCHSERVICE, ATOMSERVICE}

    /**
     * 流控类型
     */
    public enum FLOWCONTROLTYPE {PROPORTION, THRESHOLD}

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

    /**
     * 方法：匹配ID类型，校验所查询的ID类型是否在定义的范围之内。
     * @param idType id类型
     * @return 是否在定义范围之内
     */
    public static boolean matchIdType(String idType)
    {
        if(idType == null || idType.equals(""))
            return false;

        for(IDTYPE id: IDTYPE.values())
        {
            if(id.name().equalsIgnoreCase(idType))
                return true;
        }

        return false;
    }
}
