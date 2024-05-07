package com.arborwoodshop.controller;

import com.arborwoodshop.model.SecurityUser;
import com.arborwoodshop.model.User;
import com.arborwoodshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PasswordEncoder encoder;
    private final UserService userService;

    public LoginController(PasswordEncoder encoder, UserService userService) {
        this.encoder = encoder;
        this.userService = userService;
    }

    @GetMapping(value = {"", "/"})
    public String login() {
        return "security/login";
    }

    @GetMapping(value = "/register")
    public String register(Model model, User user) {
        model.addAttribute("user", user);
        return "security/register";
    }

    @PostMapping(path = "/register-user")
    public String addNewUser(@ModelAttribute("user") User user, Model model) {
        /*
         * TODO email verification
         * tutorial: https://www.codejava.net/frameworks/spring-boot/email-verification-example
         * and youtube vide at: https://www.youtube.com/watch?v=7mVTfnOIJO8
         * steps are to add verificationCode to User class, update UserDetails, add JavaMail in SpringBoot,
         *      update user registration for sending email,
         */


        if(user.getEmail().isBlank() || user.getPassword().isBlank()){
            model.addAttribute("errorMessage", "Invalid email or password.");
            return "security/register";
        }

        if(userService.userExistsByEmail(user.getEmail())) {
            model.addAttribute("errorMessage", "User email already exists.");
            return "security/register";
        }


        User savedUser = userService.create(user.getEmail(), encoder.encode(user.getPassword()));

        model.addAttribute("registrationSuccess", "Your account has been created! Please login.");
        return "security/login";
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/login-success")
    public String loginSuccess(HttpServletRequest request, @AuthenticationPrincipal SecurityUser securityUser) {
        if(securityUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            return "redirect:/admin/admin-dashboard";
        }
        String email = securityUser.getUsername();
        logger.info("LOGGING IN: {}", email);
        return "redirect:/user/dashboard";
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/account-logout")
    public String logoutUrl(HttpServletRequest request, @AuthenticationPrincipal SecurityUser securityUser) {
        String email = securityUser.getUsername();
        logger.info("LOGGING OUT: {}", email);
        return "redirect:/logout";
    }

}
