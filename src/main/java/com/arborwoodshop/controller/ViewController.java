package com.arborwoodshop.controller;

import com.arborwoodshop.model.SecurityUser;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@EnableMethodSecurity
public class ViewController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ViewController() {
        // TODO implement some sort of in memory caching for front page
    }

    @GetMapping("/hold")
    public String hold(Model model, HttpServletRequest request){
        String visitor = request.getRemoteAddr();
        return "hold";
    }

    @GetMapping(value = "/")
    public String index(HttpServletRequest request, Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        boolean isSeller = securityUser != null && securityUser.getSellerStatus();
        model.addAttribute("isSeller", isSeller);

        return "index";
    }

    @GetMapping(value = {"/marketplace/state/{state}"})
    public String displayListingsByState(@PathVariable("state") String state, Model model, @AuthenticationPrincipal SecurityUser securityUser){
        boolean isSeller = securityUser != null && securityUser.getSellerStatus();
        model.addAttribute("isSeller", isSeller);
        model.addAttribute("subtitle", state);
        return "local-listings";
    }

    @GetMapping(value = "/marketplace/listing/{id}")
    public String displayListingById(HttpServletRequest request, Model model, @PathVariable Long id, @AuthenticationPrincipal SecurityUser securityUser) {

        boolean isSeller = securityUser != null && securityUser.getSellerStatus();
        model.addAttribute("isSeller", isSeller);
        return "listing";
    }


    @GetMapping(value = "/become-a-seller")
    public String userBecomeSeller(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        boolean isSeller = securityUser != null && securityUser.getSellerStatus();
        model.addAttribute("isSeller", isSeller);
        return "become-a-seller";
    }


    /* ----------------- Service Pages ----------------------------------------------- */
    @GetMapping(value = {"/service/about"})
    public String about(HttpServletRequest request) {
        return "service/about";
    }
    @GetMapping(value = {"/service/avoiding-scams"})
    public String avoidingScams(HttpServletRequest request) {
        return "service/avoiding-scams";
    }
    @GetMapping(value = {"/service/abuse"})
    public String abuse(HttpServletRequest request) {
        return "service/abuse";
    }
    @GetMapping(value = {"/service/terms-of-use"})
    public String termsOfUse(HttpServletRequest request) {
        return "service/terms-of-use";
    }
}
