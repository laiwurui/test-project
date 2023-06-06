package org.pedia.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.pedia.system.common.service.MybatisPlusGenericService;
import org.pedia.system.user.dto.UserDTO;
import org.pedia.system.user.entity.User;
import org.pedia.system.user.entity.UserRole;
import org.pedia.system.user.mapper.UserMapper;
import org.pedia.system.user.service.IUserRoleService;
import org.pedia.system.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl extends MybatisPlusGenericService<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

    private final IUserRoleService userRoleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        // todo dept_id -> permission
        this.save(user);
        List<String> roleIds = userDTO.getRoleIds();
        saveUserRoles(roleIds, user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        this.updateById(user);
        List<String> roleIds = userDTO.getRoleIds();
        userRoleService.remove(new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, userDTO.getId()));
        saveUserRoles(roleIds, userDTO.getId());
    }

    private void saveUserRoles(List<String> roleIds, String userId) {
        List<UserRole> userRoles = roleIds.stream()
                .map(id ->
                        UserRole.builder().userId(userId).roleId(id).build()
                )
                .collect(Collectors.toList());
        userRoleService.saveBatch(userRoles);
    }
}
