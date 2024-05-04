package com.arborwoodshop.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @Column(length = 1)
    private String recordStatus;
    private LocalDateTime activeDate;
    private LocalDateTime expireDate;
    private LocalDateTime created;
    private String roles;

    public User(){}

    public User(String username, String password, String email, String recordStatus, LocalDateTime activeDate, LocalDateTime expireDate, LocalDateTime created, String roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.recordStatus = recordStatus;
        this.activeDate = activeDate;
        this.expireDate = expireDate;
        this.created = created;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public LocalDateTime getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(LocalDateTime activeDate) {
        this.activeDate = activeDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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
        return id.equals(user.id) && Objects.equals(username, user.username) && email.equals(user.email) && created.equals(user.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, created);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", recordStatus='" + recordStatus + '\'' +
                ", active=" + activeDate +
                ", expires=" + expireDate +
                ", created=" + created +
                ", roles='" + roles + '\'' +
                '}';
    }
}
