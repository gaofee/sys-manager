package com.gaofei.sysmanager.service.impl;

import com.gaofei.sysmanager.domain.User;
import com.gaofei.sysmanager.mapper.UserMapper;
import com.gaofei.sysmanager.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaofei
 * @since 2021-04-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
