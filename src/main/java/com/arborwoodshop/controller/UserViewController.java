package com.arborwoodshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class UserViewController {

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = {"", "/dashboard", "/saved-items"})
    public String user(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "user/dashboard";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/account-settings")
    public String userPreferences(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "user/account-settings";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/messages")
    public String userMessages(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "user/messages";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/listings")
    public String userListings(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "user/listings";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/listings/{id}")
    public String userListingById(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "user/listings";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/listings/create-listing")
    public String userCreateListing(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "user/user-create-listing";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/manage-subscription")
    public String userManageSubscription(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "user/user-manage-subscription";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/become-a-seller")
    public String userBecomeSeller(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "user/become-a-seller";
    }


}
