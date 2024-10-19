package com.arborwoodshop.persistence;

import com.arborwoodshop.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserRepo {

    private final String FIND_BY_USER_ID = """
        SELECT user_id, username, email, password, enabled, seller_active, created_at, seller_active_date, seller_expire_date, roles
        FROM users WHERE user_id = ?
        """;
    private final String FIND_BY_USER_EMAIL = """
        SELECT user_id, username, email, password, enabled, seller_active, created_at, seller_active_date, seller_expire_date, roles
        FROM users WHERE email = ?
        """;
    private final String CREATE_USER = """
        INSERT INTO users (username, email, password, enabled, seller_active, seller_active_date, seller_expire_date, created_at, roles)
        VALUES (?,?,?,?,?,?,?,?,?)
        """;

    private final JdbcTemplate jdbcTemplate;

    public UserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findById(Long userId) {
        return jdbcTemplate.queryForObject(FIND_BY_USER_ID, new UserMapper(), userId);
    }

    public User findByEmail(String email) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(FIND_BY_USER_EMAIL, new UserMapper(), email);
        } catch (EmptyResultDataAccessException ignored) { }
        return user;
    }

    public int create(String email, String password) {
        return jdbcTemplate.update(CREATE_USER, null, email, password, 1, 1, null, null, LocalDateTime.now(), "ROLE_USER");
    }


}
