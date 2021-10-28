package com.gaofei.sysmanager;

import cn.hutool.core.util.DesensitizedUtil;
import com.gaofei.sysmanager.common.MsgUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SysManagerApplicationTests {

   /* @Autowired
    MsgUtil util;*/

    @Test
    void contextLoads() {
        String s = "1000";
        long l = Long.parseLong(s);
        System.out.println(l);

    }

}
