package com.arborwoodshop.controller;

import com.arborwoodshop.model_dto.ListingDisplay;
import com.arborwoodshop.model_dto.MessageDetail;
import com.arborwoodshop.persistence.ListingRepo;
import com.arborwoodshop.persistence.MessageRepo;
import com.arborwoodshop.model.SecurityUser;
import com.arborwoodshop.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ListingRepo listingRepo;
    private final MessageRepo messageRepo;

    public UserController(ListingRepo listingRepo, MessageRepo messageRepo) {
        this.listingRepo = listingRepo;
        this.messageRepo = messageRepo;
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = {"", "/"})
    public String index(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        return "redirect:/user/dashboard";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = {"/dashboard"})
    public String user(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        if(securityUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            return "redirect:/admin/admin-dashboard";
        }

        Long id = securityUser.getId();
        boolean isSeller = securityUser.getSellerActive();

        if(!isSeller){
            return "redirect:/user/account-settings";
        }

        List<ListingDisplay> listings = listingRepo.findListingDisplayItemsByUserId(id);

        model.addAttribute("isSeller", isSeller);
        model.addAttribute("username", securityUser.getUsername());
        model.addAttribute("listings", listings);

        return "user/dashboard";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/messages")
    public String userMessages(Model model, @AuthenticationPrincipal SecurityUser securityUser) {

        Long userId = securityUser.getId();

        List<MessageDetail> messages = messageRepo.findMessagesForUserId(userId);

        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());
        model.addAttribute("messages", messages);
        return "user/messages";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/account-settings")
    public String userPreferences(Model model, @AuthenticationPrincipal SecurityUser securityUser) {

        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());
        return "user/account-settings";
    }
}
