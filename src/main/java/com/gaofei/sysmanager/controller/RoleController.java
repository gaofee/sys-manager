package com.gaofei.sysmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaofei.sysmanager.common.CommonResult;
import com.gaofei.sysmanager.domain.Role;
import com.gaofei.sysmanager.domain.UserRole;
import com.gaofei.sysmanager.service.IRoleService;
import com.gaofei.sysmanager.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gaofei
 * @since 2021-04-27
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @Autowired
    IUserRoleService userRoleService;

    @RequestMapping("list")
    public CommonResult list(Integer uid,@RequestParam(defaultValue = "1") long pageNum, @RequestParam(defaultValue = "6") long pageSize){
        IPage page = new Page(pageNum, pageSize);
        QueryWrapper<Role> roleWrapper = new QueryWrapper();

        IPage page1 = roleService.page(page, roleWrapper);

//        QueryWrapper<UserRole> userRoleWrapper = new QueryWrapper();
//        userRoleWrapper.eq("uid", uid);
        //根据用户id查询角色id 并且返回给前台,让他回显
//        List<UserRole> list = userRoleService.list(userRoleWrapper);//这个数据就是用户角色的数据的封装-->角色复选框回显

//        ArrayList<Role> roles = new ArrayList<>();
        //根据角色id查询对应的角色
        /*list.forEach(userRole -> {
            Role byId = roleService.getById(userRole.getRid());
            roles.add(byId);
        });

        HashMap<String, Object> map = new HashMap<>();
        map.put("page1",page1);
        map.put("roles", roles);*/
        return CommonResult.success(page1, "success");
    }

    /**
     * 根据用户id,来查询角色
     * @param uid
     * @return
     */
    @RequestMapping("findRolesByUid")
    public CommonResult findRolesByUid(Integer uid){
        QueryWrapper<UserRole> userRoleWrapper = new QueryWrapper();
        userRoleWrapper.eq("uid", uid);
        //1.根据用户id查询用户角色表
        List<UserRole> list = userRoleService.list(userRoleWrapper);//这个数据就是用户角色的数据的封装-->角色复选框回显

        ArrayList<Role> roles = new ArrayList<>();
        //根据角色id查询对应的角色
        list.forEach(userRole -> {
            Role byId = roleService.getById(userRole.getRid());
            //把查询出来的角色存入集合
            roles.add(byId);
        });
        return CommonResult.success(roles);
    }
}

