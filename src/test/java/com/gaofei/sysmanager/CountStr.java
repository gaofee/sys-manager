package com.gaofei.sysmanager;

import com.gaofei.sysmanager.common.MsgUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.File;
import java.util.HashMap;

/**
 * @author : shun
 * @date : 11:06 2021/10/25
 * @
 */
@SpringBootTest
public class CountStr {
    @Autowired
    MsgUtil msgUtil;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Test
    public void countStr(){
        //1.添加的逻辑
        //2.发邮件
//        msgUtil.sendTextEmail("79527743@qq.com","这是我发送标题","这是内容!!!");
        //往kafka发消息
        kafkaTemplate.send("1904a","mail-msg","msg");
        //往kafka发消息
        //3.返回添加成功
    }



//    排序
/*
科目表:
科目id   |科目名称
学生表:
学生id | 学生姓名| 学生所选课程|
成绩表:
成绩表id| 学生id(外键)| 成绩score| 科目id(外键)

#获取指定课程的学生成绩

select 学生id,  学生分数  from  分数表  where 科目id=?

#获取学生各个科目的总成绩
select 学生id,sum(分数) from 分数表  group by  学生id
#然后把每个学生的各个科目的总成绩降序排列取前10个
select sum(成绩) as 总分  from 成绩表  group by 学生id  order by 总分  desc limit 0,10

select  *
from
(select 学生id,  学生分数  from  分数表  where 科目id=?) a
(select 学生id,sum(分数) from 分数表  group by  学生id) b

group by b.学生id having a.学生id = b.学生id order by b.总分 desc limit 0,10



 */

    //统计
    private void count(String name) {

        char[] chars = name.toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();
        for (char aChar : chars) {
            if(!map.containsKey(aChar)){
                map.put(aChar, 1);
            }else {
                map.put(aChar, map.get(aChar) + 1);
            }
        }

        map.forEach((k,v)->{
            System.out.println(k+":"+v);
        });
    }


}
