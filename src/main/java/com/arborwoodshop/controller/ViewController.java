package com.arborwoodshop.controller;

import com.arborwoodshop.model.Item;
import com.arborwoodshop.repository.ItemRepository;
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

    private final ItemRepository itemRepository;

    public ViewController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        // TODO implement some sort of in memory caching
    }

//    @GetMapping("/")
//    public String hold(Model model){
//        return "hold";
//    }

    @GetMapping(value = "/")
    public String index(HttpServletRequest request, Model model) {
        logger.info("PAGE VIEW:/index " + request.getRemoteAddr());

        List<Item> items = itemRepository.findAll();

        model.addAttribute("items", items);
        return "index";
    }


    // TODO remove /city mapping. blocked because there are no cities
    @GetMapping(value = {"/city", "/city/{city}"})
    public String displayItemsByCity(HttpServletRequest request) {
        logger.info("PAGE VIEW:/city " + request.getRemoteAddr());
        return "local-listings";
    }

    @GetMapping(value = "/item/{id}")
    public String displayItemById(HttpServletRequest request, Model model, @PathVariable Long id) {
        logger.info("PAGE VIEW:/item/" + id + " " + request.getRemoteAddr());

        Item item = itemRepository.findById(id).orElse(new Item());
        model.addAttribute("item", item);
        return "item";
    }

    @GetMapping(value = {"/about"})
    public String about(HttpServletRequest request) {
        logger.info("PAGE VIEW:/about " + request.getRemoteAddr());
        return "about";
    }

    @GetMapping(value = {"/avoiding-scams"})
    public String avoidingScams(HttpServletRequest request) {
        logger.info("PAGE VIEW:/avoiding-scams " + request.getRemoteAddr());
        return "avoiding-scams";
    }

    @GetMapping(value = {"/abuse"})
    public String abuse(HttpServletRequest request) {
        logger.info("PAGE VIEW:/abuse " + request.getRemoteAddr());
        return "abuse";
    }

    @GetMapping(value = {"/terms-of-use"})
    public String termsOfUse(HttpServletRequest request) {
        logger.info("PAGE VIEW:/terms-of-use " + request.getRemoteAddr());
        return "terms-of-use";
    }


}
