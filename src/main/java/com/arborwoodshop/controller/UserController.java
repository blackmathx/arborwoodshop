package com.arborwoodshop.controller;

import com.arborwoodshop.model.Listing;
import com.arborwoodshop.model.SecurityUser;
import com.arborwoodshop.model.User;
import com.arborwoodshop.repository.ListingRepository;
import com.arborwoodshop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ListingRepository listingRepo;
    private final UserRepository userRepo;

    public UserController(ListingRepository listingRepo, UserRepository userRepo){
        this.listingRepo = listingRepo;
        this.userRepo = userRepo;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = {"", "/dashboard", "/saved-listings"})
    public String user(Model model, @AuthenticationPrincipal SecurityUser user) {

        model.addAttribute("username", user.getUsername());
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
    public String userListings(Model model, @AuthenticationPrincipal SecurityUser user) {

        Optional<User> u = userRepo.findById(user.getId());
        List<Listing> listings = new ArrayList<>();
        u.ifPresent(i -> listings.addAll(i.getListings()));
        model.addAttribute("username", user.getUsername());
        model.addAttribute("listings", listings);
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
    public String userCreateListing(Model model, Listing listing, Principal principal) {
        model.addAttribute("username", principal.getName());

        model.addAttribute("listing", listing);
        return "user/user-create-listing";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/listings/create-listing")
    public String createListing(@ModelAttribute("listing") Listing listing, Model model,@AuthenticationPrincipal SecurityUser user) {

//        Optional<User> user = userRepo.findByEmail(userDetails.getUsername());
//        user.ifPresent(listing::setUser);
//        if(listing.getUser() == null){
//            logger.error("Create Listing. User is null. Username:{}, ListingTitle:{}", userDetails.getUsername(), listing.getTitle());
//            return "redirect:/user/dashboard";
//        }


        Long id = user.getId();
        User u = new User();
        u.setId(id);
        listing.setUser(u);

        listingRepo.save(listing);

        return "redirect:/user/listings";
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
