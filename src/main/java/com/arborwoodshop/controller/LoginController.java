package com.arborwoodshop.controller;

import com.arborwoodshop.model.User;
import com.arborwoodshop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public LoginController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @GetMapping(value = {"", "/"})
    public String login() {
        return "security/login";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
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
        if(!userRepository.findByEmail(user.getEmail()).isEmpty()) {
            model.addAttribute("errorMessage", "User email already exists.");
            return "security/register";
        }

        user.setUsername(null);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setMemberActiveDate(null);
        user.setMemberExpireDate(null);
        user.setMemberStatus(false);
        user.setRoles("ROLE_USER");

        userRepository.save(user);

        model.addAttribute("registrationSuccess", "Your account has been created! Please login.");
        return "security/login";
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/login-success")
    public String loginSuccess(HttpServletRequest request) {

        String userEmail = request.getUserPrincipal().getName();
        String address = request.getRemoteAddr();

        if(userEmail.equals("admin@arborwoodshop.com")){
            return "redirect:/admin/dashboard";
        }

        logger.info("LOGGING IN: {} {}", address, userEmail);

        return "redirect:/user/dashboard";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/account-logout")
    public String logoutUrl(HttpServletRequest request) {
        logger.info("LOGGING OUT: {}", request.getUserPrincipal().getName());
        return "redirect:/logout";
    }

}
