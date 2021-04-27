package com.gaofei.sysmanager.service.impl;

import com.gaofei.sysmanager.domain.Nation;
import com.gaofei.sysmanager.mapper.NationMapper;
import com.gaofei.sysmanager.service.INationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 城市字典表 服务实现类
 * </p>
 *
 * @author gaofei
 * @since 2021-04-27
 */
@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

    @Autowired
    NationMapper nationMapper;
    @Override
    public List<Nation> getNations(int parentId) {
        return nationMapper.getNations(parentId);
    }
}
