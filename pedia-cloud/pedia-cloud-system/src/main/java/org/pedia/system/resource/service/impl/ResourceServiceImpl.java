package org.pedia.system.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pedia.system.resource.entity.Resource;
import org.pedia.system.resource.mapper.ResourceMapper;
import org.pedia.system.resource.service.IResourceService;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {
}
