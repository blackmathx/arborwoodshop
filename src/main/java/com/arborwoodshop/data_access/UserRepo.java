package com.arborwoodshop.data_access;

import com.arborwoodshop.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserRepo {

    private final JdbcTemplate jdbcTemplate;

    public UserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findById(Long id) {
        String sql = "SELECT id, username, email, password, enabled, seller_status, created_date, seller_active_date, seller_expire_date, roles FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    public User findByEmail(String email) {
        User user = null;
        try {
            String sql = "SELECT id, username, email, password, enabled, seller_status, created_date, seller_active_date, seller_expire_date, roles FROM users WHERE email = ?";
            user = jdbcTemplate.queryForObject(sql, new UserMapper(), email);
        } catch (EmptyResultDataAccessException ignored) { }
        return user;
    }

    public int create(String email, String password) {
        String sql = "INSERT INTO users (username, email, password, enabled, seller_status, seller_active_date, seller_expire_date, created_date, roles) values(?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, null, email, password, 1, 0, null, null, LocalDateTime.now(), "ROLE_USER");
    }


}
