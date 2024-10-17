package com.arborwoodshop.controller;

import com.arborwoodshop.model.SecurityUser;
import com.arborwoodshop.model_dto.ListingDetailDisplay;
import com.arborwoodshop.persistence.ListingRepo;
import com.arborwoodshop.persistence.MessageRepo;
import com.arborwoodshop.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/user/listings")
public class UserListingsController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ListingRepo listingRepo;
    private final S3Service s3Service;

    public UserListingsController(ListingRepo listingRepo, S3Service s3Service) {
        this.listingRepo = listingRepo;
        this.s3Service = s3Service;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/create-listing")
    public String createListingCity(Model model, @AuthenticationPrincipal SecurityUser securityUser) {

        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());
        return "user/create-listing-city";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/create-listing-title")
    public String createListingTitle(@ModelAttribute("city") String city, Model model, @AuthenticationPrincipal SecurityUser securityUser) {

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("city", city);
        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());
        return "user/create-listing-title";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/create-listing-form")
    public String userCreateListing(ListingDetailDisplay listing, Model model, @AuthenticationPrincipal SecurityUser securityUser) {

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        logger.debug("LISTING: TITLE {}, CITY {}", listing.getTitle(), listing.getCity());

        model.addAttribute("listing", listing);
        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());
        return "user/create-listing-form";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/create-listing")
    public String createListing(ListingDetailDisplay listing, Model model, @AuthenticationPrincipal SecurityUser securityUser) {

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

        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());
        return "redirect:/user/dashboard";
    }

    /*
     * GET method  at /{id} returns the owners listing for view/edit page
     */
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public String userListingById(Model model, @PathVariable("id") Long listingId, @AuthenticationPrincipal SecurityUser securityUser) {

        model.addAttribute("listing", listingRepo.findListingDetailItemById(listingId));
        model.addAttribute("isSeller", securityUser.getSellerActive());
        model.addAttribute("username", securityUser.getUsername());
        return "user/user-listing";
    }


    /*
     * POST method at /{id} to DELETE the listing
     */
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/{id}") // DELETE method
    public String deleteListing(Model model, @PathVariable("id") Long listingId){

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

}
