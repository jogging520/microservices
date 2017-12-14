package com.northbrain.base.common.model.bo;

/**
 * 错误编码枚举
 * 错误码编码为4为数字，分为：
 * 1XXX：系统级错误，如文件系统访问、并发执行、反射操作、SPING等错误；
 * 2XXX~3XXX：业务级错误，如配置参数、权限限制、监控、类型、数据格式等错误；业务级错误分为通用和专用两种，通用的在每一个项目中都适用，而专用的只适用于本项目。通用以ERROR_BUSINESS_COMMON_开头，错误码为2XXX，专用的以ERROR_BUSINESS_项目名称_开头，错误码为3XXX；
 * 4XXX：数据存储级（关系型数据库、NOSQL缓存、ZOOKEEPER、文件系统存储）错误，如主键冲突、连接超时等错误；
 * 5XXX：其他错误，如未知错误；
 * 各类异常增加后缀_EXCEPTION，这个跟在业务处理过程中打印的详细错误信息往往成对出现。
 * 命名以ERROR_SYSTEM、ERROR_BUSINESS、ERROR_STORAGE、ERROR_OTHER打头。
 * @author Jiakun
 * @version 1.0
 */
public enum Errors
{
	/**
	 * 0000:成功
	 */
	SUCCESS_EXECUTE											        ("0000", "成功执行。"),
	
	/**
	 * 1XXX:系统级错误，ERROR_SYSTEM_
	 */
	ERROR_SYSTEM_THREAD_POOL_EXECUTOR_EXCEPTION				        ("1101", "获取线程池执行现场异常。"),
	ERROR_SYSTEM_SPRING_ASPECT_INVOKE                               ("1201", "系统使用SPRING切面调用方法出错。"),
    ERROR_SYSTEM_PROCESS_EXCEPTION_CATCH                            ("1301", "系统捕获异常信息出错。"),
    ERROR_SYSTEM_NUMBER_FORMAT_EXCEPTION					        ("1402", "系统发生数据格式处理异常。"),
    ERROR_SYSTEM_INTERRUPTED_EXCEPTION						        ("1403", "系统发生中断异常。"),
    ERROR_SYSTEM_FILE_NOT_FOUND_EXCEPTION					        ("1404", "系统发生文件未找到异常。"),
    ERROR_SYSTEM_IO_EXCEPTION								        ("1405", "系统发生IO异常。"),
    ERROR_SYSTEM_ILLEGAL_ACCESS_EXCEPTION					        ("1406", "系统发生非法访问异常。"),
    ERROR_SYSTEM_ILLEGAL_ARGUMENT_EXCEPTION					        ("1407", "系统发生非法方法参数异常。"),
    ERROR_SYSTEM_PARSE_COMMAND_LINE                                 ("1408", "系统发生解析命令行错误。"),
    ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION                          ("1888", "系统调用服务时发生异常后熔断。"),
    ERROR_SYSTEM_ILLEGAL_STATE_EXCEPTION                            ("1999", "系统调用服务时发生无效状态异常。"),
    ERROR_SYSTEM_CLASS_CAST_EXCEPTION                               ("1997", "系统调用服务时发生类映射错误。"),
    ERROR_SYSTEM_JSON_EXCEPTION                                     ("1996", "系统调用服务时发生JSON错误。"),
    ERROR_SYSTEM_FEIGN_EXCEPTION                                    ("1995", "系统调用Feign服务时发生错误。"),
    ERROR_SYSTEM_CLIENT_EXCEPTION                                   ("1994", "系统调用Feign服务时发生client错误。"),
    /**
	 * 2XXX:通用业务级错误，ERROR_BUSINESS_COMMON_
	 */
    ERROR_BUSINESS_COMMON_PARAMETER_STATUS_EXCEPTION                ("2090", "基础配置器的状态异常。"),
    ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION		        ("2091", "基础参数配置异常。"),
    ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION			        ("2092", "参数输入异常。"),
    ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION				        ("2904", "空对象异常。"),
    ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION			        ("2093", "数值范围异常。"),
    EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION		        ("2904", "集合为空异常。"),
    ERROR_BUSINESS_COMMON_PARAMETER_CONFIG                          ("2100", "业务参数配置错误。"),
    ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL                       ("2101", "参数为空错误。"),
    ERROR_BUSINESS_COMMON_OBJECT_NULL                               ("2103", "空对象错误。"),
    ERROR_BUSINESS_COMMON_OBJECT_EMPTY						        ("2104", "选出来的数据为空："),
    ERROR_BUSINESS_COMMON_NUMBER_SCOPE          			        ("2106", "数值范围不正确。"),
    ERROR_BUSINESS_COMMON_UPDATE_PARAMETERS					        ("2105", "同步更新Paramters基础配置器时发生错误。"),
    ERROR_BUSINESS_COMMON_REMOVE_PARAMETERS					        ("2105", "同步删除Paramters基础配置器时发生错误。"),
    ERROR_BUSINESS_COMMON_COMMAND_LINE_MISSING				        ("2350", "命令行参数缺失或错误。"),
    ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE				        ("2351", "调用原子服务返回不成功。"),
    ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE_EXCEPTION             ("2352", "调用原子服务返回不成功。"),
    ERROR_BUSINESS_COMMON_OPERATION_RECORD                          ("2400", "创建或更新操作记录失败。"),
    ERROR_BUSINESS_COMMON_OPERATION_RECORD_EXCEPTION                ("2401", "创建或更新操作记录失败。"),

    /**
	 * 3XXX:专用业务级错误，ERROR_BUSINESS_XXXX_，XXXX如ONLINEEDU等
	 */
    ERROR_BUSINESS_COMMON_SECURITY_REGISTRY_EXCEPTION               ("3001", "操作注册信息时候发生异常。"),
    ERROR_BUSINESS_COMMON_SECURITY_REGISTRY_INSERT                  ("3002", "插入注册信息时候发生异常。"),
    ERROR_BUSINESS_COMMON_SECURITY_REGISTRY_UPDATE                  ("3003", "更新注册信息时候发生异常。"),
    ERROR_BUSINESS_COMMON_SECURITY_REGISTRY_DELETE                  ("3004", "删除注册信息时候发生异常。"),
    ERROR_BUSINESS_COMMON_SECURITY_LOGIN_EXCEPTION                  ("3005", "操作登录信息时候发生异常。"),
    ERROR_BUSINESS_COMMON_SECURITY_LOGIN_INSERT                     ("3006", "插入登录信息时候发生异常。"),
    ERROR_BUSINESS_COMMON_SECURITY_LOGIN_UPDATE                     ("3007", "更新登录信息时候发生异常。"),
    ERROR_BUSINESS_COMMON_SECURITY_LOGIN_DELETE                     ("3008", "删除登录信息时候发生异常。"),
    ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION               ("3009", "操作策略信息时候发生异常。"),
    ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_INSERT                  ("3010", "插入策略信息时候发生异常。"),
    ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_UPDATE                  ("3011", "更新策略信息时候发生异常。"),
    ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_DELETE                  ("3012", "删除策略信息时候发生异常。"),

    ERROR_BUSINESS_RELATION_OPERATION_RECORD_EXCEPTION              ("3101", "操作业务记录时候发生异常。"),
    ERROR_BUSINESS_RELATION_OPERATION_RECORD_INSERT                 ("3102", "插入业务记录时候发生异常。"),
    ERROR_BUSINESS_RELATION_OPERATION_RECORD_UPDATE                 ("3103", "更新业务记录时候发生异常。"),
    ERROR_BUSINESS_RELATION_OPERATION_RECORD_DELETE                 ("3104", "删除业务记录时候发生异常。"),

    ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION                     ("3111", "操作组织机构时候发生异常。"),
    ERROR_BUSINESS_PARTY_ORGANIZATION_INSERT                        ("3112", "插入组织机构时候发生异常。"),
    ERROR_BUSINESS_PARTY_ORGANIZATION_UPDATE                        ("3113", "更新组织机构时候发生异常。"),
    ERROR_BUSINESS_PARTY_ORGANIZATION_DELETE                        ("3114", "删除组织机构时候发生异常。"),
    ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION                       ("3121", "操作隶属关系时候发生异常。"),
    ERROR_BUSINESS_PARTY_SUBJECTION_INSERT                          ("3122", "插入隶属关系时候发生异常。"),
    ERROR_BUSINESS_PARTY_SUBJECTION_UPDATE                          ("3123", "更新隶属关系时候发生异常。"),
    ERROR_BUSINESS_PARTY_SUBJECTION_DELETE                          ("3124", "删除隶属关系时候发生异常。"),
    ERROR_BUSINESS_PARTY_ROLE_EXCEPTION                             ("3131", "操作角色时候发生异常。"),
    ERROR_BUSINESS_PARTY_ROLE_INSERT                                ("3132", "插入角色时候发生异常。"),
    ERROR_BUSINESS_PARTY_ROLE_UPDATE                                ("3133", "更新角色时候发生异常。"),
    ERROR_BUSINESS_PARTY_ROLE_DELETE                                ("3134", "删除角色时候发生异常。"),
    ERROR_BUSINESS_PARTY_BASIC_EXCEPTION                            ("3141", "操作参与者时候发生异常。"),
    ERROR_BUSINESS_PARTY_BASIC_INSERT                               ("3142", "插入参与者时候发生异常。"),
    ERROR_BUSINESS_PARTY_BASIC_UPDATE                               ("3143", "更新参与者时候发生异常。"),
    ERROR_BUSINESS_PARTY_BASIC_DELETE                               ("3144", "删除参与者时候发生异常。"),

    ERROR_BUSINESS_RESOURCE_BASIC_EXCEPTION                         ("3151", "操作资源时候发生异常。"),
    ERROR_BUSINESS_RESOURCE_BASIC_INSERT                            ("3152", "插入资源时候发生异常。"),
    ERROR_BUSINESS_RESOURCE_BASIC_UPDATE                            ("3153", "更新资源时候发生异常。"),
    ERROR_BUSINESS_RESOURCE_BASIC_DELETE                            ("3154", "删除资源时候发生异常。"),
    ERROR_BUSINESS_RESOURCE_STORAGE_EXCEPTION                       ("3161", "操作存储资源时候发生异常。"),
    ERROR_BUSINESS_RESOURCE_STORAGE_INSERT                          ("3162", "插入存储资源时候发生异常。"),
    ERROR_BUSINESS_RESOURCE_STORAGE_UPDATE                          ("3163", "更新存储资源时候发生异常。"),
    ERROR_BUSINESS_RESOURCE_STORAGE_DELETE                          ("3164", "删除存储资源时候发生异常。"),

    /**
	 * 4XXX:数据存储级级错误，ERROR_STORAGE_XXXX，XXXX主要有:REDIS、ZOOKEEPER、POSTGRESQL等
	 */
    ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION				        ("4001", "ZooKeeper会话连接异常："),
    ERROR_STORAGE_ZOOKEEPER_INCORRECT_DATAVERSION			        ("4050", "ZooKeeper的数据版本错误。"),
    ERROR_STORAGE_REDIS_SESSION_EXCEPTION					        ("4202", "Redis会话连接异常："),
    ERROR_STORAGE_REDIS_SESSION_NULL	                  	        ("4101", "获取Redis会话不成功。"),
    ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION	                  	    ("4333", "连接数据库或执行数据库操作时候发生异常。"),

    /**
	 * 5XXX:其他错误，如未知错误，ERROR_OTHER_
	 */    
    ERROR_OTHER_UNKNOW_EXCEPTION                                    ("5000", "其他未知错误。");
	
    private String code;
    private String desc;

    Errors(String code, String desc)
    {
        this.setCode(code);
        this.setDesc(desc);
    }

    public String getCode()
    {
        return this.code;
    }

    private void setCode(String code)
    {
        this.code = code;
    }

    public String getDesc()
    {
        return this.desc;
    }

    private void setDesc(String desc)
    {
        this.desc = desc;
    }

    @Override
    public String toString()
    {
        return "[" + this.getCode() + ":" + this.getDesc() + "]";
    }
}
