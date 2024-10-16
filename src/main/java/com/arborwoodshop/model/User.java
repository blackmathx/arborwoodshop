package com.arborwoodshop.model;

import java.time.LocalDateTime;
import java.util.Objects;

// TODO add a field for admin to deactivate/remove the user and it's listings
public class User {

    private Long userId;
    private String username;
    private String email;
    private String password;
    private Boolean enabled;
    private Boolean sellerActive;
    private LocalDateTime sellerActiveDate;
    private LocalDateTime sellerExpireDate;
    private LocalDateTime createdDate;
    private String roles;

    public User(){}

    public User(String username, String email, String password, Boolean enabled, LocalDateTime sellerActiveDate, LocalDateTime sellerExpireDate, LocalDateTime createdDate, Boolean sellerActive, String roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.sellerActive = sellerActive;
        this.sellerActiveDate = sellerActiveDate;
        this.sellerExpireDate = sellerExpireDate;
        this.createdDate = createdDate;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getSellerActive() {
        return sellerActive;
    }

    public void setSellerActive(Boolean sellerActive) {
        this.sellerActive = sellerActive;
    }

    public LocalDateTime getSellerActiveDate() {
        return sellerActiveDate;
    }

    public void setSellerActiveDate(LocalDateTime sellerActiveDate) {
        this.sellerActiveDate = sellerActiveDate;
    }

    public LocalDateTime getSellerExpireDate() {
        return sellerExpireDate;
    }

    public void setSellerExpireDate(LocalDateTime sellerExpireDate) {
        this.sellerExpireDate = sellerExpireDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return userId.equals(user.userId) && Objects.equals(username, user.username) && email.equals(user.email) && createdDate.equals(user.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email, createdDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enabled='" + enabled + '\'' +
                ", sellerActive=" + sellerActive +
                ", sellerActiveDate=" + sellerActiveDate +
                ", sellerExpireDate=" + sellerExpireDate +
                ", created=" + createdDate +
                ", roles='" + roles + '\'' +
                '}';
    }
}
