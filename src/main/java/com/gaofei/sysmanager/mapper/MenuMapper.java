package com.gaofei.sysmanager.mapper;

import com.gaofei.sysmanager.domain.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gaofei
 * @since 2021-04-27
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getTree(Integer mid);
    List<Menu> findMenusByUid(@Param("uid") Integer uid, @Param("parentId") Integer parentId);
}
