package com.arborwoodshop.data_access;

import com.arborwoodshop.model.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepo {

    private final JdbcTemplate jdbcTemplate;

    public MessageRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Message> findByUserId(Long id){
        String sql = "select * from message where user_id = ?";
        return jdbcTemplate.query(sql, new MessageMapper(), id);
    }

    public Integer create(Message message){
        String sql = "insert into message (message, contact_email, contact_phone, user_id)" +
                "values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql);
    }

    public int delete(Long id){
        String sql = "delete from message where id = ?";
        return jdbcTemplate.update(sql);
    }





}

