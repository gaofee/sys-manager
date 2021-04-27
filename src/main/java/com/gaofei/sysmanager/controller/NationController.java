package com.gaofei.sysmanager.controller;


import com.gaofei.sysmanager.domain.Nation;
import com.gaofei.sysmanager.service.INationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 城市字典表 前端控制器
 * </p>
 *
 * @author gaofei
 * @since 2021-04-27
 */
@RestController
@RequestMapping("/nation")
public class NationController {

    @Autowired
    INationService nationService;

    @RequestMapping("list")
    public List<Nation> list(){
        List<Nation>  nations = nationService.getNations(0);
        return nations;
    }
}

