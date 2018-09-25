package com.wxainn.springsecurity.oauth2.repository;

import com.wxainn.springsecurity.oauth2.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户Repository
 *
 * @author 王晓安
 */
public interface UserRepository extends CrudRepository<User, Integer>, UserRepositoryPlus {
}
