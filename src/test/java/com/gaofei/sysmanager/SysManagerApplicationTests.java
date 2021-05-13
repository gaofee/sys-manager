package com.gaofei.sysmanager;

import com.gaofei.sysmanager.common.MsgUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SysManagerApplicationTests {

    @Autowired
    MsgUtil util;

    @Test
    void contextLoads() {

        util.sendTextEmail("79527743@qq.com","这个是测试1","这是邮件内容1");
    }

}
