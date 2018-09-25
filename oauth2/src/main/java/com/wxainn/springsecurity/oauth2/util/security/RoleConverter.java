package com.wxainn.springsecurity.oauth2.util.security;

import com.wxainn.springsecurity.oauth2.model.Role;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色转换器
 *
 * @author 王晓安
 */
public class RoleConverter {

    /**
     * 将角色集合转换为{@link Collection<SecurityConfig>}返回
     *
     * @param roles 角色集合
     * @return 角色对应的安全属性配置
     */
    public static Collection<ConfigAttribute> toSecurityConfig(List<Role> roles) {
        return roles.stream()
                .map(role -> new SecurityConfig("ROLE_" + role.getName()))
                .collect(Collectors.toSet());
    }
}
