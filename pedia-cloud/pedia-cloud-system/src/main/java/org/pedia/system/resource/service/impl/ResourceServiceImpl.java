package org.pedia.system.resource.service.impl;

import org.pedia.system.common.service.MybatisPlusGenericService;
import org.pedia.system.resource.entity.Resource;
import org.pedia.system.resource.mapper.ResourceMapper;
import org.pedia.system.resource.service.IResourceService;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl extends MybatisPlusGenericService<ResourceMapper, Resource> implements IResourceService {
}
