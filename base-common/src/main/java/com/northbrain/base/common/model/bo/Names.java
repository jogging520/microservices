package com.northbrain.base.common.model.bo;

/**
 * 参数命名枚举。
 * 通过type：类型，name：名称的两级模式来组织。
 * SYSTEM_XXXXX：
 * BUSINESS_XXXXX：
 * STORAGE_XXXXX：
 * 使用下划线增加可读性。
 * @author Jiakun
 * @version 1.0
 */
public enum Names
{
    /**
     * 系统级参数命名SYSTEM_
     */
	SYSTEM_MIN_FREE_MEMORY                                          ("SYSTEM",      "SYSTEM_MIN_FREE_MEMORY"                                    ), //最小内存剩余量
    SYSTEM_USAGE_PROMPT                                             ("SYSTEM",      "SYSTEM_USAGE_PROMPT"                                       ), //系统启停方法
    SYSTEM_SPRING_CONFIG_LOCATION                                   ("SYSTEM",      "SYSTEM_SPRING_CONFIG_LOCATION"                             ), //spring配置文件位置
    
    /**
     * 通用业务级参数命名BUSINESS_COMMON_
     */
    BUSINESS_COMMON_PROJECT_NAME									("BUSINESS",	"BUSINESS_COMMON_PROJECT_NAME"								), //项目名称
    BUSINESS_COMMON_APPLICATION_NAME								("BUSINESS",	"BUSINESS_COMMON_APPLICATION_NAME"							), //应用名称
    BUSINESS_COMMON_SERVER_PORT										("BUSINESS",	"BUSINESS_COMMON_SERVER_PORT"								), //服务端口号
    BUSINESS_COMMON_LOG_LEVEL										("BUSINESS",	"BUSINESS_COMMON_LOG_LEVEL"									), //日志级别
    BUSINESS_COMMON_LOG_DIRECTORY									("BUSINESS",	"BUSINESS_COMMON_LOG_DIRECTORY"								), //日志目录
    BUSINESS_COMMON_CHARSET                                         ("BUSINESS",    "BUSINESS_COMMON_CHARSET"                                   ), //业务字符集
    BUSINESS_COMMON_COMMAND_END_SYMBOL                              ("BUSINESS",    "BUSINESS_COMMON_COMMAND_END_SYMBOL"                        ), //命令结束符号
    BUSINESS_COMMON_MAX_RETRIES                                     ("BUSINESS",    "BUSINESS_COMMON_MAX_RETRIES"                               ), //最大尝试次数（重连）
    BUSINESS_COMMON_MANAGE_INTERVAL_MS                              ("BUSINESS",    "BUSINESS_COMMON_MANAGE_INTERVAL_MS"                        ), //任务处理时间间隔
    BUSINESS_COMMON_CACHE_THREAD_POOL_NUMBER                        ("BUSINESS",    "BUSINESS_COMMON_CACHE_THREAD_POOL_NUMBER"                  ), //用作ZooKeeper缓存的线程池数量
    BUSINESS_COMMON_TASK_THREAD_POOL_NUMBER                         ("BUSINESS",    "BUSINESS_COMMON_TASK_THREAD_POOL_NUMBER"                   ), //用生产任务的线程池数量
    BUSINESS_COMMON_JWT_TOKEN_KEY                                   ("BUSINESS",    "BUSINESS_COMMON_JWT_TOKEN_KEY"                             ), //用于后端服务进行JWT加解密的key
    BUSINESS_COMMON_JWT_TOKEN_COMPANY_ID                            ("BUSINESS",    "BUSINESS_COMMON_JWT_TOKEN_COMPANY_ID"                      ), //用于后端服务生产JWT Token的公司ID
    BUSINESS_COMMON_JWT_TOKEN_ISSUER                                ("BUSINESS",    "BUSINESS_COMMON_JWT_TOKEN_ISSUER"                          ), //用于后端服务生产JWT Token的发行者
    BUSINESS_COMMON_JWT_TOKEN_AUDIENCE                              ("BUSINESS",    "BUSINESS_COMMON_JWT_TOKEN_AUDIENCE"                        ), //用于后端服务生产JWT Token的接收者
    BUSINESS_COMMON_JWT_TOKEN_EXPIRE_TTL                            ("BUSINESS",    "BUSINESS_COMMON_JWT_TOKEN_EXPIRE_TTL"                      ), //用于后端服务生产JWT Token的过期时长（毫秒）

    /**
     * 专用业务级参数命名BUSINESS_XXXX_， XXXX为域名称
     */

    
    /**
     * 数据存储级参数命名BUSINESS_XXXX_， XXXX为REDIS、ZOOKEEPER、DATABASE等
     */
    STORAGE_ZOOKEEPER_DOMAIN_NAMESPACE                              ("STORAGE",     "STORAGE_ZOOKEEPER_DOMAIN_NAMESPACE"                        ), //ZooKeeper基础域命名空间
    STORAGE_ZOOKEEPER_SERVERS_ADDRESS                               ("STORAGE",     "STORAGE_ZOOKEEPER_SERVERS_ADDRESS"                         ), //ZooKeeper服务器地址群
    STORAGE_ZOOKEEPER_BASE_SLEEP_TIME_MS                            ("STORAGE",     "STORAGE_ZOOKEEPER_BASE_SLEEP_TIME_MS"                      ), //连接ZooKeeper重试策略RetryPolicy初始Sleep时间，毫秒
    STORAGE_ZOOKEEPER_CONNECT_MAX_RETRIES							("STORAGE",		"STORAGE_ZOOKEEPER_CONNECT_MAX_RETRIES"                     ), //连接ZooKeeper重试次数
    STORAGE_ZOOKEEPER_SESSION_TIMEOUT_MS                            ("STORAGE",     "STORAGE_ZOOKEEPER_SESSION_TIMEOUT_MS"                      ), //ZooKeeper会话超时时间
    STORAGE_ZOOKEEPER_CACHE_THREAD_POOL_NUMBER                      ("STORAGE",     "STORAGE_ZOOKEEPER_CACHE_THREAD_POOL_NUMBER"                ), //ZooKeeper服务器缓存数据的线程数
    STORAGE_ZOOKEEPER_GLOBAL_OPERATION_RECORD_SEQUENCE              ("STORAGE",     "STORAGE_ZOOKEEPER_GLOBAL_OPERATION_RECORD_SEQUENCE"        ), //ZooKeeper全局唯一操作记录序列号
    STORAGE_ZOOKEEPER_GLOBAL_REGISTRY_SEQUENCE                      ("STORAGE",     "STORAGE_ZOOKEEPER_GLOBAL_REGISTRY_SEQUENCE"                ), //ZooKeeper全局唯一注册序列号
    STORAGE_ZOOKEEPER_GLOBAL_LOGIN_SEQUENCE                         ("STORAGE",     "STORAGE_ZOOKEEPER_GLOBAL_LOGIN_SEQUENCE"                   ), //ZooKeeper全局唯一登录序列号
    STORAGE_ZOOKEEPER_GLOBAL_PARTY_SEQUENCE                         ("STORAGE",     "STORAGE_ZOOKEEPER_GLOBAL_PARTY_SEQUENCE"                   ), //ZooKeeper全局唯一参与者序列号


    STORAGE_DATABASE_JDBC_DRIVER_CLASS_NAME                         ("STORAGE",     "STORAGE_DATABASE_JDBC_DRIVER_CLASS_NAME"                   ), //数据库驱动
    STORAGE_DATABASE_JDBC_URL                                       ("STORAGE",     "STORAGE_DATABASE_JDBC_URL"                                 ), //数据库连接串
    STORAGE_DATABASE_JDBC_USER_NAME                                 ("STORAGE",     "STORAGE_DATABASE_JDBC_USER_NAME"                           ), //数据库登录用户名
    STORAGE_DATABASE_JDBC_PASSWORD                                  ("STORAGE",     "STORAGE_DATABASE_JDBC_PASSWORD"                            ), //数据库登录密码
    STORAGE_DATABASE_JDBC_DEFAULT_AUTO_COMMIT						("STORAGE",     "STORAGE_DATABASE_JDBC_DEFAULT_AUTO_COMMIT"                 ), //连接池创建的连接的默认的auto-commit 状态
    STORAGE_DATABASE_JDBC_INITIAL_SIZE								("STORAGE",     "STORAGE_DATABASE_JDBC_INITIAL_SIZE"                        ), //初始化连接： 连接池启动时创建的初始化连接数量
    STORAGE_DATABASE_JDBC_MAX_ACTIVE								("STORAGE",     "STORAGE_DATABASE_JDBC_MAX_ACTIVE"                          ), //最大活动连接： 连接池在同一时间能够分配的最大活动连接的数量， 如果设置为非正数则表示不限制
    STORAGE_DATABASE_JDBC_MAX_IDLE									("STORAGE",     "STORAGE_DATABASE_JDBC_MAX_IDLE"                            ), //最大空闲连接： 连接池中容许保持空闲状态的最大连接数量， 超过的空闲连接将被释放， 如果设置为负数表示不限制 。如果启用，将定期检查限制连接，如果空闲时间超过minEvictableIdleTimeMillis 则释放连接 （ 参考testWhileIdle ）
    STORAGE_DATABASE_JDBC_MIN_IDLE									("STORAGE",     "STORAGE_DATABASE_JDBC_MIN_IDLE"                            ), //最小空闲连接： 连接池中容许保持空闲状态的最小连接数量， 低于这个数量将创建新的连接， 如果设置为0 则不创建 。如果连接验证失败将缩小这个值（ 参考testWhileIdle ）
    STORAGE_DATABASE_JDBC_MAX_WAIT									("STORAGE",     "STORAGE_DATABASE_JDBC_MAX_WAIT"                            ), //最大等待时间： 当没有可用连接时， 连接池等待连接被归还的最大时间( 以毫秒计数)， 超过时间则抛出异常， 如果设置为-1 表示无限等待
    STORAGE_DATABASE_JDBC_VALIDATION_QUERY							("STORAGE",     "STORAGE_DATABASE_JDBC_VALIDATION_QUERY"                    ), //SQL 查询， 用来验证从连接池取出的连接， 在将连接返回给调用者之前。 如果指定， 则查询必须是一个SQL SELECT 并且必须返回至少一行记录 。查询不必返回记录，但这样将不能抛出SQL异常
    STORAGE_DATABASE_JDBC_TEST_ON_BORROW							("STORAGE",     "STORAGE_DATABASE_JDBC_TEST_ON_BORROW"                      ), //指明是否在从池中取出连接前进行检验， 如果检验失败， 则从池中去除连接并尝试取出另一个。注意： 设置为true 后如果要生效，validationQuery 参数必须设置为非空字符串 。参考validationInterval以获得更有效的验证
    STORAGE_DATABASE_JDBC_TEST_ON_RETURN							("STORAGE",     "STORAGE_DATABASE_JDBC_TEST_ON_RETURN"                      ), //指明是否在归还到池中前进行检验 注意： 设置为true 后如果要生效，validationQuery 参数必须设置为非空字符串
    STORAGE_DATABASE_JDBC_TEST_WHILE_IDLE							("STORAGE",     "STORAGE_DATABASE_JDBC_TEST_WHILE_IDLE"                     ), //指明连接是否被空闲连接回收器( 如果有) 进行检验。 如果检测失败， 则连接将被从池中去除。注意： 设置为true 后如果要生效，validationQuery 参数必须设置为非空字符串
    STORAGE_DATABASE_JDBC_TIME_BETWEEN_EVICTION_RUNS_MILLIS			("STORAGE",     "STORAGE_DATABASE_JDBC_TIME_BETWEEN_EVICTION_RUNS_MILLIS"   ), //在空闲连接回收器线程运行期间休眠的时间值， 以毫秒为单位。 如果设置为非正数， 则不运行空闲连接回收器线程 。这个值不应该小于1秒，它决定线程多久验证连接或丢弃连接
    STORAGE_DATABASE_JDBC_MIN_EVICTABLE_IDLE_TIME_MILLIS			("STORAGE",     "STORAGE_DATABASE_JDBC_MIN_EVICTABLE_IDLE_TIME_MILLIS"      ), //连接在池中保持空闲而不被空闲连接回收器线程( 如果有) 回收的最小时间值，单位毫秒
    STORAGE_DATABASE_JDBC_REMOVE_ABANDONED							("STORAGE",     "STORAGE_DATABASE_JDBC_REMOVE_ABANDONED"                    ), //标记是否删除泄露的连接， 如果他们超过了removeAbandonedTimout 的限制。 如果设置为true， 连接被认为是被泄露并且可以被删除， 如果空闲时间超过removeAbandonedTimeout。 设置为true 可以为写法糟糕的没有关闭连接的程序修复数据库连接。 
    STORAGE_DATABASE_JDBC_REMOVE_ABANDONED_TIMEOUT					("STORAGE",     "STORAGE_DATABASE_JDBC_REMOVE_ABANDONED_TIMEOUT"            ), //泄露的连接可以被删除的超时值， 单位秒应设置为应用中查询执行最长的时间
    STORAGE_DATABASE_JDBC_VALIDATION_INTERVAL						("STORAGE",     "STORAGE_DATABASE_JDBC_VALIDATION_INTERVAL"                 ), //避免过度验证，保证验证不超过这个频率——以毫秒为单位。如果一个连接应该被验证，但上次验证未达到指定间隔，将不再次验证。
    
    
    STORAGE_REDIS_SERVER                                            ("STORAGE",     "STORAGE_REDIS_SERVER"                                      ), //Redis服务器（池）
    STORAGE_REDIS_POOL_MAX_TOTAL                                    ("STORAGE",     "STORAGE_REDIS_POOL_MAX_TOTAL"                              ), //Redis最大连接总数
    STORAGE_REDIS_POOL_MAX_IDLE                                     ("STORAGE",     "STORAGE_REDIS_POOL_MAX_IDLE"                               ), //Redis最大空闲数
    STORAGE_REDIS_POOL_MIN_IDLE                                     ("STORAGE",     "STORAGE_REDIS_POOL_MIN_IDLE"                               ), //Redis最小空闲数
    STORAGE_REDIS_POOL_MAX_WAIT_MILLIS                              ("STORAGE",     "STORAGE_REDIS_POOL_MAX_WAIT_MILLIS"                        ), //Redis最大等待时长
    STORAGE_REDIS_POOL_TEST_ON_BORROW                               ("STORAGE",     "STORAGE_REDIS_POOL_TEST_ON_BORROW"                         ), //Redis在获取连接的时候检查有效性, 默认false
    STORAGE_REDIS_POOL_TEST_ON_RETURN                               ("STORAGE",     "STORAGE_REDIS_POOL_TEST_ON_RETURN"                         ), //Redis在空闲时检查有效性, 默认false
    STORAGE_REDIS_POOL_TEST_ON_CREATE                               ("STORAGE",     "STORAGE_REDIS_POOL_TEST_ON_CREATE"                         ), //Redis在创建时检查有效性, 默认false
    STORAGE_REDIS_POOL_JMX_ENABLED                                  ("STORAGE",     "STORAGE_REDIS_POOL_JMX_ENABLED"                            ), //Redis是否启用pool的jmx管理功能, 默认true
    STORAGE_REDIS_POOL_TEST_WHILE_IDLE                              ("STORAGE",     "STORAGE_REDIS_POOL_TEST_WHILE_IDLE"                        ), //Redis Idle时进行连接扫描
    STORAGE_REDIS_POOL_LIFO                                         ("STORAGE",     "STORAGE_REDIS_POOL_LIFO"                                   ), //Redis是否启用后进先出, 默认true
    STORAGE_REDIS_POOL_TIME_BETWEEN_EVICTION_RUNS_MILLIS            ("STORAGE",     "STORAGE_REDIS_POOL_TIME_BETWEEN_EVICTION_RUNS_MILLIS"      ), //Redis逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
    STORAGE_REDIS_POOL_NUM_TESTS_PER_EVICTION_RUN                   ("STORAGE",     "STORAGE_REDIS_POOL_NUM_TESTS_PER_EVICTION_RUN"             ), //Redis每次逐出检查时逐出的最大数目如果为负数就是: 1/abs(n), 默认3
    STORAGE_REDIS_POOL_MIN_EVICTABLE_IDLE_TIME_MILLIS               ("STORAGE",     "STORAGE_REDIS_POOL_MIN_EVICTABLE_IDLE_TIME_MILLIS"         ), //Redis表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
    STORAGE_REDIS_POOL_BLOCK_WHEN_EXHAUSTED                         ("STORAGE",     "STORAGE_REDIS_POOL_BLOCK_WHEN_EXHAUSTED"                   ), //Redis连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
    STORAGE_REDIS_POOL_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS          ("STORAGE",     "STORAGE_REDIS_POOL_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS"    ), //Redis对象空闲多久后逐出, 当空闲时间>该值且空闲连接>最大空闲数时直接逐出,不再根据MinEvictableIdleTimeMillis判断(默认逐出策略)

    STORAGE_REDIS_SENTINEL_POOL                                     ("STORAGE",     "STORAGE_REDIS_SENTINEL_POOL"                               ), //Redis哨兵连接池
    STORAGE_REDIS_SENTINEL_POOL_NAME                                ("STORAGE",     "STORAGE_REDIS_SENTINEL_POOL_NAME"                          ); //Redis哨兵连接池名称

    private String type;
    private String name;

    Names(String type)
    {
        this.type = type;
    }

    Names(String type, String name)
    {
        this.type = type;
        this.name = name;
    }

    public String getType()
    {
        return this.type;
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public String toString()
    {
        return "TYPE:" + getType() + ", NAME:" + getName();
    }
}
