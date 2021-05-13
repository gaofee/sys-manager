package com.gaofei.sysmanager.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : gaofee
 * @date : 10:45 2021/5/10
 * @码云地址 : https://gitee.com/itgaofee
 */
@Component
public class MsgUtil {

    private String from="2312949906@qq.com";

    @Autowired
    private JavaMailSender sender;

    public void sendTextEmail(String to,String subject,String content){
                 SimpleMailMessage message = new SimpleMailMessage();
                 message.setFrom(from);
                 message.setTo(to);
                 message.setSubject(subject);
                 message.setText(content);
                 message.setSentDate(new Date());
                 sender.send(message);
             }
}
