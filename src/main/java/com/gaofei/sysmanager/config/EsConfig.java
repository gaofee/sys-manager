package com.gaofei.sysmanager.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author gaofee
 */
@Configuration
public class EsConfig {
    /**
        构造执行,加载该配置
     */
    @PostConstruct
    public void config(){
        //es整合netty冲突解决:Caused by: java.lang.IllegalStateException: availableProcessors is already set to [8], rejecting [8]
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }


}
