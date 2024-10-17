package com.arborwoodshop.model_dto;

import java.time.LocalDateTime;

public class MessageDetail {

    private Long messageUserId;
    private Long messageUser_fromUserId;
    private Long messageUser_toUserId;
    private Long messageUser_messageId;
    private Long messageUser_listingId;

    private Long messageId;
    private String message;
    private char recordStatus;
    private LocalDateTime createdAt;

    public Long getMessageUserId() {
        return messageUserId;
    }

    public void setMessageUserId(Long messageUserId) {
        this.messageUserId = messageUserId;
    }

    public Long getMessageUser_fromUserId() {
        return messageUser_fromUserId;
    }

    public void setMessageUser_fromUserId(Long messageUser_fromUserId) {
        this.messageUser_fromUserId = messageUser_fromUserId;
    }

    public Long getMessageUser_toUserId() {
        return messageUser_toUserId;
    }

    public void setMessageUser_toUserId(Long messageUser_toUserId) {
        this.messageUser_toUserId = messageUser_toUserId;
    }

    public Long getMessageUser_messageId() {
        return messageUser_messageId;
    }

    public void setMessageUser_messageId(Long messageUser_messageId) {
        this.messageUser_messageId = messageUser_messageId;
    }

    public Long getMessageUser_listingId() {
        return messageUser_listingId;
    }

    public void setMessageUser_listingId(Long messageUser_listingId) {
        this.messageUser_listingId = messageUser_listingId;
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

    public char getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(char recordStatus) {
        this.recordStatus = recordStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
