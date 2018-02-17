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
	public static final long     BUSINESS_COMMON_DEFAULT_INTEVALMS 										    = 30000L;								        //默认的轮询时间间隔，30秒
    public static final int      BUSINESS_COMMON_BASIC_SEQUENCE                                             = 1000000000;                                   //基础的序列号
    public static final int      BUSINESS_COMMON_OPERATION_RECORD_DETAIL_ID_MULTIPLE                        = 100;                                          //操作记录明细ID号的倍数关系，明细记录由操作记录的ID乘以该值得到
    public static final int      BUSINESS_COMMON_OPERATION_RECORD_DEFAULT_ID                                = -1;                                           //操作记录默认编号
    public static final int      BUSINESS_COMMON_OPERATOR_CODE                                              = 99999999;                                     //系统默认工号
    public static final String   BUSINESS_COMMON_TRUE 													    = "TRUE";
    public static final String   BUSINESS_COMMON_NODE_SEPARATOR 											= "/";									        //Zookeeper的分隔符
    public static final String   BUSINESS_COMMON_JSON_REQUEST_DATE_FORMART                                  = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";                 //Json请求报文日期格式
    public static final String   BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART                                 = "yyyy-MM-dd HH:mm:ss.SSS";                    //Json响应报文日期格式
    public static final String   BUSINESS_COMMON_LOG_DEBUG_LEVEL											= "DEBUG";								        //DEBUG日志级别
    public static final String   BUSINESS_COMMON_LOG_ENCODING												= "UTF-8";								        //日志编码格式
    public static final String   BUSINESS_COMMON_LOG_APPENDER_NAME										    = "common";								        //LOG4J appender名称											= "DEBUG";								//DEBUG日志级别
    public static final String   BUSINESS_COMMON_LOG_SEPARATOR											    = ".";									        //日志文件名分隔符
    public static final String   BUSINESS_COMMON_LOG_SUFFIX												    = "log";								        //日志后缀
    public static final String   BUSINESS_COMMON_LOG_DATE_PATTERN											= "'.'yyyyMMdd";						        //日志的日期切断模式，每日一个日志文件
    public static final String   BUSINESS_COMMON_LAUNCHER_PREFIX 											= "launcher.";							        //launcher配置的前缀
    public static final String   BUSINESS_COMMON_COMMAND_LINE_ASSIGN_SYMBOL								    = "=";									        //命令行:赋值符号
    public static final String   BUSINESS_COMMON_COMMAND_LINE_PROJECT_NAME								    = "projectName";						        //命令行:项目名称
    public static final String   BUSINESS_COMMON_COMMAND_LINE_SERVER_PORT									= "server.port";						        //命令行:服务端口号（tomcat）
    public static final String   BUSINESS_COMMON_COMMAND_LINE_SPRING_CONFIG_LOCATION						= "spring.config.location";				        //命令行:配置文件位置
    public static final String   BUSINESS_COMMON_SERVICE_GATEWAY                                            = "base-gateway";                               //服务网关
    public static final String   BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS                                     = "application/json";                           //http请求消费类型
    public static final String   BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS                                     = "application/json";                           //http请求生产类型
    public static final String   BUSINESS_COMMON_JWT_HEADER_PARAM_AUTHORIZATION                             = "Authorization";                              //JWT的header中的认证部分
    public static final String   BUSINESS_COMMON_JWT_HEADER_PARAM_AUTHORIZATION_STARTER                     = "Bearer ";                                    //JWT的header中的认证部分的开头
    public static final String   BUSINESS_COMMON_JWT_HEADER_PARAM_TYPE                                      = "typ";                                        //JWT的header部分类型参数
    public static final String   BUSINESS_COMMON_JWT_HEADER_PARAM_TYPE_VALUE                                = "JWT";                                        //JWT的header部分类型参数取值
    public static final String   BUSINESS_COMMON_JWT_PAYLOAD_PARAM_PARTY_ID                                 = "party_id";                                   //JWT的payload部分参与者编号参数
    public static final String   BUSINESS_COMMON_JWT_PAYLOAD_PARAM_ROLE_ID                                  = "role_id";                                    //JWT的payload部分角色编号参数
    public static final String   BUSINESS_COMMON_JWT_PAYLOAD_PARAM_ORGANIZATION_ID                          = "organization_id";                            //JWT的payload部分组织机构编号参数

    public static final String[] BUSINESS_COMMON_JWT_REQUEST_URI_FILTER                                     = {"login", "registry"};                        //JWT过滤的含有登入、注册的请求
    public static final int      BUSINESS_COMMON_JWT_RESPONSE_SUCCESS                                       = 200;                                          //JWT过滤后应答成功
    public static final int      BUSINESS_COMMON_JWT_RESPONSE_UNAUTHORIZED                                  = 401;                                          //JWT过滤后应答未鉴权成功
    public static final String   BUSINESS_COMMON_JWT_RESPONSE_FILTER_RESULT                                 = "isSuccess";                                  //JWT过滤后的ZuulFilter属性，该属性决定是否进行下一个过滤


    /**
     * 微服务名称定义
     */
    public static final String   BUSINESS_COMMON_SEQUENCE_ATOM_MICROSERVICE                                 = "atom-common-sequence";                       //原子微服务：配置-序列号
    public static final String   BUSINESS_COMMON_SECURITY_ATOM_MICROSERVICE                                 = "atom-common-security";                       //原子微服务：配置-安全
    public static final String   BUSINESS_COMMON_STRATEGY_ATOM_MICROSERVICE                                 = "atom-common-strategy";                       //原子微服务：配置-策略

    public static final String   BUSINESS_PRODUCT_BASIC_ATOM_MICROSERVICE                                   = "atom-product-basic";                         //原子微服务：产品-基础
    public static final String   BUSINESS_PRODUCT_COURSE_ATOM_MICROSERVICE                                  = "atom-product-course";                        //原子微服务：产品-课程
    public static final String   BUSINESS_RESOURCE_BASIC_ATOM_MICROSERVICE                                  = "atom-resource-basic";                        //原子微服务：资源-基础
    public static final String   BUSINESS_RESOURCE_STORAGE_ATOM_MICROSERVICE                                = "atom-resource-storage";                      //原子微服务：资源-存储
    public static final String   BUSINESS_RELATION_OPERATION_RECORD_ATOM_MICROSERVICE                       = "atom-relation-operation-record";             //原子微服务：关系-操作记录
    public static final String   BUSINESS_PARTY_BASIC_ATOM_MICROSERVICE                                     = "atom-party-basic";                           //原子微服务：参与者-基础

    public static final String   BUSINESS_FOUNDATION_AUTHENTICATION_ORCH_MICROSERVICE                       = "orch-foundation-authentication";             //编排微服务：基础-鉴权
    public static final String   BUSINESS_LIST_COURSE_ORCH_MICROSERVICE                                     = "orch-list-course";                           //编排微服务：列表-课程


    public static final String   STORAGE_ZOOKEEPER_PROJECT_NAMESPACE										= "project";							        //项目的命名空间
    public static final String   STORAGE_ZOOKEEPER_GATEWAY_NAMESPACE										= "gateway";							        //负载均衡（路由）：网关
    public static final String   STORAGE_ZOOKEEPER_PRODUCT_NAMESPACE										= "product";							        //后端：产品域命名空间
    public static final String   STORAGE_ZOOKEEPER_CHARGE_NAMESPACE										    = "charge";							            //后端：计费域命名空间
    public static final String   STORAGE_ZOOKEEPER_RESOURCE_NAMESPACE										= "resource";							        //后端：资源域命名空间
    public static final String   STORAGE_ZOOKEEPER_SERVICE_NAMESPACE										= "service";							        //后端：服务域命名空间
    public static final String   STORAGE_ZOOKEEPER_PARTY_NAMESPACE										    = "party";							            //后端：参与者域命名空间
    public static final String   STORAGE_ZOOKEEPER_RELATION_NAMESPACE										= "relation";							        //后端：关系域命名空间
    public static final String   STORAGE_ZOOKEEPER_COMMON_NAMESPACE										    = "common";							            //后端：通用域命名空间
    public static final String   STORAGE_ZOOKEEPER_FOUNDATION_NAMESPACE									    = "foundation";							        //前端：基础域命名空间
    public static final String   STORAGE_ZOOKEEPER_LIST_NAMESPACE										    = "list";							            //前端：清单域命名空间
    public static final String   STORAGE_ZOOKEEPER_CONFIG_NAMESPACE										    = "config";								        //配置的命名空间
    public static final String   STORAGE_ZOOKEEPER_STORAGE_NAMESPACE 										= "STORAGE";							        //存储大类常量命名空间（Parameter配置）
    public static final String   STORAGE_ZOOKEEPER_BUSINESS_NAMESPACE 									    = "BUSINESS";							        //业务大类常量命名空间（Parameter配置）
    public static final String   STORAGE_ZOOKEEPER_SYSTEM_NAMESPACE 										= "SYSTEM";								        //系统大类常量命名空间（Parameter配置）
    public static final String   STORAGE_ZOOKEEPER_SEQUENCE_NAMESPACE 									    = "sequence";							        //序列号命名空间（也是全局的序列号载体

    public static final String   STORAGE_DATABASE_JDBC_DRIVER_CLASS_NAME_PROPERTY_NAME                	    = "driverClassName"; 					        //数据库驱动属性名
    public static final String   STORAGE_DATABASE_JDBC_URL_PROPERTY_NAME                              	    = "url"; 								        //数据库连接串属性名
    public static final String   STORAGE_DATABASE_JDBC_USER_NAME_PROPERTY_NAME                        	    = "username"; 							        //数据库登录用户名属性名
    public static final String   STORAGE_DATABASE_JDBC_PASSWORD_PROPERTY_NAME                         	    = "password"; 							        //数据库登录密码属性名
    public static final String   STORAGE_DATABASE_JDBC_DEFAULT_AUTO_COMMIT_PROPERTY_NAME					= "defaultAutoCommit"; 					        //连接池创建的连接的默认的auto-commit属性名
    public static final String   STORAGE_DATABASE_JDBC_INITIAL_SIZE_PROPERTY_NAME							= "initialSize"; 						        //初始化连接属性名
    public static final String   STORAGE_DATABASE_JDBC_MAX_ACTIVE_PROPERTY_NAME							    = "maxActive"; 							        //最大活动连接属性名
    public static final String   STORAGE_DATABASE_JDBC_MAX_IDLE_PROPERTY_NAME								= "maxIdle"; 							        //最大空闲连接属性名
    public static final String   STORAGE_DATABASE_JDBC_MIN_IDLE_PROPERTY_NAME								= "minIdle"; 							        //最小空闲连接属性名
    public static final String   STORAGE_DATABASE_JDBC_MAX_WAIT_PROPERTY_NAME								= "maxWait"; 							        //最大等待时间属性名
    public static final String   STORAGE_DATABASE_JDBC_VALIDATION_QUERY_PROPERTY_NAME						= "validationQuery"; 					        //SQL 查询， 用来验证从连接池取出的连接属性名
    public static final String   STORAGE_DATABASE_JDBC_TEST_ON_BORROW_PROPERTY_NAME						    = "testOnBorrow"; 						        //指明是否在从池中取出连接前进行检验属性名
    public static final String   STORAGE_DATABASE_JDBC_TEST_ON_RETURN_PROPERTY_NAME						    = "testOnReturn"; 						        //指明是否在归还到池中前进行检验属性名
    public static final String   STORAGE_DATABASE_JDBC_TEST_WHILE_IDLE_PROPERTY_NAME						= "testWhileIdle"; 						        //指明连接是否被空闲连接回收器( 如果有) 进行检验属性名
    public static final String   STORAGE_DATABASE_JDBC_TIME_BETWEEN_EVICTION_RUNS_MILLIS_PROPERTY_NAME	    = "timeBetweenEvictionRunsMillis"; 		        //在空闲连接回收器线程运行期间休眠的时间值属性名
    public static final String   STORAGE_DATABASE_JDBC_MIN_EVICTABLE_IDLE_TIME_MILLIS_PROPERTY_NAME		    = "minEvictableIdleTimeMillis"; 		        //连接在池中保持空闲而不被空闲连接回收器线程( 如果有)属性名
    public static final String   STORAGE_DATABASE_JDBC_REMOVE_ABANDONED_PROPERTY_NAME						= "removeAbandoned"; 					        //标记是否删除泄露的连接属性名
    public static final String   STORAGE_DATABASE_JDBC_REMOVE_ABANDONED_TIMEOUT_PROPERTY_NAME				= "removeAbandonedTimeout"; 			        //泄露的连接可以被删除的超时值属性名
    public static final String   STORAGE_DATABASE_JDBC_VALIDATION_INTERVAL_PROPERTY_NAME					= "validationInterval"; 				        //避免过度验证属性名

    
    public static final String   SYSTEM_SPRING_RESOURCE_PROPERTY_NAME 									    = "resource";							        //Properties配置的名称（目录）
    public static final String   SYSTEM_SPRING_RESOURCE_PROPERTY_LOCATION 								    = "classpath:application.properties";	        //Properties配置的位置及文件名
    public static final String   SYSTEM_SPRING_DATASOURCE_BEAN_NAME										    = "dataSource";							        //Spring boot启动数据库的数据源（名称）
    public static final String   SYSTEM_SPRING_PROPERTY_APPLICATION_NAME							        = "spring.application.name";			        //Spring boot属性:应用名称



    /**
     * 这部分是URI定义
     * URI_方法_域名
     * 原子服务域名包括：PRODUCT、RESOURCE、SERVICE、RELATION、PARTY、CHARGE、COMMON、SECURITY、REPORT、STATISTICS、SEARCH、CONFIG、THETHIRD
     * 分别是：产品、资源、服务、关系、参与者、计费、通用、安全、报表、统计、搜索、配置（参数）、第三方
     * 编排服务域名包括：LIST、DETAIL、HOTSPOT等
     */
    public static final String   URI_ATOM_PRODUCT_BASIC_REQUEST_MAPPING                                     = "/product/basic";					            //原子服务URI：产品-基础
    public static final String   URI_ATOM_PRODUCT_COURSE_REQUEST_MAPPING                                    = "/product/course";					        //原子服务URI：课程清单（只有课程本身）
    public static final String   URI_ATOM_PRODUCT_COURSE_SPECIFIED_REQUEST_MAPPING						    = "/product/course/{courseId}";			        //原子服务URI：指定的课程（只有课程本身）

    public static final String   URI_ATOM_RESOURCE_BASIC_REQUEST_MAPPING                                    = "/resource/basic";                            //原子服务URI：资源
    public static final String   URI_ATOM_RESOURCE_STORAGE_REQUEST_MAPPING                                  = "/resource/storage";                          //原子服务URI：存储信息
    public static final String   URI_ATOM_RESOURCE_STORAGE_SPECIFIED_REQUEST_MAPPING                        = "/resource/storage/{storageId}";              //原子服务URI：指定的存储信息

    public static final String   URI_ATOM_RELATION_OPERATION_RECORD_REQUEST_MAPPING                         = "/relation/operationrecord";                  //原子服务URI：操作记录

    public static final String   URI_ATOM_PARTY_BASIC_REQUEST_MAPPING                                       = "/party/basic";                               //原子服务URI：参与者
    public static final String   URI_ATOM_PARTY_ROLE_SPECIFIED_REQUEST_MAPPING                              = "/party/role/{name}";                         //原子服务URI：指定的参与者角色
    public static final String   URI_ATOM_PARTY_ROLE_REQUEST_MAPPING                                        = "/party/role";                                //原子服务URI：角色
    public static final String   URI_ATOM_PARTY_ORGANIZATION_REQUEST_MAPPING                                = "/party/organization";                        //原子服务URI：组织机构
    public static final String   URI_ATOM_PARTY_SUBJECTION_REQUEST_MAPPING                                  = "/party/subjection";                          //原子服务URI：隶属关系

    public static final String   URI_ATOM_COMMON_OPERATION_RECORD_SEQUENCE_REQUEST_MAPPING                  = "/common/sequence/operationrecord";           //原子服务URI：全局唯一的操作记录序列号
    public static final String   URI_ATOM_COMMON_REGISTRY_SEQUENCE_REQUEST_MAPPING                          = "/common/sequence/registry";                  //原子服务URI：全局唯一的注册序列号
    public static final String   URI_ATOM_COMMON_LOGIN_SEQUENCE_REQUEST_MAPPING                             = "/common/sequence/login";                     //原子服务URI：全局唯一的登录序列号
    public static final String   URI_ATOM_COMMON_PARTY_SEQUENCE_REQUEST_MAPPING                             = "/common/sequence/party";                     //原子服务URI：全局唯一的参与者序列号

    public static final String   URI_ATOM_COMMON_SECURITY_PRIVILEGE_REQUEST_MAPPING                         = "/common/security/privilege";                 //原子服务URI：安全-权限
    public static final String   URI_ATOM_COMMON_SECURITY_PRIVILEGE_SPECIFIED_REQUEST_MAPPING               = "/common/security/privilege/{privilegeId}";   //原子服务URI：指定的权限
    public static final String   URI_ATOM_COMMON_SECURITY_ACCESS_CONTROL_REQUEST_MAPPING                    = "/common/security/accesscontrol";             //原子服务URI：安全-访问控制
    public static final String   URI_ATOM_COMMON_SECURITY_LOGIN_SPECIFIED_REQUEST_MAPPING                   = "/common/security/login/{partyId}";           //原子服务URI：指定的登录信息
    public static final String   URI_ATOM_COMMON_SECURITY_LOGIN_REQUEST_MAPPING                             = "/common/security/login";                     //原子服务URI：登录
    public static final String   URI_ATOM_COMMON_SECURITY_REGISTRY_REQUEST_MAPPING                          = "/common/security/registry";                  //原子服务URI：注册
    public static final String   URI_ATOM_COMMON_SECURITY_TOKEN_REQUEST_MAPPING                             = "/common/security/token";                     //原子服务URI：JWT
    public static final String   URI_ATOM_COMMON_STRATEGY_REQUEST_MAPPING                                   = "/common/strategy";                           //原子服务URI：策略

    public static final String   URI_ORCH_FOUNDATION_AUTHENTICATION_REGISTRY_REQUEST_MAPPING                = "/foundation/authentication/registry";        //编排服务URI：基础鉴权认证-注册						        //编排服务URI：全量在用的课程清单（只有课程本身）
    public static final String   URI_ORCH_FOUNDATION_AUTHENTICATION_LOGIN_REQUEST_MAPPING                   = "/foundation/authentication/login";           //编排服务URI：基础鉴权认证-登录						        //编排服务URI：全量在用的课程清单（只有课程本身）
    public static final String   URI_ORCH_FOUNDATION_AUTHENTICATION_ACCESS_CONTROL_REQUEST_MAPPING          = "/foundation/authentication/accesscontrol";   //编排服务URI：基础鉴权认证-访问控制						        //编排服务URI：全量在用的课程清单（只有课程本身）
    public static final String   URI_ORCH_LIST_COURSE_REQUEST_MAPPING                                       = "/list/course";						        //编排服务URI：全量在用的课程清单（只有课程本身）
    public static final String   URI_ORCH_LIST_COURSE_SPECIFIED_REQUEST_MAPPING                             = "/list/course/{courseId}";			        //编排服务URI：全量在用的课程清单（只有课程本身）

    /**
     * 匹配布尔类型
     * @param bool 布尔值
     * @return 布尔值true或false
     */
    public static boolean matchBoolean(String   bool)
    {
        if(bool == null || bool.equals(""))
            return false;

        return bool.equalsIgnoreCase(BUSINESS_COMMON_TRUE);
    }
}
