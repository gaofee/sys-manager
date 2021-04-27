package com.gaofei.sysmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaofei.domain.FileResult;
import com.gaofei.service.FileService;
import com.gaofei.sysmanager.common.CommonResult;
import com.gaofei.sysmanager.domain.User;
import com.gaofei.sysmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        boolean save = userService.save(user);
        return CommonResult.success(save,"保存成功");
    }



    @RequestMapping("faceUpload")
    public CommonResult faceUpload(MultipartFile[] file){
        List<FileResult> upload = fileService.upload(file);
        return CommonResult.success(upload,"upload成功");
    }
}

