package org.pedia.system.user.service.impl;

import lombok.AllArgsConstructor;
import org.pedia.system.common.service.MybatisPlusGenericService;
import org.pedia.system.user.entity.User;
import org.pedia.system.user.mapper.UserMapper;
import org.pedia.system.user.service.IUserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl extends MybatisPlusGenericService<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

}
