package org.pedia.system.resource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pedia.system.resource.entity.Resource;

import java.util.List;

public interface IResourceService extends IService<Resource> {

    List<Resource> getResourceByUserId(String userId);
}
