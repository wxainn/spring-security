package com.wxainn.springsecurity.oauth2.repository;

import com.wxainn.springsecurity.oauth2.model.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 * 资源Repository
 *
 * @author 王晓安
 */
public interface ResourceRepository extends CrudRepository<Resource, Integer> {
}
