package com.northbrain.base.launcher.model.po;

/**
 * 类名：占位符类
 * 用途：保持占位符信息
 * @author Jiakun
 * @version 1.0
 *
 */
public class PlaceHolderPO
{
    private String 	jdbcDriverClassName;					//数据库驱动类名
    private String 	jdbcUrl;								//数据库连接串
    private String 	jdbcUserName;							//数据库用户名
    private String 	jdbcPassword;							//数据库密码
    private boolean jdbcDefaultAutoCommit;					//连接池创建的连接的默认的auto-commit 状态
    private int 	jdbcInitialSize;						//初始化连接： 连接池启动时创建的初始化连接数量
    private int 	jdbcMaxActive;							//最大活动连接： 连接池在同一时间能够分配的最大活动连接的数量， 如果设置为非正数则表示不限制
    private int 	jdbcMaxIdle;							//最大空闲连接： 连接池中容许保持空闲状态的最大连接数量， 超过的空闲连接将被释放， 如果设置为负数表示不限制 。如果启用，将定期检查限制连接，如果空闲时间超过minEvictableIdleTimeMillis 则释放连接 （ 参考testWhileIdle ）
    private int 	jdbcMinIdle;							//最小空闲连接： 连接池中容许保持空闲状态的最小连接数量， 低于这个数量将创建新的连接， 如果设置为0 则不创建 。如果连接验证失败将缩小这个值（ 参考testWhileIdle ）
    private int 	jdbcMaxWait;							//最大等待时间： 当没有可用连接时， 连接池等待连接被归还的最大时间( 以毫秒计数)， 超过时间则抛出异常， 如果设置为-1 表示无限等待
    private String 	jdbcValidationQuery;					//SQL 查询， 用来验证从连接池取出的连接， 在将连接返回给调用者之前。 如果指定， 则查询必须是一个SQL SELECT 并且必须返回至少一行记录 。查询不必返回记录，但这样将不能抛出SQL异常
    private boolean jdbcTestOnBorrow;						//指明是否在从池中取出连接前进行检验， 如果检验失败， 则从池中去除连接并尝试取出另一个。注意： 设置为true 后如果要生效，validationQuery 参数必须设置为非空字符串 。参考validationInterval以获得更有效的验证
    private boolean jdbcTestOnReturn;						//指明是否在归还到池中前进行检验 注意： 设置为true 后如果要生效，validationQuery 参数必须设置为非空字符串
    private boolean jdbcTestWhileIdle;						//指明连接是否被空闲连接回收器( 如果有) 进行检验。 如果检测失败， 则连接将被从池中去除。注意： 设置为true 后如果要生效，validationQuery 参数必须设置为非空字符串
    private int 	jdbcTimeBetweenEvictionRunsMillis;		//在空闲连接回收器线程运行期间休眠的时间值， 以毫秒为单位。 如果设置为非正数， 则不运行空闲连接回收器线程 。这个值不应该小于1秒，它决定线程多久验证连接或丢弃连接
    private int		jdbcMinEvictableIdleTimeMillis;			//连接在池中保持空闲而不被空闲连接回收器线程( 如果有) 回收的最小时间值，单位毫秒
    private boolean jdbcRemoveAbandoned;					//标记是否删除泄露的连接， 如果他们超过了removeAbandonedTimout 的限制。 如果设置为true， 连接被认为是被泄露并且可以被删除， 如果空闲时间超过removeAbandonedTimeout。 设置为true 可以为写法糟糕的没有关闭连接的程序修复数据库连接。 
    private int 	jdbcRemoveAbandonedTimeout;				//泄露的连接可以被删除的超时值， 单位秒应设置为应用中查询执行最长的时间
    private long	jdbcValidationInterval;					//避免过度验证，保证验证不超过这个频率——以毫秒为单位。如果一个连接应该被验证，但上次验证未达到指定间隔，将不再次验证。
    
    public String getJdbcDriverClassName()
    {
        return jdbcDriverClassName;
    }
    
    public void setJdbcDriverClassName(String jdbcDriverClassName)
    {
        this.jdbcDriverClassName = jdbcDriverClassName;
    }
    
    public String getJdbcUrl()
    {
        return jdbcUrl;
    }
    
    public void setJdbcUrl(String jdbcUrl)
    {
        this.jdbcUrl = jdbcUrl;
    }
    
    public String getJdbcUserName()
    {
        return jdbcUserName;
    }
    
    public void setJdbcUserName(String jdbcUserName)
    {
        this.jdbcUserName = jdbcUserName;
    }
    
    public String getJdbcPassword()
    {
        return jdbcPassword;
    }
    
    public void setJdbcPassword(String jdbcPassword)
    {
        this.jdbcPassword = jdbcPassword;
    }

	public boolean isJdbcDefaultAutoCommit() 
	{
		return jdbcDefaultAutoCommit;
	}

	public void setJdbcDefaultAutoCommit(boolean jdbcDefaultAutoCommit) 
	{
		this.jdbcDefaultAutoCommit = jdbcDefaultAutoCommit;
	}

	public int getJdbcInitialSize()
	{
		return jdbcInitialSize;
	}

	public void setJdbcInitialSize(int jdbcInitialSize) 
	{
		this.jdbcInitialSize = jdbcInitialSize;
	}

	public int getJdbcMaxActive()
	{
		return jdbcMaxActive;
	}

	public void setJdbcMaxActive(int jdbcMaxActive) 
	{
		this.jdbcMaxActive = jdbcMaxActive;
	}

	public int getJdbcMaxIdle() 
	{
		return jdbcMaxIdle;
	}

	public void setJdbcMaxIdle(int jdbcMaxIdle) 
	{
		this.jdbcMaxIdle = jdbcMaxIdle;
	}

	public int getJdbcMinIdle() 
	{
		return jdbcMinIdle;
	}

	public void setJdbcMinIdle(int jdbcMinIdle) 
	{
		this.jdbcMinIdle = jdbcMinIdle;
	}

	public int getJdbcMaxWait() 
	{
		return jdbcMaxWait;
	}

	public void setJdbcMaxWait(int jdbcMaxWait)
	{
		this.jdbcMaxWait = jdbcMaxWait;
	}

	public String getJdbcValidationQuery()
	{
		return jdbcValidationQuery;
	}

	public void setJdbcValidationQuery(String jdbcValidationQuery) 
	{
		this.jdbcValidationQuery = jdbcValidationQuery;
	}

	public boolean isJdbcTestOnBorrow() 
	{
		return jdbcTestOnBorrow;
	}

	public void setJdbcTestOnBorrow(boolean jdbcTestOnBorrow) 
	{
		this.jdbcTestOnBorrow = jdbcTestOnBorrow;
	}

	public boolean isJdbcTestOnReturn() 
	{
		return jdbcTestOnReturn;
	}

	public void setJdbcTestOnReturn(boolean jdbcTestOnReturn)
	{
		this.jdbcTestOnReturn = jdbcTestOnReturn;
	}

	public boolean isJdbcTestWhileIdle() 
	{
		return jdbcTestWhileIdle;
	}

	public void setJdbcTestWhileIdle(boolean jdbcTestWhileIdle)
	{
		this.jdbcTestWhileIdle = jdbcTestWhileIdle;
	}

	public int getJdbcTimeBetweenEvictionRunsMillis() 
	{
		return jdbcTimeBetweenEvictionRunsMillis;
	}

	public void setJdbcTimeBetweenEvictionRunsMillis(int jdbcTimeBetweenEvictionRunsMillis) {
		this.jdbcTimeBetweenEvictionRunsMillis = jdbcTimeBetweenEvictionRunsMillis;
	}

	public int getJdbcMinEvictableIdleTimeMillis() 
	{
		return jdbcMinEvictableIdleTimeMillis;
	}

	public void setJdbcMinEvictableIdleTimeMillis(int jdbcMinEvictableIdleTimeMillis) {
		this.jdbcMinEvictableIdleTimeMillis = jdbcMinEvictableIdleTimeMillis;
	}

	public boolean isJdbcRemoveAbandoned()
	{
		return jdbcRemoveAbandoned;
	}

	public void setJdbcRemoveAbandoned(boolean jdbcRemoveAbandoned) 
	{
		this.jdbcRemoveAbandoned = jdbcRemoveAbandoned;
	}

	public int getJdbcRemoveAbandonedTimeout() 
	{
		return jdbcRemoveAbandonedTimeout;
	}

	public void setJdbcRemoveAbandonedTimeout(int jdbcRemoveAbandonedTimeout) {
		this.jdbcRemoveAbandonedTimeout = jdbcRemoveAbandonedTimeout;
	}

	public long getJdbcValidationInterval() 
	{
		return jdbcValidationInterval;
	}

	public void setJdbcValidationInterval(long jdbcValidationInterval) 
	{
		this.jdbcValidationInterval = jdbcValidationInterval;
	}
}
