package com.arborwoodshop.app_config;

import com.arborwoodshop.service.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;


    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService){
        this.jpaUserDetailsService = jpaUserDetailsService;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // without it the logout has to be a POST to /logout
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user").authenticated()
                        .requestMatchers("/register").permitAll()
                        .anyRequest().permitAll())
                .userDetailsService(jpaUserDetailsService)

                //.logout(logout -> logout.logoutSuccessUrl("/logout-url"))
                //.logout(Customizer.withDefaults())
                .logout((logout) -> logout.logoutSuccessUrl("/"))

                //.formLogin(Customizer.withDefaults())
               .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/user").permitAll())

                .build();
    }

    /**
     * Defines the password encoder used when verifying users. Another option is a Bean with DoaAuthenticationProvider
     * and set the encoder there.
     */
    @Bean
    PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }


}
