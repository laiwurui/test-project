package org.pedia.system.role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pedia.system.role.entity.Role;
import org.pedia.system.role.mapper.RoleMapper;
import org.pedia.system.role.service.IResourceService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IResourceService {
}
