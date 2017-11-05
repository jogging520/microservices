package com.northbrain.base.common.model.bo;

/**
 * 类名：常量类
 * 用途：将各系统、模块的常量集中在此类，达到统一管理的目的。
 * 命名也按照BUSINESS、STORAGE、SYSTEM来统一管理。URI资源定义以URI开头。
 * @author Jiakun
 *
 */
public class Constants 
{
	/**
	 * 这部分是一般的常量定义
	 */
	public static final long   BUSINESS_COMMON_DEFAULT_INTEVALMS 										= 30000L;								//默认的轮询时间间隔，30秒	
	public static final String BUSINESS_COMMON_TRUE 													= "TRUE";
    public static final String BUSINESS_COMMON_NODE_SEPARATOR 											= "/";									//Zookeeper的分隔符
    public static final String BUSINESS_COMMON_LOG_DEBUG_LEVEL											= "DEBUG";								//DEBUG日志级别
    public static final String BUSINESS_COMMON_LOG_ENCODING												= "UTF-8";								//日志编码格式
    public static final String BUSINESS_COMMON_LOG_APPENDER_NAME										= "common";								//LOG4J appender名称											= "DEBUG";								//DEBUG日志级别
    public static final String BUSINESS_COMMON_LOG_SEPARATOR											= ".";									//日志文件名分隔符
    public static final String BUSINESS_COMMON_LOG_SUFFIX												= "log";								//日志后缀
    public static final String BUSINESS_COMMON_LOG_DATE_PATTERN											= "'.'yyyyMMdd";						//日志的日期切断模式，每日一个日志文件
    public static final String BUSINESS_COMMON_LAUNCHER_PREFIX 											= "launcher.";							//launcher配置的前缀
    public static final String BUSINESS_COMMON_COMMAND_LINE_ASSIGN_SYMBOL								= "=";									//命令行:赋值符号
    public static final String BUSINESS_COMMON_COMMAND_LINE_PROJECT_NAME								= "projectName";						//命令行:项目名称
    public static final String BUSINESS_COMMON_COMMAND_LINE_SERVER_PORT									= "server.port";						//命令行:服务端口号（tomcat）
    public static final String BUSINESS_COMMON_COMMAND_LINE_SPRING_CONFIG_LOCATION						= "spring.config.location";				//命令行:配置文件位置
    public static final String BUSINESS_COMMON_SERVICE_GATEWAY                                          = "service-gateway";                    //服务网关
    public static final String BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS                                   = "application/json";
    public static final String BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS                                   = "application/json";

    /**
     * 微服务名称定义
     */
    public static final String BUSINESS_CONFIG_SEQUENCE_ATOM_MICROSERVICE                               = "atom-config-sequence";               //原子微服务：配置-序列号

    public static final String BUSINESS_PRODUCT_COURSE_ATOM_MICROSERVICE                                = "atom-product-course";                //原子微服务：产品-课程
    public static final String BUSINESS_PRODUCT_OPERATION_ATOM_MICROSERVICE                             = "atom-product-operation";             //原子微服务：产品-操作记录
    public static final String BUSINESS_RESOURCE_STORAGE_ATOM_MICROSERVICE                              = "atom-resource-storage";               //原子微服务：资源-存储

    public static final String BUSINESS_PRODUCT_COURSE_ORCH_MICROSERVICE                                = "orch-product-course";                //编排微服务：产品-课程


    public static final String STORAGE_ZOOKEEPER_PROJECT_NAMESPACE										= "project";							//项目的命名空间    
    public static final String STORAGE_ZOOKEEPER_GATEWAY_NAMESPACE										= "gateway";							//负载均衡（路由）：网关
    public static final String STORAGE_ZOOKEEPER_PRODUCT_NAMESPACE										= "product";							//后端：产品域命名空间
    public static final String STORAGE_ZOOKEEPER_CHARGE_NAMESPACE										= "charge";							    //后端：计费域命名空间
    public static final String STORAGE_ZOOKEEPER_RESOURCE_NAMESPACE										= "resource";							//后端：资源域命名空间
    public static final String STORAGE_ZOOKEEPER_SERVICE_NAMESPACE										= "service";							//后端：服务域命名空间
    public static final String STORAGE_ZOOKEEPER_PARTY_NAMESPACE										= "party";							    //后端：参与者域命名空间
    public static final String STORAGE_ZOOKEEPER_RELATION_NAMESPACE										= "relation";							//后端：关系域命名空间
    public static final String STORAGE_ZOOKEEPER_LIST_NAMESPACE										    = "list";							    //前端：清单域命名空间
    public static final String STORAGE_ZOOKEEPER_SEQUENCE_NAMESPACE										= "sequence";							//序列号域命名空间
    public static final String STORAGE_ZOOKEEPER_CONFIG_NAMESPACE										= "config";								//配置的命名空间
    public static final String STORAGE_ZOOKEEPER_STORAGE_NAMESPACE 										= "STORAGE";							//存储大类常量命名空间（Parameter配置）
    public static final String STORAGE_ZOOKEEPER_BUSINESS_NAMESPACE 									= "BUSINESS";							//业务大类常量命名空间（Parameter配置）
    public static final String STORAGE_ZOOKEEPER_SYSTEM_NAMESPACE 										= "SYSTEM";								//系统大类常量命名空间（Parameter配置）
    
    public static final String STORAGE_DATABASE_JDBC_DRIVER_CLASS_NAME_PROPERTY_NAME                	= "driverClassName"; 					//数据库驱动属性名
    public static final String STORAGE_DATABASE_JDBC_URL_PROPERTY_NAME                              	= "url"; 								//数据库连接串属性名
    public static final String STORAGE_DATABASE_JDBC_USER_NAME_PROPERTY_NAME                        	= "username"; 							//数据库登录用户名属性名
    public static final String STORAGE_DATABASE_JDBC_PASSWORD_PROPERTY_NAME                         	= "password"; 							//数据库登录密码属性名
    public static final String STORAGE_DATABASE_JDBC_DEFAULT_AUTO_COMMIT_PROPERTY_NAME					= "defaultAutoCommit"; 					//连接池创建的连接的默认的auto-commit属性名
    public static final String STORAGE_DATABASE_JDBC_INITIAL_SIZE_PROPERTY_NAME							= "initialSize"; 						//初始化连接属性名
    public static final String STORAGE_DATABASE_JDBC_MAX_ACTIVE_PROPERTY_NAME							= "maxActive"; 							//最大活动连接属性名
    public static final String STORAGE_DATABASE_JDBC_MAX_IDLE_PROPERTY_NAME								= "maxIdle"; 							//最大空闲连接属性名
    public static final String STORAGE_DATABASE_JDBC_MIN_IDLE_PROPERTY_NAME								= "minIdle"; 							//最小空闲连接属性名
    public static final String STORAGE_DATABASE_JDBC_MAX_WAIT_PROPERTY_NAME								= "maxWait"; 							//最大等待时间属性名
    public static final String STORAGE_DATABASE_JDBC_VALIDATION_QUERY_PROPERTY_NAME						= "validationQuery"; 					//SQL 查询， 用来验证从连接池取出的连接属性名
    public static final String STORAGE_DATABASE_JDBC_TEST_ON_BORROW_PROPERTY_NAME						= "testOnBorrow"; 						//指明是否在从池中取出连接前进行检验属性名
    public static final String STORAGE_DATABASE_JDBC_TEST_ON_RETURN_PROPERTY_NAME						= "testOnReturn"; 						//指明是否在归还到池中前进行检验属性名
    public static final String STORAGE_DATABASE_JDBC_TEST_WHILE_IDLE_PROPERTY_NAME						= "testWhileIdle"; 						//指明连接是否被空闲连接回收器( 如果有) 进行检验属性名
    public static final String STORAGE_DATABASE_JDBC_TIME_BETWEEN_EVICTION_RUNS_MILLIS_PROPERTY_NAME	= "timeBetweenEvictionRunsMillis"; 		//在空闲连接回收器线程运行期间休眠的时间值属性名
    public static final String STORAGE_DATABASE_JDBC_MIN_EVICTABLE_IDLE_TIME_MILLIS_PROPERTY_NAME		= "minEvictableIdleTimeMillis"; 		//连接在池中保持空闲而不被空闲连接回收器线程( 如果有)属性名
    public static final String STORAGE_DATABASE_JDBC_REMOVE_ABANDONED_PROPERTY_NAME						= "removeAbandoned"; 					//标记是否删除泄露的连接属性名 
    public static final String STORAGE_DATABASE_JDBC_REMOVE_ABANDONED_TIMEOUT_PROPERTY_NAME				= "removeAbandonedTimeout"; 			//泄露的连接可以被删除的超时值属性名
    public static final String STORAGE_DATABASE_JDBC_VALIDATION_INTERVAL_PROPERTY_NAME					= "validationInterval"; 				//避免过度验证属性名

    
    public static final String SYSTEM_SPRING_RESOURCE_PROPERTY_NAME 									= "resource";							//Properties配置的名称（目录）
    public static final String SYSTEM_SPRING_RESOURCE_PROPERTY_LOCATION 								= "classpath:application.properties";	//Properties配置的位置及文件名
    public static final String SYSTEM_SPRING_DATASOURCE_BEAN_NAME										= "dataSource";							//Spring boot启动数据库的数据源（名称）
    public static final String SYSTEM_SPRING_PROPERTY_APPLICATION_NAME							        = "spring.application.name";			//Spring boot属性:应用名称



    /**
     * 这部分是URI定义
     * URI_方法_域名
     * 原子服务域名包括：PRODUCT、RESOURCE、SERVICE、RELATION、PARTY、CHARGE、LOG、SECURITY、REPORT、STATISTICS、SEARCH、CONFIG、THETHIRD
     * 分别是：产品、资源、服务、关系、参与者、计费、日志、安全、报表、统计、搜索、配置（参数）、第三方
     * 编排服务域名包括：LIST、DETAIL、HOTSPOT等
     */
    public static final String URI_ATOM_PRODUCT_GET_COURSES_IN_USED                                     = "product/course";						//原子服务URI：获取全量在用的课程清单（只有课程本身）
    public static final String URI_ATOM_PRODUCT_GET_COURSE_SPECIFIED									= "product/course/{id}";				//原子服务URI：获取指定的课程（只有课程本身）
    public static final String URI_ATOM_PRODUCT_POST_OPERATION_RECORD                                   = "product/operation";					//原子服务URI：增加操作记录

    public static final String URI_ATOM_RESOURCE_GET_STORAGE_SPECIFIED                                  = "resource/storage/{id}";              //原子服务URI：获取指定的存储信息

    public static final String URI_ORCH_LIST_GET_COURSES_IN_USED                                        = "list/course";							//编排服务URI：获取全量在用的课程清单（只有课程本身）

    /**
     * 匹配布尔类型
     * @param bool 布尔值
     * @return 布尔值true或false
     */
    public static boolean matchBoolean(String bool)
    {
        if(bool == null || bool.equals(""))
            return false;

        return bool.equalsIgnoreCase(BUSINESS_COMMON_TRUE);
    }
}
