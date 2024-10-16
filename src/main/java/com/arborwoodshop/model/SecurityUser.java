package com.arborwoodshop.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * The SecurityUser is for our User Details Service we created.
 * It implements UserDetails so that User doesn't have to.
 * Learned from dvega at https://www.youtube.com/watch?v=awcCiqBO36E
 * titled Spring Security JPA Authentication in Spring Boot from 2023
 *
 */
public class SecurityUser implements UserDetails {

    private final User user;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
    @Override  public boolean isEnabled() {
        return user.getEnabled();
    }

    // BO 5/5/24 Return user id
    public Long getId(){
        return user.getUserId();
    }

    // BO 5/4/24 Custom method to check seller active. Appears to persist in SecurityContextHolder.getContext() at login
    public boolean getSellerActive(){
        return user.getSellerActive();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(user.getRoles()
                .split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override  public boolean isAccountNonExpired() { return true; }
    @Override  public boolean isAccountNonLocked() { return true; }
    @Override  public boolean isCredentialsNonExpired() { return true; }


}
