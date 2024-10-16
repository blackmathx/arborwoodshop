package com.arborwoodshop.persistence;

import com.arborwoodshop.model.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepo {

    String FIND_ALL_MESSAGES_BY_USER_ID = """
        SELECT * FROM message WHERE user_id = ?
        """;
    String CREATE_MESSAGE = """
        INSERT INTO message (message, contact_email, contact_phone, user_id)
        VALUES (?, ?, ?, ?)
        """;
    String DELETE_MESSAGE_BY_ID = """
        DELETE FROM message WHERE id = ?
        """;

    private final JdbcTemplate jdbcTemplate;

    public MessageRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Message> findByUserId(Long id){
        return jdbcTemplate.query(FIND_ALL_MESSAGES_BY_USER_ID, new MessageMapper(), id);
    }

    public Integer create(Message message){
        return jdbcTemplate.update(CREATE_MESSAGE);
    }

    public int delete(Long id){
        return jdbcTemplate.update(DELETE_MESSAGE_BY_ID);
    }

}

