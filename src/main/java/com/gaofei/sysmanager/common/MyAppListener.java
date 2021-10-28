package com.gaofei.sysmanager.common;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author : gaofee
 * @date : 14:39 2021/8/20
 * @码云地址 : https://gclouds.gitee.io
 */
//@Component
public class MyAppListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("@@@@==================================@@@@");
        System.out.println("@@@@----springboot启动,我就执行了---@@@@");
    }
}
