package com.gaofei.sysmanager.mapper;

import com.gaofei.sysmanager.domain.City;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gaofei
 * @since 2021-04-27
 */
public interface CityMapper extends BaseMapper<City> {

    List<City> getCitys(int pid);
}
