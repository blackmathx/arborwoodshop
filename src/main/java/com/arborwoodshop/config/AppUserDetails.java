package com.arborwoodshop.config;

import com.arborwoodshop.data_access.UserRepo;
import com.arborwoodshop.model.SecurityUser;
import com.arborwoodshop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class AppUserDetails implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepo userRepo;

    public AppUserDetails(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    // BO 5/9/24 Changed to the JDBC implementation
    // BO 5/1/24 Changed from login from username to email with fildByUsername to findByEmail and the parameter name
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("Email not found");
        }

        return new SecurityUser(user);


    }

}
