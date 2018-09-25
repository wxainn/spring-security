package com.wxainn.springsecurity.oauth2.service;

import com.wxainn.springsecurity.oauth2.model.Resource;
import com.wxainn.springsecurity.oauth2.model.Role;

import java.util.List;
import java.util.Map;

/**
 * 资源service
 *
 * @author 王晓安
 */
public interface ResourceService {
    /**
     * 列出所有资源
     *
     * @return 所有资源的集合
     */
    List<Resource> findAll();

    /**
     * 按资源分组角色，列出每个资源所需要的角色集合
     *
     * @param resources 资源集合
     * @return 每个资源所需要的角色集合
     */
    Map<Resource, List<Role>> groupRoleByResourceId(List<Resource> resources);
}
