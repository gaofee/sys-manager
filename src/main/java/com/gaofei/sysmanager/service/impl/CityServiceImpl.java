package com.gaofei.sysmanager.service.impl;

import com.gaofei.sysmanager.domain.City;
import com.gaofei.sysmanager.mapper.CityMapper;
import com.gaofei.sysmanager.service.ICityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

    @Autowired
    CityMapper cityMapper;
    @Override
    public List<City> getCitys(int i) {
        return cityMapper.getCitys(i);
    }
}
