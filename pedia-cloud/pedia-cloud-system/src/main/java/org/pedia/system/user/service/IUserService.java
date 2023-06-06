package org.pedia.system.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pedia.system.user.dto.UserDTO;
import org.pedia.system.user.entity.User;

public interface IUserService extends IService<User> {

    void add(UserDTO userDTO);

    void edit(UserDTO userDTO);
}
