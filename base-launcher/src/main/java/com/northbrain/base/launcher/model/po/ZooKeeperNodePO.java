package com.northbrain.base.launcher.model.po;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.bo.Names;
import com.northbrain.base.common.model.bo.Parameters;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;

import java.io.Serializable;

/**
 * 类名：ZooKeeper节点类
 * 用途：读写ZooKeeper节点
 * @author Jiakun
 * @version 1.0
 */
public class ZooKeeperNodePO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(ZooKeeperNodePO.class);

    private String 		nameSpace;		//命名空间
    private CreateMode 	type;			//节点类型（永久/临时，顺序/随机）
    private String 		nodeName;		//节点名称
    private String 		nodeValue;		//节点取值
    private int 		dataVersion;	//节点数据版本号

    /**
     * 方法：ZooKeeper节点类的构造方法
     * @param nameSpace 命名空间
     * @param type 节点类型
     * @param nodeName 节点名称
     * @param nodeValue 节点取值
     * @param dataVersion 节点数据版本号
     */
    ZooKeeperNodePO(String nameSpace, CreateMode type,
                    String nodeName, String nodeValue, int dataVersion)
    {
        setNameSpace(nameSpace);
        setType(type);
        setNodeName(nodeName);
        setNodeValue(nodeValue);
        setDataVersion(dataVersion);
    }

    public String getNameSpace()
    {
        return nameSpace;
    }

    private void setNameSpace(String nameSpace)
    {
        this.nameSpace = nameSpace;
    }

    public CreateMode getType()
    {
        return type;
    }

    public void setType(CreateMode type)
    {
        this.type = type;
    }

    public String getNodeName()
    {
        return nodeName;
    }

    private void setNodeName(String nodeName)
    {
        this.nodeName = nodeName;
    }

    public String getNodeValue()
    {
        return nodeValue;
    }

    /**
     * 方法：读取节点的取值，返回该值的字节数组。
     * @return 节点在ZooKeeper的取值。
     */
    public byte[] getByteNodeValue() throws Exception 
    {
        String businessCommonCharset = Parameters.get(Names.BUSINESS_COMMON_CHARSET);

        if(businessCommonCharset == null || businessCommonCharset.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_CHARSET");
            return null;
        }

        return nodeValue.getBytes(businessCommonCharset);
    }

    private void setNodeValue(String nodeValue)
    {
        this.nodeValue = nodeValue;
    }

    public int getDataVersion()
    {
        return dataVersion;
    }

    private void setDataVersion(int dataVersion)
    {
        this.dataVersion = dataVersion;
    }

    /**
     * 方法：获取节点的完整路径
     * @return 命名空间+节点名的完整路径
     */
    public String getPath()
    {
        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_NODE_PATH_INFO + this.getNameSpace() + "," + this.getNodeName());

        if(this.getNameSpace() == null || this.getNameSpace().equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL);
            return null;
        }

        if(this.getNodeName() == null || this.getNodeName().equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL);
            return null;
        }

        return this.getNameSpace() + Constants.BUSINESS_COMMON_NODE_SEPARATOR + this.getNodeName();
    }
}
