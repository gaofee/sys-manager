package com.gaofei.sysmanager.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaofei.domain.FileResult;
import com.gaofei.service.FileService;
import com.gaofei.sysmanager.common.CommonResult;
import com.gaofei.sysmanager.domain.Role;
import com.gaofei.sysmanager.domain.User;
import com.gaofei.sysmanager.domain.UserRole;
import com.gaofei.sysmanager.service.IUserRoleService;
import com.gaofei.sysmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping("login")
    public CommonResult login(@RequestBody User user){
        String newPass = SecureUtil.md5(user.getPassword());
        user.setPassword(newPass);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        wrapper.eq("password", user.getPassword());
        List<User> list = userService.list(wrapper);
        //从数据库对比用户名和密码是否正确,如果能查询出来,说明正确
        if(list!=null&&list.size()>0){
            return CommonResult.success(list.get(0));
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
        //获取用户的密码,加密之后再存入实体类,保存
        String password = user.getPassword();
        //为密码进行md5加密,使用糊涂工具类
        String secPassword = SecureUtil.md5(password);
        user.setPassword(secPassword);

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

