package com.wxainn.springsecurity.oauth2.service;

import com.wxainn.springsecurity.oauth2.model.RoleResource;

import java.util.List;

/**
 * 角色——资源service
 *
 * @author 王晓安
 */
public interface RoleResourceService {
    /**
     * 找出数据库中所有角色——资源
     *
     * @return 数据库中所有角色——资源
     */
    List<RoleResource> findAll();
}
