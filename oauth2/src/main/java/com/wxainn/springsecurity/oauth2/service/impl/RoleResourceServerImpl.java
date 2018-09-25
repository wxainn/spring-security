package com.wxainn.springsecurity.oauth2.service.impl;

import com.wxainn.springsecurity.oauth2.model.RoleResource;
import com.wxainn.springsecurity.oauth2.repository.RoleResourceRepository;
import com.wxainn.springsecurity.oauth2.service.RoleResourceService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.keyvalue.core.IterableConverter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色——资源service实现类
 *
 * @author 王晓安
 */
@Service
public class RoleResourceServerImpl implements RoleResourceService {

    private RoleResourceRepository roleResourceRepository;

    public RoleResourceServerImpl(RoleResourceRepository roleResourceRepository) {
        this.roleResourceRepository = roleResourceRepository;
    }

    @Override
    @Cacheable(value = "com:wxainn:security:param:role_resource")
    public List<RoleResource> findAll() {
       return IterableConverter.toList(roleResourceRepository.findAll());
    }
}
