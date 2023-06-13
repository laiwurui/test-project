package org.pedia.system.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.pedia.starter.security.SecurityUser;
import org.pedia.system.resource.entity.Resource;
import org.pedia.system.resource.service.IResourceService;
import org.pedia.system.user.entity.User;
import org.pedia.system.user.service.IUserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Component
public class SystemUserDetailsService implements UserDetailsService {

    private final IUserService userService;

    private final IResourceService resourceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        User user = userService.getOne(wrapper);
        if(user == null) {
            return null;
        }
        List<Resource> resources = resourceService.getResourceByUserId(user.getId());
        String[] authorities = resources.stream().map(Resource::getAuthority).filter(Objects::nonNull).toArray(String[]::new);
        return new SecurityUser(username,
                user.getPassword(),
                user.getEnabled() == 1,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList(authorities));
    }

}
