package com.gaofei.sysmanager.service.impl;

import com.gaofei.sysmanager.domain.Menu;
import com.gaofei.sysmanager.mapper.MenuMapper;
import com.gaofei.sysmanager.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaofei
 * @since 2021-04-27
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getTree(Integer id) {
        return menuMapper.getTree(id);
    }

    @Override
    public List<Menu> findMenusByUid(Integer uid,Integer parentId) {
        List<Menu>  menus = menuMapper.findMenusByUid(uid,parentId);

        return  menus;
    }

    @Override
    public List<Menu> findMenusByIds(List<Integer> mids) {
        List<Menu> menus = new ArrayList<>();
        for (Integer mid : mids) {
            Menu menu = menuMapper.selectById(mid);
            menus.add(menu);

        }
        return menus;
    }
}
