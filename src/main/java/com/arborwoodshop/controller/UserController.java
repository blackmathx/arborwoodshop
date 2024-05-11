package com.arborwoodshop.controller;

import com.arborwoodshop.data_access.ListingRepo;
import com.arborwoodshop.data_access.MessageRepo;
import com.arborwoodshop.data_access.UserRepo;
import com.arborwoodshop.model.Listing;
import com.arborwoodshop.model.Message;
import com.arborwoodshop.model.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @GetMapping(value = {"", "/dashboard"})
    public String user(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        if(securityUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            return "redirect:/admin/admin-dashboard";
        }

        Long id = securityUser.getId();
        List<Listing> listings = listingRepo.findByUserId(id);

        model.addAttribute("listings", listings);

        model.addAttribute("isSeller", securityUser.getSellerStatus());
        model.addAttribute("username", securityUser.getUsername());
        return "user/dashboard";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/account-settings")
    public String userPreferences(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        model.addAttribute("isSeller", securityUser.getSellerStatus());
        model.addAttribute("username", securityUser.getUsername());
        return "user/account-settings";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/messages")
    public String userMessages(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        Long id = securityUser.getId();

        List<Message> messages = messageRepo.findByUserId(id);
        model.addAttribute("isSeller", securityUser.getSellerStatus());
        model.addAttribute("username", securityUser.getUsername());
        return "user/messages";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/listings")
    public String userListings(Model model, @AuthenticationPrincipal SecurityUser securityUser) {

        Long id = securityUser.getId();
        List<Listing> listings = listingRepo.findByUserId(id);

        model.addAttribute("isSeller", securityUser.getSellerStatus());
        model.addAttribute("username", securityUser.getUsername());
        model.addAttribute("listings", listings);
        return "user/listings";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/listings/{id}")
    public String userListingById(Model model, @PathVariable Long id, @AuthenticationPrincipal SecurityUser securityUser) {
        model.addAttribute("isSeller", securityUser.getSellerStatus());
        model.addAttribute("username", securityUser.getUsername());
        return "user/listings";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/listings/create-listing")
    public String userCreateListing(Model model, Listing listing, @AuthenticationPrincipal SecurityUser securityUser) {
        model.addAttribute("isSeller", securityUser.getSellerStatus());
        model.addAttribute("username", securityUser.getUsername());
        model.addAttribute("listing", listing);
        return "user/user-create-listing";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/listings/create-listing")
    public String createListing(@ModelAttribute("listing") Listing listing, Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        Long userId = securityUser.getId();
        listing.setCreatedDate(LocalDateTime.now());
        listing.setUserId(userId);
        listingRepo.create(listing);

        model.addAttribute("isSeller", securityUser.getSellerStatus());
        model.addAttribute("username", securityUser.getUsername());
        return "redirect:/user/listings";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/manage-subscription")
    public String userManageSubscription(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        model.addAttribute("isSeller", securityUser.getSellerStatus());
        model.addAttribute("username", securityUser.getUsername());
        return "user/user-manage-subscription";
    }
}
