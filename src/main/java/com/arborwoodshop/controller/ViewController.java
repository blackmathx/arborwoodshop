package com.arborwoodshop.controller;

import com.arborwoodshop.model_dto.ListingDetailDisplay;
import com.arborwoodshop.model_dto.ListingDisplay;
import com.arborwoodshop.persistence.ListingRepo;
import com.arborwoodshop.model.SecurityUser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.profiles.active}")
    private String environment;

    public ViewController(ListingRepo listingRepo) {
        this.listingRepo = listingRepo;
    }

    @GetMapping("/hold")
    public String hold(Model model, HttpServletRequest request){
        String visitor = request.getRemoteAddr();
        return "hold";
    }

    @GetMapping(value = "/")
    public String index(Model model, @AuthenticationPrincipal SecurityUser securityUser) {

        boolean isSeller = securityUser != null && securityUser.getSellerActive();
        model.addAttribute("isSeller", isSeller);
        return "index";
    }

    /*
     * Default site links from index page
     */
    @GetMapping(value = {"/marketplace/browse/site"})
    public String chooseLocation(HttpServletRequest request){

        if(request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals("site")) {
                    return "redirect:/marketplace/browse?site=" + c.getValue();
                }
            }
        }
        return "index-site";
    }


    @GetMapping(value = {"/marketplace/browse"})
    public String displayListingsByState(@RequestParam(name="site", required=false) String siteUrlParam,
                HttpServletRequest request, HttpServletResponse response,
                Model model, @AuthenticationPrincipal SecurityUser securityUser){

        // TODO fix the naming of variables between city and city-from-cookie
        String siteCookieValue = null;

        if(siteUrlParam == null){
            if(request.getCookies() != null) {
                for (Cookie c : request.getCookies()) {
                    if (c.getName().equals("site")) {
                        siteCookieValue = c.getValue();
                    }
                }
            }
            if(siteCookieValue == null){
                logger.debug("location parameters is null. redirecting");
                return "redirect:/marketplace/browse/site";
            }
        } else {
            // get location

            // set cookie to new location
            Cookie siteCookie = new Cookie("site", siteUrlParam);
            siteCookie.setMaxAge(86400 * 365);
            response.addCookie(siteCookie);
        }


        // TODO query for listings from the city that is selected
        List<ListingDisplay> listings = listingRepo.findListingDisplayItems();

        boolean isSeller = securityUser != null && securityUser.getSellerActive();

        model.addAttribute("listings", listings);
        model.addAttribute("isSeller", isSeller);

        return "local-listings";
    }

    @GetMapping(value = "/marketplace/browse/listing")
    public String displayListingById(Model model, @RequestParam("listing") Long listingId, @AuthenticationPrincipal SecurityUser securityUser) {

        ListingDetailDisplay listing = listingRepo.findListingDetailItemById(listingId);
        boolean isSeller = securityUser != null && securityUser.getSellerActive();
        Long userId = securityUser != null ? securityUser.getId() : null;

        String apiUrl = environment.equals("prod") ? "https://arborwoodshop.com/message" : "http://localhost:8080/message";
        model.addAttribute("api_url", apiUrl);
        model.addAttribute("userId", userId);
        model.addAttribute("listing", listing);
        model.addAttribute("isSeller", isSeller);
        return "listing";
    }

    // TODO unused???
//    @PostMapping(value = "/marketplace/listing/{id}/message")
//    public String sendMessage(Model model, Message message, @AuthenticationPrincipal SecurityUser securityUser){
//       // int i = messageRepo.create(message);
//        boolean isSeller = securityUser != null && securityUser.getSellerActive();
//        model.addAttribute("isSeller", isSeller);
//        return "listing";
//    }


    @GetMapping(value = "/become-a-seller")
    public String userBecomeSeller(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        boolean isSeller = securityUser != null && securityUser.getSellerActive();
        model.addAttribute("isSeller", isSeller);
        return "become-a-seller";
    }





    /* ----------------- Service Pages ----------------------------------------------- */
    @GetMapping(value = {"/about"})
    public String about(HttpServletRequest request) {
        return "service-about";
    }
    @GetMapping(value = {"/avoiding-scams"})
    public String avoidingScams(HttpServletRequest request) {
        return "service-avoiding-scams";
    }
    @GetMapping(value = {"/abuse"})
    public String abuse(HttpServletRequest request) {
        return "service-abuse";
    }
    @GetMapping(value = {"/terms-of-use"})
    public String termsOfUse(HttpServletRequest request) {
        return "service-terms-of-use";
    }
}
