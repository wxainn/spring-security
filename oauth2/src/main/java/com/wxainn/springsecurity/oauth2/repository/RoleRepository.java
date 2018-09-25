package com.wxainn.springsecurity.oauth2.repository;

import com.wxainn.springsecurity.oauth2.model.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * 角色Repository
 *
 * @author 王晓安
 */
public interface RoleRepository extends CrudRepository<Role, Integer>, RoleRepositoryPlus {
}
