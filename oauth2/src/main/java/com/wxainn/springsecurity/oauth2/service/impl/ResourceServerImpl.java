package com.wxainn.springsecurity.oauth2.service.impl;

import com.wxainn.springsecurity.oauth2.model.Resource;
import com.wxainn.springsecurity.oauth2.model.Role;
import com.wxainn.springsecurity.oauth2.model.RoleResource;
import com.wxainn.springsecurity.oauth2.repository.ResourceRepository;
import com.wxainn.springsecurity.oauth2.service.ResourceService;
import com.wxainn.springsecurity.oauth2.service.RoleResourceService;
import com.wxainn.springsecurity.oauth2.service.RoleService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.keyvalue.core.IterableConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 资源service实现类
 *
 * @author 王晓安
 */
@Service
public class ResourceServerImpl implements ResourceService {

    private ResourceRepository resourceRepository;
    private RoleResourceService roleResourceService;
    private RoleService roleService;

    public ResourceServerImpl(ResourceRepository resourceRepository,
                              RoleResourceService roleResourceService,
                              RoleService roleService) {
        this.resourceRepository = resourceRepository;
        this.roleResourceService = roleResourceService;
        this.roleService = roleService;
    }

    @Override
    @Cacheable(value = "com:wxainn:security:param:resource")
    public List<Resource> findAll() {
        return IterableConverter.toList(resourceRepository.findAll());
    }

    @Override
    public Map<Resource, List<Role>> groupRoleByResourceId(List<Resource> resources) {
        Assert.notEmpty(resources, "资源不可为空");
        Map<Resource, List<Role>> map = new HashMap<>(resources.size());
        List<Role> roles = roleService.findAll();
        List<RoleResource> roleResources = roleResourceService.findAll();
        resources.forEach(resource -> {
            List<Integer> availableRoleIds = roleResources.stream()
                    .filter(roleResource -> resource.getId() == roleResource.getResourceId())
                    .map(RoleResource::getRoleId)
                    .collect(Collectors.toList());
            List<Role> availableRoles = roles.stream()
                    .filter(role -> availableRoleIds.contains(role.getId()))
                    .collect(Collectors.toList());
            map.put(resource, availableRoles);
        });
        return map;
    }
}
