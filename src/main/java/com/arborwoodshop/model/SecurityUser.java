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

    private User user;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // BO 5/4/24 Custom method to check member status. Appears to persist in SecurityContextHolder.getContext() at login
    public boolean getMemberStatus(){
        boolean flag = false;
        String email = user.getEmail();
        boolean isAdminUser = email.equals("admin@arborwoodshop.com");
        if(isAdminUser){
            flag = true;
        }
        if(!flag){
            flag = user.getMemberStatus();
        }
        return flag;
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // BO 5/4/24 Check account is enabled. was returning true;
        return user.getEnabled();
    }

}
