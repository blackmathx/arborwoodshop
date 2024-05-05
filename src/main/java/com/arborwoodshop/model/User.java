package com.arborwoodshop.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Listing> listings = new ArrayList<>();

    private Boolean memberStatus;
    private LocalDateTime memberActiveDate;
    private LocalDateTime memberExpireDate;
    private LocalDateTime createdDate;
    private String roles;

    public User(){}

    public User(String username, String email, String password, Boolean enabled, LocalDateTime memberActiveDate, LocalDateTime memberExpireDate, Boolean memberStatus, String roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;

        this.memberStatus = memberStatus;
        this.memberActiveDate = memberActiveDate;
        this.memberExpireDate = memberExpireDate;
        this.createdDate = LocalDateTime.now();
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

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    public Boolean getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Boolean memberStatus) {
        this.memberStatus = memberStatus;
    }

    public LocalDateTime getMemberActiveDate() {
        return memberActiveDate;
    }

    public void setMemberActiveDate(LocalDateTime memberActiveDate) {
        this.memberActiveDate = memberActiveDate;
    }

    public LocalDateTime getMemberExpireDate() {
        return memberExpireDate;
    }

    public void setMemberExpireDate(LocalDateTime memberExpireDate) {
        this.memberExpireDate = memberExpireDate;
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
        return id.equals(user.id) && Objects.equals(username, user.username) && email.equals(user.email) && createdDate.equals(user.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, createdDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enabled='" + enabled + '\'' +
                ", memberActiveDate=" + memberActiveDate +
                ", memberExpireDate=" + memberExpireDate +
                ", memberStatus=" + memberStatus +
                ", created=" + createdDate +
                ", roles='" + roles + '\'' +
                '}';
    }
}
