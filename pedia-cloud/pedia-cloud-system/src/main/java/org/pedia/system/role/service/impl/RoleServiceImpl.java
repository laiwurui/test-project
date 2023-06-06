package org.pedia.system.role.service.impl;

import org.pedia.system.common.service.MybatisPlusGenericService;
import org.pedia.system.role.entity.Role;
import org.pedia.system.role.mapper.RoleMapper;
import org.pedia.system.role.service.IResourceService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends MybatisPlusGenericService<RoleMapper, Role> implements IResourceService {
}
