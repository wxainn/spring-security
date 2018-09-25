package com.wxainn.springsecurity.oauth2.repository;

import com.wxainn.springsecurity.oauth2.model.Role;

import java.util.List;

/**
 * 角色Repository扩展
 *
 * @author 王晓安
 */
public interface RoleRepositoryPlus {
    /**
     * 查询用户的所有角色，以集合返回
     *
     * @param userId 用户的id
     * @return 该用户角色集合
     */
    List<Role> findByUserId(int userId);
}
