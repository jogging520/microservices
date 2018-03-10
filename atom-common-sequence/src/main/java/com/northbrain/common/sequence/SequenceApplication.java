package com.northbrain.common.sequence;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import com.northbrain.base.common.model.bo.*;
import com.northbrain.base.common.util.LauncherUitl;
import com.northbrain.base.common.util.StackTracerUtil;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan({"com.northbrain"})
@EnableDiscoveryClient
@EnableSwagger2
public class SequenceApplication
{
    private static Logger logger = Logger.getLogger(SequenceApplication.class);

    public static void main(String[] arguments)
    {
        try
        {
            if(LauncherUitl.parseCommandLine(arguments))
            {
                //初始化属性配置，从application.properties中读取zookeeper和业务的基础配置参数。
                LauncherUitl.initProperties(BaseType.SERVICETYPE.ATOM);
                //设置域名
                LauncherUitl.appendParameter(Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE,
                        Constants.STORAGE_ZOOKEEPER_DOMAIN_NAMESPACE_NAME,
                        Constants.STORAGE_ZOOKEEPER_COMMON_NAMESPACE);
                //设置应用名称
                LauncherUitl.appendParameter(Constants.STORAGE_ZOOKEEPER_BUSINESS_NAMESPACE,
                        Constants.BUSINESS_COMMON_APPLICATION_NAME,
                        Constants.BUSINESS_COMMON_SEQUENCE_ATOM_MICROSERVICE);
                //设置日志级别
                LauncherUitl.setLog();

                logger.info(Hints.HINT_SYSTEM_PROCESS_SYSTEM_STARTUP);

                //设置服务名称
                Map<String, Object> defaultProperties = new HashMap<>();
                defaultProperties.put(Constants.SYSTEM_SPRING_PROPERTY_APPLICATION_NAME,
                        Constants.BUSINESS_COMMON_SEQUENCE_ATOM_MICROSERVICE);

                //设置spring.application.name
                SpringApplication springApplication = new SpringApplication(SequenceApplication.class);
                springApplication.setDefaultProperties(defaultProperties);

                //启动服务
                springApplication.run(arguments);
            }
            else
            {
                logger.error(Errors.ERROR_SYSTEM_PARSE_COMMAND_LINE);
            }
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
        }
        catch (Throwable throwable)
        {
            logger.error(StackTracerUtil.getExceptionInfo(throwable));
        }
    }
}
