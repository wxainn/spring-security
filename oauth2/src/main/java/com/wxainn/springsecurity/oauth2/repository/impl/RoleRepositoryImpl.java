package com.wxainn.springsecurity.oauth2.repository.impl;

import com.wxainn.springsecurity.oauth2.model.Role;
import com.wxainn.springsecurity.oauth2.repository.RoleRepositoryPlus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色Repository实现类
 *
 * @author 王晓安
 */
@Repository
public class RoleRepositoryImpl implements RoleRepositoryPlus {

    private JdbcTemplate jdbcTemplate;

    public RoleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> findByUserId(int userId) {
        String sql = "" +
                "SELECT r.* FROM role r " +
                "INNER JOIN user_role ur " +
                "ON r.id=ur.roleId " +
                "WHERE ur.userId=? ";
        Object[] args = new Object[]{userId};
        return jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(Role.class));
    }
}
