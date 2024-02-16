package com.arborwoodshop.controller;

import com.arborwoodshop.model.User;
import com.arborwoodshop.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public LoginController(UserRepository userRepository, PasswordEncoder encoder){
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @GetMapping(value="")
    public String login(){
        return "/login/user-login";
    }
    @GetMapping(value="/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "/login/user-register";
    }

    @PostMapping(path="/register-user") // Map ONLY POST Requests
    public String addNewUser (@ModelAttribute("user") User user) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        user.setRoles("ROLE_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
    ///////////////////
    @GetMapping(value="/login-success")
    public String loginSuccess(){ System.out.println("login-success url"); return "/user"; }

    @GetMapping(value="/logout-url")
    public String logoutUrl(){
        return "/index";
    }
/////////////////

}
