package com.gaofei.sysmanager.service;

import com.gaofei.sysmanager.domain.City;
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
public interface ICityService extends IService<City> {

    List<City> getCitys(int i);
}
