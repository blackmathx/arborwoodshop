package com.arborwoodshop.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/* Coding creation reference, dvega at https://www.youtube.com/watch?v=awcCiqBO36E
 * titled Spring Security JPA Authentication in Spring Boot from 2023
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AppUserDetails appUserDetails;

    public SecurityConfig(AppUserDetails appUserDetails) {
        this.appUserDetails = appUserDetails;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // without it the logout has to be a POST to /logout
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/user").authenticated() // BO 5/4/24 Comment out bc the controller requires auth
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(appUserDetails)
                .logout((logout) -> logout.logoutSuccessUrl("/"))
                .formLogin(form -> form.loginPage("/login")
                        // BO 5/3/24 Replaced deafultSuccessUrl with custom success handler
                        //.defaultSuccessUrl("/user/dashboard")
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
                                response.sendRedirect("/login/login-success");
                            }
                        })
                        .usernameParameter("email").permitAll())
                .build();
    }

    // Defines the password encoder used when verifying users.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
