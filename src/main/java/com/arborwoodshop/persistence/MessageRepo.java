package com.arborwoodshop.persistence;

import com.arborwoodshop.model_dto.MessageDetail;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public class MessageRepo {

    String FIND_MESSAGES_FOR_USER_ID = """
        SELECT
            message_user_xref.message_user_id,
            message_user_xref.message_id AS xref_message_id,
            message_user_xref.from_user_id,
            message_user_xref.to_user_id,
            message_user_xref.listing_id,
            message.message_id AS message_message_id,
            message.message, message.record_status, message.created_at
        FROM message_user_xref
        LEFT JOIN message ON message_user_xref.message_id = message.message_id
        WHERE message_user_xref.to_user_id = ?
        AND record_status = 'A'
        """;

    String CREATE_MESSAGE = """
            INSERT INTO message (message, record_status, created_at)
            VALUES (?, ?, ?)
            """;
    String CREATE_MESSAGE_USER_XREF = """
        INSERT INTO message_user_xref (from_user_id, to_user_id, message_id, listing_id)
        VALUES (?, ?, ?, ?)
        """;

    String DELETE_MESSAGE_USER_XREF = """
            DELETE FROM message_user_xref WHERE message_user_id = ?
            """;
    String DELETE_MESSAGE = """
            DELETE FROM message WHERE message_id = ?
            """;

    private final JdbcTemplate jdbcTemplate;

    public MessageRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<MessageDetail> findMessagesForUserId(Long user_id){
        return jdbcTemplate.query(FIND_MESSAGES_FOR_USER_ID, new MessageDetailMapper(), user_id);
    }

    public int create(Long fromUserId, Long toUserId, Long listingId, String message){

        // insert into message and get generated key
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_MESSAGE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, message);
            ps.setString(2, "A");
            ps.setObject(3, LocalDateTime.now());
            return ps;
        }, keyHolder);

        long createdMessageId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        // insert into message_user_xref
        return jdbcTemplate.update(CREATE_MESSAGE_USER_XREF, fromUserId, toUserId, createdMessageId, listingId);

    }

    public int delete(Long messageUserId, Long messageId){

        int xrefRows = jdbcTemplate.update(DELETE_MESSAGE_USER_XREF, messageUserId);
        int msgRows = jdbcTemplate.update(DELETE_MESSAGE, messageId);

        return 0;
    }


//    String CREATE_MESSAGE = """
//        INSERT INTO message (message, contact_email, contact_phone, user_id)
//        VALUES (?, ?, ?, ?)
//        """;
//    String DELETE_MESSAGE_BY_ID = """
//        DELETE FROM message WHERE id = ?
//        """;
//

//
//    public Integer create(Message message){
//        return jdbcTemplate.update(CREATE_MESSAGE, message.getMessage(), message.getContactEmail(), message.getContactPhone(), message.getUserId());
//    }
//
//    public int delete(Long id){
//        return jdbcTemplate.update(DELETE_MESSAGE_BY_ID);
//    }



}

