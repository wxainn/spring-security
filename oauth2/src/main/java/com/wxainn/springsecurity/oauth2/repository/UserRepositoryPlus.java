package com.wxainn.springsecurity.oauth2.repository;

import com.wxainn.springsecurity.oauth2.model.User;

/**
 * 用户Repository扩展
 *
 * @author 王晓安
 */
public interface UserRepositoryPlus {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 根据用户名查询到的用户
     */
    User findByUsername(String username);
}
