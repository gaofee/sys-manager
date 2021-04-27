package com.gaofei.sysmanager.controller;


import com.gaofei.sysmanager.domain.City;
import com.gaofei.sysmanager.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/city")
public class CityController {
    @Autowired
    ICityService cityService;

    @RequestMapping("list")
    public List<City> list(){
        List<City> citys = cityService.getCitys(1);
        return  citys;
    }
}

