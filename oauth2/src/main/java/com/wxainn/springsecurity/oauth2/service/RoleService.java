package com.wxainn.springsecurity.oauth2.service;

import com.wxainn.springsecurity.oauth2.model.Role;

import java.util.List;

/**
 * 角色service
 *
 * @author 王晓安
 */
public interface RoleService {
    /**
     * 列出所有角色
     *
     * @return 所有角色
     */
    List<Role> findAll();
}
