package com.gaofei.sysmanager.mapper;

import com.gaofei.sysmanager.domain.Nation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 城市字典表 Mapper 接口
 * </p>
 *
 * @author gaofei
 * @since 2021-04-27
 */
public interface NationMapper extends BaseMapper<Nation> {

    List<Nation> getNations(int parentId);
}
