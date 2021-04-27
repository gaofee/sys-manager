package com.gaofei.sysmanager.service;

import com.gaofei.sysmanager.domain.Nation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 城市字典表 服务类
 * </p>
 *
 * @author gaofei
 * @since 2021-04-27
 */
public interface INationService extends IService<Nation> {

    List<Nation> getNations(int i);
}
