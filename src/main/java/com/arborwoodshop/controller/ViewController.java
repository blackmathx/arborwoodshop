package com.arborwoodshop.controller;

import com.arborwoodshop.data_access.ListingRepo;
import com.arborwoodshop.data_access.MessageRepo;
import com.arborwoodshop.model.Listing;
import com.arborwoodshop.model.SecurityUser;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@EnableMethodSecurity
public class ViewController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ListingRepo listingRepo;
    private final MessageRepo messageRepo;

    public ViewController(ListingRepo listingRepo, MessageRepo messageRepo) {
        this.listingRepo = listingRepo;
        this.messageRepo = messageRepo;
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

    @GetMapping(value = {"/marketplace/browse"})
    public String displayListingsByState(Model model, @AuthenticationPrincipal SecurityUser securityUser){

        List<Listing> listings = listingRepo.findAll();
        boolean isSeller = securityUser != null && securityUser.getSellerStatus();

        model.addAttribute("listings", listings);
        model.addAttribute("isSeller", isSeller);

        return "local-listings";
    }

    @GetMapping(value = "/marketplace/browse/listing")
    public String displayListingById(Model model, @RequestParam("listing") Long id, @AuthenticationPrincipal SecurityUser securityUser) {
        Listing listing = listingRepo.findById(id);

        boolean isSeller = securityUser != null && securityUser.getSellerStatus();

        model.addAttribute("listing", listing);
        model.addAttribute("isSeller", isSeller);
        return "listing";
    }

//    @PostMapping(value = "/marketplace/listing/{id}/message")
//    public String sendMessage(Model model, Message message, @AuthenticationPrincipal SecurityUser securityUser){
//        int i = messageRepo.create(message);
//        boolean isSeller = securityUser != null && securityUser.getSellerStatus();
//        model.addAttribute("isSeller", isSeller);
//        return "listing";
//    }

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
