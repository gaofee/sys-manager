package com.gaofei.sysmanager.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaofei.domain.FileResult;
import com.gaofei.service.FileService;
import com.gaofei.sysmanager.common.CommonResult;
import com.gaofei.sysmanager.common.MsgUtil;
import com.gaofei.sysmanager.domain.Role;
import com.gaofei.sysmanager.domain.User;
import com.gaofei.sysmanager.domain.UserRole;
//import com.gaofei.sysmanager.es.EsUserResp;
import com.gaofei.sysmanager.service.IUserRoleService;
import com.gaofei.sysmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gaofei
 * @since 2021-04-27
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    FileService fileService;

    @Autowired
    IUserRoleService userRoleService;

    @Autowired
    MsgUtil util;


   /* @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
//
//
//    @Autowired
//    EsUserResp esUserResp;

    @Autowired
    RedisTemplate<String,String> redisTemplate;*/



    //接口幂等性处理!!!
//    @Idempotent(key = "#user.username",expireTime = 1,info = "请勿重复提交!!!")

    @RequestMapping("login")
    public CommonResult login(@RequestBody User user){


        //如何解决消息重复消费问题?
        //发消息的时候 kafkaTemplate.send("1904a", "mail-msg", "msg");中间的参数是一个文艺的key标识

//        //发送消息,生产者的消息确认机制
//        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("1904a", "mail-msg", "msg");
//        redisTemplate.opsForValue().set("mail-msg","mail-msg");//往reids中存入唯一标识
//        send.addCallback(new ListenableFutureCallback(){

//            @Override
//            public void onSuccess(Object o) {
//                System.out.println("消息发送成功了!!!!");
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                System.out.println("消息发送失败会执行这里!!!");
//                //再发一次
//            }
//        });
//        User user1 = new User();
//        user1.setName("zhangsan");
//        user1.setAddress("北京");
//        esUserResp.save(user1);
//        esUserResp.delete();
//        List<User> zhangsan = esUserResp.findByName("zhangsan");


            String newPass = SecureUtil.md5(user.getPassword());
            user.setPassword(newPass);
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username", user.getUsername());
            wrapper.eq("password", user.getPassword());
            List<User> list = userService.list(wrapper);
            //从数据库对比用户名和密码是否正确,如果能查询出来,说明正确
            if(list!=null&&list.size()>0){
                return CommonResult.success(list.get(0));
    //            util.sendTextEmail("");
            }
            return CommonResult.success(null, "没有此用户");

    }

    @RequestMapping("setRole")
    public CommonResult setRole(Integer uid, @RequestBody List<Role> checkedArr){
        System.out.println(uid+"***************"+checkedArr);
        //根据uid 更新或者保存对应的角色信息
        //1.先根据用户id删除所有的角色
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid",uid);
        userRoleService.remove(wrapper);
        checkedArr.forEach(role->{
            UserRole userRole = new UserRole();
            userRole.setRid(role.getId());
            userRole.setUid(uid);
            //2.添加中间表
            userRoleService.save(userRole);

        });

        return CommonResult.success(null);
    }

    //统一结果集
    @RequestMapping("list")
    public CommonResult list( String name , @RequestParam(defaultValue = "1") long pageNum, @RequestParam(defaultValue = "5")long pageSize){
        System.out.println(name+"==");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name",name);
        IPage<User> page = userService.page(new Page<>(pageNum, pageSize), wrapper);
        return CommonResult.success(page,"数据查询成功");
    }

    @RequestMapping("add")
    public CommonResult add( @RequestBody User user){
        //防止修改的时候,把原来的密码加密
        if(user.getId()==null||user.getId()==0){
            //获取用户的密码,加密之后再存入实体类,保存
            String password = user.getPassword();
            //为密码进行md5加密,使用糊涂工具类
            String secPassword = SecureUtil.md5(password);
            user.setPassword(secPassword);

        }

        System.out.println(user);
        boolean save = userService.saveOrUpdate(user);
        return CommonResult.success(save,"保存成功");
    }



    @RequestMapping("faceUpload")
    public CommonResult faceUpload(MultipartFile[] file){
        List<FileResult> upload = fileService.upload(file);
        return CommonResult.success(upload,"upload成功");
    }

    @RequestMapping("del")
    public CommonResult del(@RequestParam("ids") Integer[] ids){

        userService.removeByIds(Arrays.asList(ids));

        return CommonResult.success(null, "成功");
    }
    @RequestMapping("getOne")
    public CommonResult getOne(Integer id){

        User user = userService.getById(id);

        return CommonResult.success(user, "成功");
    }
}

