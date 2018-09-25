package com.wxainn.springsecurity.oauth2.security;

import com.wxainn.springsecurity.oauth2.model.Resource;
import com.wxainn.springsecurity.oauth2.model.Role;
import com.wxainn.springsecurity.oauth2.service.ResourceService;
import com.wxainn.springsecurity.oauth2.service.RoleService;
import com.wxainn.springsecurity.oauth2.util.security.RoleConverter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 权限资源SecurityMetadataSource
 *
 * @author 王晓安
 */
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private ResourceService resourceService;
    private RoleService roleService;

    public CustomSecurityMetadataSource(ResourceService resourceService, RoleService roleService) {
        this.resourceService = resourceService;
        this.roleService = roleService;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        List<Resource> resources = resourceService.findAll();
        if (!CollectionUtils.isEmpty(resources)) {
            FilterInvocation fi = (FilterInvocation) object;
            HttpServletRequest request = fi.getRequest();
            Map<Resource, List<Role>> resourceRoleMap = resourceService.groupRoleByResourceId(resources);
            for (Map.Entry<Resource, List<Role>> entry : resourceRoleMap.entrySet()) {
                RequestMatcher matcher = new AntPathRequestMatcher(entry.getKey().getValue());
                if (matcher.matches(request)) {
                    return RoleConverter.toSecurityConfig(entry.getValue());
                }
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        List<Role> roles = roleService.findAll();
        return RoleConverter.toSecurityConfig(roles);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
