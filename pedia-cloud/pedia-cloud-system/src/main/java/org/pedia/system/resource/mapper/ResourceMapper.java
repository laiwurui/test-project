package org.pedia.system.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.pedia.system.resource.entity.Resource;

@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
}
