package com.arborwoodshop.controller;

import com.arborwoodshop.model.Listing;
import com.arborwoodshop.repository.ListingRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@EnableMethodSecurity
public class ViewController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ListingRepository listingRepo;

    public ViewController(ListingRepository listingRepo) {
        this.listingRepo = listingRepo;
        // TODO implement some sort of in memory caching for front page
    }

//    @GetMapping("/")
//    public String hold(Model model){
//        return "hold";
//    }

    @GetMapping(value = "/")
    public String index(HttpServletRequest request, Model model) {
        //logger.info("PAGE VIEW:/index " + request.getRemoteAddr());

        List<Listing> listings = listingRepo.findAll();

        model.addAttribute("listings", listings);
        return "index";
    }

    // TODO Delete /city route
//    @GetMapping(value = {"/city", "/city/{city}"})
//    public String displayListingsByCity(HttpServletRequest request) {
//        return "local-listings";
//    }

    @GetMapping(value = {"/marketplace/state/{state}"})
    public String displayListingsByState(@PathVariable("state") String state, Model model){
        // TODO get listings by state
        model.addAttribute("subtitle", state);
        return "local-listings";
    }

    @GetMapping(value = "/marketplace/listing/{id}")
    public String displayListingById(HttpServletRequest request, Model model, @PathVariable Long id) {

        //Listing listing = listingRepo.findById(id).orElse(new Listing());
        //model.addAttribute("listing", listing);
        return "listing";
    }




    /* ----------------- Service Pages ----------------------------------------------- */

    @GetMapping(value = {"/about"})
    public String about(HttpServletRequest request) {
        //logger.info("PAGE VIEW:/about " + request.getRemoteAddr());
        return "about";
    }

    @GetMapping(value = {"/avoiding-scams"})
    public String avoidingScams(HttpServletRequest request) {
        //logger.info("PAGE VIEW:/avoiding-scams " + request.getRemoteAddr());
        return "avoiding-scams";
    }

    @GetMapping(value = {"/abuse"})
    public String abuse(HttpServletRequest request) {
        //logger.info("PAGE VIEW:/abuse " + request.getRemoteAddr());
        return "abuse";
    }

    @GetMapping(value = {"/terms-of-use"})
    public String termsOfUse(HttpServletRequest request) {
        //logger.info("PAGE VIEW:/terms-of-use " + request.getRemoteAddr());
        return "terms-of-use";
    }
}
