package com.wxainn.springsecurity.oauth2.repository;

import com.wxainn.springsecurity.oauth2.model.RoleResource;
import org.springframework.data.repository.CrudRepository;

/**
 * 角色——资源Repository
 *
 * @author 王晓安
 */
public interface RoleResourceRepository extends CrudRepository<RoleResource, Integer> {
}
