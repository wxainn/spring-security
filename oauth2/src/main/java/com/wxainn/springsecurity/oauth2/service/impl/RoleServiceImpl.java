package com.wxainn.springsecurity.oauth2.service.impl;

import com.wxainn.springsecurity.oauth2.model.Role;
import com.wxainn.springsecurity.oauth2.repository.RoleRepository;
import com.wxainn.springsecurity.oauth2.service.RoleService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.keyvalue.core.IterableConverter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色service实现类
 *
 * @author 王晓安
 */
@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Cacheable(value = "com:wxainn:security:param:role")
    public List<Role> findAll() {
        return IterableConverter.toList(roleRepository.findAll());
    }
}
