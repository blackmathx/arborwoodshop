package com.arborwoodshop.model;

import java.time.LocalDateTime;

public class Message {

    private Long messageId;
    private String message;
    private char record_status;
    private LocalDateTime created_at;

    public Message(){

    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public char isRecord_status() {
        return record_status;
    }

    public void setRecord_status(char record_status) {
        this.record_status = record_status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
