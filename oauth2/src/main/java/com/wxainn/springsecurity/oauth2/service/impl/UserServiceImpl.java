package com.wxainn.springsecurity.oauth2.service.impl;

import com.wxainn.springsecurity.oauth2.model.Role;
import com.wxainn.springsecurity.oauth2.model.User;
import com.wxainn.springsecurity.oauth2.repository.RoleRepository;
import com.wxainn.springsecurity.oauth2.repository.UserRepository;
import com.wxainn.springsecurity.oauth2.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 用户Service实现类
 *
 * @author 王晓安
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("找不到该用户: " + username);
        }
        List<Role> roles = roleRepository.findByUserId(user.getId());
        user.setRoles(roles);
        return user;
    }
}
