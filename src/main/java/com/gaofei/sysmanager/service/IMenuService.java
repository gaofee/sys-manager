package com.gaofei.sysmanager.service;

import com.gaofei.sysmanager.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gaofei
 * @since 2021-04-27
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> getTree(Integer id);

    List<Menu> findMenusByUid(Integer uid,Integer parentId);

    List<Menu> findMenusByIds(List<Integer> mids);
}
