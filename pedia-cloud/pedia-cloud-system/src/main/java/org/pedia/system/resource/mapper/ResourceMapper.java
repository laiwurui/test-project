package org.pedia.system.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pedia.system.resource.entity.Resource;

import java.util.List;

@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    List<Resource> getResourceByUserId(@Param("userId") String userId);

}
