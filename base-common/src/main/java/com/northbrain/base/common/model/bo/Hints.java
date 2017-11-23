package com.northbrain.base.common.model.bo;

/**
 * 提示枚举
 * HINT_SYSTEM：系统级提示，如文件系统访问、并发执行、反射操作、SPING等提示；
 * HINT_BUSINESS：业务级提示，如配置参数、权限限制、监控、类型、数据格式等提示；通用的增加COMMON，专用的增加相应的项目名称；
 * HINT_STORAGE：数据存储级（关系型数据库、NOSQL缓存、ZOOKEEPER、文件系统）提示，如队列等待数等提示；
 * @author Jiakun
 * @version 1.0
 */
public enum Hints
{
    /**
     * HINT_SYSTEM：系统级提示
     */
	HINT_SYSTEM_PROCESS_BEGIN_INVOKE_METHOD                     ("==>开始调用方法："),
    HINT_SYSTEM_PROCESS_END_INVOKE_METHOD                       ("<==结束调用方法："),
    HINT_SYSTEM_PROCESS_INVOKE_METHOD_COST                      ("耗时(ms)："),
    HINT_SYSTEM_PROCESS_SYSTEM_STARTUP                          ("系统启动......."),
    HINT_SYSTEM_PROCESS_SYSTEM_SHUTDOWN                         ("系统退出......."),
    HINT_SYSTEM_PROCESS_ESTABLISH_THREAD_POOL                   ("创建线程池。"),
    HINT_SYSTEM_PROCESS_THREAD_POOL_EXECUTE_TASK				("线程池线程执行任务。"),
    HINT_SYSTEM_PROCESS_THREAD_SLEEP							("当前线程休眠："),
    HINT_SYSTEM_PROCESS_CALL_CONTROLLER                         ("开始调用控制层方法："),
    HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO                        ("调用原子服务时发生熔断。"),

    HINT_SYSTEM_SPRING_BEAN_DEFINITION_REGISTRY					("动态注册bean。"),
    
    HINT_SYSTEM_MONITOR_FREE_MEMORY_INFO                        ("当前JVM剩余内存量："),

    /**
     * HINT_BUSINESS_COMMON：通用业务级提示
     */
    HINT_BUSINESS_COMMON_COMMAND_LINE_ARGUMENT					("系统启动命令行参数："),
    HINT_BUSINESS_COMMON_DYNAMIC_ADD_REMOTE_PROPERTIES			("初始化bean前，动态增加远端（zk服务器）提供的属性。"),
    HINT_BUSINESS_COMMON_DYNAMIC_ADD_LOCAL_PARAMETERS			("初始化配置，从application.properties初始化launcher参数。"),
    HINT_BUSINESS_COMMON_LOCAL_PARAMETER_VALUE					("properties配置的参数和取值："),
    HINT_BUSINESS_COMMON_ADD_PARAMETER_ITEM						("增加Parameters基础配置器项目："),
    HINT_BUSINESS_COMMON_GET_PARAMETER_ITEM_VALUE				("获取Parameters基础配置器项目的取值："),
    HINT_BUSINESS_COMMON_CHECK_PARAMETER_IS_EXISTS				("检查Parameters基础配置器项目是否存在："),
    HINT_BUSINESS_COMMON_REMOVE_PARAMETER_ITEM					("移除Parameters基础配置器项目："),
    HINT_BUSINESS_COMMON_UPDATE_PARAMETER_ITEM					("更新Parameters基础配置器项目："),
    HINT_BUSINESS_COMMON_SEQUENCE_NEXT_VALUE					("获取到的序列号为："),
    HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST					("接收到http请求     ："),
    HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_URL				("======URL         ："),
    HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_HTTP_METHOD		("======HTTP_METHOD ："),
    HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_IP				("======IP          ："),
    HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_CLASS_METHOD		("======CLASS_METHOD："),
    HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_ARGS				("======ARGS        ："),
    HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_PARA_NAME			("======PARA_NAME   ："),
    HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_AFTER_RETURNING	("http请求已经完成响应。总耗时(ms)："),


    /**
     * HINT_BUSINESS_XXXX：专用业务级提示，XXXX如LIST、DETAIL等
     */
    
    
    /**
     * HINT_STORAGE_XXXX：数据存储级提示，XXXX如ZOOKEEPER、REDIS、POSTGRESQL等
     */
    HINT_STORAGE_ZOOKEEPER_CONNECT_SERVER                       ("连接ZooKeeper服务。"),
    HINT_STORAGE_ZOOKEEPER_DOMAIN_NAMESPACE                     ("域命名空间为："),
    HINT_STORAGE_ZOOKEEPER_CONNECT_SESSION                      ("开始建立ZK会话。"),
    HINT_STORAGE_ZOOKEEPER_CLOSE_SESSION                        ("关闭ZooKeeper会话。"),
    HINT_STORAGE_ZOOKEEPER_NODE_PATH_INFO                       ("ZooKeeper节点路径信息（命名空间、节点名）："),
    HINT_STORAGE_ZOOKEEPER_CREATE_NODE                          ("创建ZooKeeper节点："),
    HINT_STORAGE_ZOOKEEPER_DELETE_NODE                          ("删除ZooKeeper节点："),
    HINT_STORAGE_ZOOKEEPER_NULL_NODE                            ("ZooKeeper节点为空。"),
    HINT_STORAGE_ZOOKEEPER_DISCONNECT                           ("未建立ZooKeeper连接或者连接已经断开。"),
    HINT_STORAGE_ZOOKEEPER_CHECK_IS_EXISTS                      ("检查ZooKeeper节点是否存在"),
    HINT_STORAGE_ZOOKEEPER_ADD_CACHE                            ("增加同步Cache："),
    HINT_STORAGE_ZOOKEEPER_GET_CHILD_NODE						("获取ZooKeeper节点的所有子节点列表："					),		
    HINT_STORAGE_ZOOKEEPER_GET_NODE_DATA						("获取ZooKeeper的节点取值："),
    HINT_STORAGE_ZOOKEEPER_UPDATE_NODE_DATA						("更新ZooKeeper的节点取值："),
    HINT_STORAGE_ZOOKEEPER_SET_CACHE_LISTENER					("设置ZooKeeper的Cache监听："),
    HINT_STORAGE_ZOOKEEPER_GET_CACHE_CHILD_NODE_DATA			("获取ZooKeeper已经Cache变化的所有子节点数据："),
    HINT_STORAGE_ZOOKEEPER_REMOVE_CACHE_LISTENER				("取消ZooKeeper的Cache监听："),
    HINT_STORAGE_ZOOKEEPER_RECV_CHILDEVENT						("接收到ZooKeeper节点变更事件："),
    HINT_STORAGE_ZOOKEEPER_ADD_CHILD_NODE						("ZooKeeper增加子节点，同步到本地。"),
    HINT_STORAGE_ZOOKEEPER_UPDATE_CHILD_NODE					("ZooKeeper更新子节点，同步到本地。"),
    HINT_STORAGE_ZOOKEEPER_REMOVE_CHILD_NODE					("ZooKeeper移除子节点，同步到本地。"),
    
    
    
    HINT_STORAGE_REDIS_BUILD_BUILDER							("创建Redis构造器实例"),
    HINT_STORAGE_REDIS_CLOSE_POOL								("关闭Redis连接池"),
    HINT_STORAGE_REDIS_CONNECT_SESSION                          ("建立Redis消息队列会话......"),
    HINT_STORAGE_REDIS_CLOSE_SESSION                            ("关闭Redis消息队列会话。"),
    HINT_STORAGE_REDIS_GET_LIST_DATA_SUCCESS                    ("从Redis消息队列接收数据。Key和Value分别是："),
    HINT_STORAGE_REDIS_SET_LIST_DATA_SUCCESS                    ("往Redis消息队列保存数据成功。Key和Value分别是："),
    HINT_STORAGE_REDIS_SET_HASH_DATA_SUCCESS		            ("往Redis消息散列保存数据成功。Key、field和Value分别是："	),
    HINT_STORAGE_REDIS_GET_HASH_DATA_SUCCESS            		("从Redis消息散列接收数据。Key、field和Value分别是："		),
    HINT_STORAGE_REDIS_HASH_DATA_EXISTS				            ("Redis消息中存在散列。Key、field分别是："				),
    HINT_STORAGE_REDIS_HASH_DATA_NOTEXISTS          			("Redis消息中不存在散列。Key、field分别是："				),
    HINT_STORAGE_REDIS_GET_LIST_DATA_LENGTH                     ("消息队列的长度是："                                     );

    private String desc;

    Hints(String desc)
    {
        this.desc = desc;
    }

    public String getDesc()
    {
        return this.desc;
    }

    public String toString()
    {
        return getDesc();
    }
}
