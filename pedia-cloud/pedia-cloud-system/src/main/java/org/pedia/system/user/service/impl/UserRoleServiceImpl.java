package org.pedia.system.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pedia.system.user.entity.UserRole;
import org.pedia.system.user.mapper.UserRoleMapper;
import org.pedia.system.user.service.IUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
}
