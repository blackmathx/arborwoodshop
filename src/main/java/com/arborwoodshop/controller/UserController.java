package com.arborwoodshop.controller;

import com.arborwoodshop.model_dto.ListingDetailDisplay;
import com.arborwoodshop.model_dto.ListingDisplay;
import com.arborwoodshop.persistence.ListingRepo;
import com.arborwoodshop.persistence.MessageRepo;
import com.arborwoodshop.model.Message;
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

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ListingRepo listingRepo;
    private final MessageRepo messageRepo;
    private final S3Service s3Service;

    public UserController(ListingRepo listingRepo, MessageRepo messageRepo, S3Service s3Service) {
        this.listingRepo = listingRepo;
        this.messageRepo = messageRepo;
        this.s3Service = s3Service;
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

        model.addAttribute("isSeller", isSeller);
        model.addAttribute("username", securityUser.getUsername());

        List<ListingDisplay> listings = listingRepo.findListingDisplayItemsByUserId(id);

        model.addAttribute("listings", listings);

        return "user/dashboard";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/messages")
    public String userMessages(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        Long id = securityUser.getId();

        List<Message> messages = messageRepo.findByUserId(id);
        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());
        return "user/messages";
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/listings/create-listing")
    public String createListingCity(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());
        return "user/create-listing-city";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/listings/create-listing-title")
    public String createListingTitle(@ModelAttribute("city") String city, Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());
        model.addAttribute("city", city);
        return "user/create-listing-title";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/listings/create-listing-form")
    public String userCreateListing(ListingDetailDisplay listing, Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        logger.debug("LISTING TITLE: {}", listing.getTitle());
        logger.debug("LISTING CITY: {}", listing.getCity());

        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());

        model.addAttribute("listing", listing);

        return "user/create-listing-form";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/listings/create-listing")
    public String createListing(ListingDetailDisplay listing, Model model, @AuthenticationPrincipal SecurityUser securityUser) {

        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());

        // TODO add more field validations and display invalid error messages to the user on html/form
        // Validation again that should already be done on the client
        if(listing.getTitle().isBlank() || listing.getDescription().isBlank()){
            return "redirect:/user/dashboard";
        }

        Long userId = securityUser.getId();
        listing.setCreatedDate(LocalDateTime.now());
        listing.setUserId(userId);

        // Hardcoded/Default values for fields not yet in use
        listing.setDeliveryAvailable(false);
        listing.setShippingAvailable(false);
        listing.setState("IN");

        listingRepo.create(listing);

        return "redirect:/user/dashboard";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/listings/{id}") // DELETE method
    public String deleteListing(Model model, @PathVariable("id") Long listingId){
        logger.debug("DELETE LISTING ID: {}", listingId);

        // Delete listing images stored on S3
        ListingDetailDisplay listing = listingRepo.findListingDetailItemById(listingId);
        if(!listing.getImageOne().isBlank() && !listing.getImageOne().contains("localhost"))
            s3Service.deleteImageForListing(listing.getImageOne().substring(listing.getImageOne().lastIndexOf("/") + 1));
        if(!listing.getImageTwo().isBlank() && !listing.getImageTwo().contains("localhost"))
            s3Service.deleteImageForListing(listing.getImageTwo().substring(listing.getImageTwo().lastIndexOf("/") + 1));
        if(!listing.getImageThree().isBlank() && !listing.getImageThree().contains("localhost"))
            s3Service.deleteImageForListing(listing.getImageThree().substring(listing.getImageThree().lastIndexOf("/") + 1));


        listingRepo.delete(listingId);
        return "redirect:/user/dashboard";
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/listings/{id}")
    public String userListingById(Model model, @PathVariable("id") Long listingId, @AuthenticationPrincipal SecurityUser securityUser) {
        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());

        model.addAttribute("listing", listingRepo.findListingDetailItemById(listingId));

        return "user/user-listing";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/manage-subscription")
    public String userManageSubscription(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());
        return "user/manage-subscription";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/account-settings")
    public String userPreferences(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());
        return "user/account-settings";
    }
}
