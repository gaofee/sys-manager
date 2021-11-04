//package com.gaofei.sysmanager.kafka;
//
//import com.gaofei.sysmanager.common.MsgUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.listener.MessageListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Component;
//
///**
// * @author : gaofee
// * @date : 10:32 2021/10/28
// * @码云地址 : https://feege.gitee.io
// */
//@Component
//@Slf4j  //开启日志管理的注解
//public class MsgListenner {
//
//    @Autowired
//    MsgUtil msgUtil;
//    /*@Autowired
//    RedisTemplate<String,String> redisTemplate;*/
//
//
//    @KafkaListener(topics = "1904a",containerFactory="ackContainerFactory" ,id="ack")
//
//    public void msg(ConsumerRecord<String, String> stringStringConsumerRecord, Acknowledgment ack) {
//
//        String key = stringStringConsumerRecord.key();
////        Boolean aBoolean = redisTemplate.hasKey(key);
//
//        if(aBoolean){
//            System.out.println("重复消费!!!");
//        }else{
//            String value = stringStringConsumerRecord.value();
////        System.out.println(key+":"+value);
//            log.info("key:{},value:{}", key, value);
////士大夫撒旦
//            if (value.equals("msg")) {
//                msgUtil.sendTextEmail("79527743@qq.com", "这是我发送标题", "这是内容!!!");
//            }
//            //手动提交offset
//            ack.acknowledge();
//        }
//
//    }
//
//}
