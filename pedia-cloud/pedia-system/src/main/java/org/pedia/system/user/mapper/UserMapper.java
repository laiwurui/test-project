package org.pedia.system.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.pedia.system.user.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
