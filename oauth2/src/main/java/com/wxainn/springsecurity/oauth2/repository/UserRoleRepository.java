package com.wxainn.springsecurity.oauth2.repository;

import com.wxainn.springsecurity.oauth2.model.UserRole;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户——角色Repository
 *
 * @author 王晓安
 */
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
}
