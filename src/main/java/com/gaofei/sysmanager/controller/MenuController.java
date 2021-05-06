package com.gaofei.sysmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gaofei.sysmanager.common.CommonResult;
import com.gaofei.sysmanager.domain.Menu;
import com.gaofei.sysmanager.domain.MenuRole;
import com.gaofei.sysmanager.service.IMenuRoleService;
import com.gaofei.sysmanager.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/menu")
public class MenuController {


    @Autowired
    IMenuService menuService;

    @Autowired
    IMenuRoleService menuRoleService;


    @RequestMapping("findMenuByUid")
    public List<Menu> findMenuByUid (Integer uid){
        //根据用户id查询一级菜单
        List<Menu> oneL = menuService.findMenusByUid(uid,1);
        System.out.println("^^^^^"+oneL);
        return selectChild(uid,oneL);
    }

    private List<Menu> selectChild(Integer uid,List<Menu> oneL) {
        List<Menu> oneList = new ArrayList<>();
        //遍历一级菜单
        if(oneL!=null&& oneL.size()>0){
            oneL.forEach(oneMenu -> {
                System.out.println(oneMenu.getName()+"****");
                //根据一级的id作为二级的parentId查询二级菜单
                List<Menu> twoL = menuService.findMenusByUid(uid,oneMenu.getId());

                if(twoL!=null&& twoL.size()>0){
                    oneMenu.setChildren(twoL);
                    //递归调用
                    selectChild(uid,twoL);
                }
                oneList.add(oneMenu);
            });
        }
        return oneList;
    }

    @RequestMapping("getTree")
    public CommonResult getTree(Integer roleId){
        HashMap<Object, Object> map = new HashMap<>();
        List<Menu> menuList= menuService.getTree(0);//查询所有的菜单
        QueryWrapper wrapper = new QueryWrapper();
        QueryWrapper ww = new QueryWrapper();
        wrapper.eq("rid",roleId);
        //根据角色id查询角色对应的菜单
        List<MenuRole> list = menuRoleService.list(wrapper);
        List<Integer> mids = list.stream().map((mr) -> mr.getMid()).collect(Collectors.toList());//jdk8之后的新特性:stream流
        List<Menu> menus = menuService.findMenusByIds(mids);
        List<Menu> collect1 = menus.stream().filter(menu -> menu.getLevel() != 1).collect(Collectors.toList());
        //根据菜单id查询菜单,过滤掉父节点 level为1 的节点过滤掉
        List<Integer> collect = collect1.stream().map(menu -> menu.getId()).collect(Collectors.toList());
        System.out.println(collect+"======================================================");
        map.put("mids", collect); //需要回显的id
        map.put("menuList", menuList);//所有的菜单
        return CommonResult.success(map);

    }
    @RequestMapping("saveMenuRole")
    public boolean save(@RequestBody Map<String,Object> map){
        try {

            System.err.println(map+"%%%%%%%%%%");
            Integer rid = (Integer) map.get("rid");
            ArrayList<Integer> mids = (ArrayList<Integer>) map.get("mids");//从map中获取参数


            //先删除根据rid  menu_role表
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("rid", rid);

            menuRoleService.remove(wrapper);
            //再加中间表

            MenuRole menuRole = new MenuRole();
            for (Integer mid : mids) {
                if(mid!=1){//保存的时候,去掉顶级节点
                    menuRole.setMid(mid);
                    menuRole.setRid(rid);
                    menuRoleService.save(menuRole);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}

