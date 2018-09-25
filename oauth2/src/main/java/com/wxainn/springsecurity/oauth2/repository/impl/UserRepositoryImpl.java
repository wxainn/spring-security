package com.wxainn.springsecurity.oauth2.repository.impl;

import com.wxainn.springsecurity.oauth2.model.User;
import com.wxainn.springsecurity.oauth2.repository.UserRepositoryPlus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户Repository实现
 *
 * @author 王晓安
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryPlus {

    private JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username=?";
        Object[] args = new Object[]{username};
        List<User> list = jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(User.class));
        return list.isEmpty() ? null : list.get(0);
    }
}
