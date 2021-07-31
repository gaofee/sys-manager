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
        DesensitizedUtil.mobilePhone("18049531999");

        int i = "重地".hashCode();
        int e = "通话".hashCode();
//        System.out.println(c);
        System.out.println(i);
        System.out.println(e);

    }

}
