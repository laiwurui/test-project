package org.pedia.system.resource.service.impl;

import lombok.AllArgsConstructor;
import org.pedia.system.common.service.MybatisPlusGenericService;
import org.pedia.system.resource.entity.Resource;
import org.pedia.system.resource.mapper.ResourceMapper;
import org.pedia.system.resource.service.IResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResourceServiceImpl extends MybatisPlusGenericService<ResourceMapper, Resource> implements IResourceService {

    private final ResourceMapper resourceMapper;

    @Override
    public List<Resource> getResourceByUserId(String userId) {
        return resourceMapper.getResourceByUserId(userId);
    }
}
