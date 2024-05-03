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

import java.util.ArrayList;
import java.util.List;


@Controller
@EnableMethodSecurity
public class ViewController {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    private final ItemRepository itemRepository;
    //private static final List<Item> cachedItems = new ArrayList<>();

    public ViewController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        // TODO limit caching to an item limit
        //List<Item> stash = itemRepository.findAll();
        //cachedItems.addAll(stash);
    }

//    @GetMapping("/")
//    public String hold(Model model){
//        return "hold";
//    }

    @GetMapping(value = "/")
    public String index(HttpServletRequest request, Model model) {
        logger.debug("*********A DEBUG Message");
        logger.info("**********An INFO Message");
        logger.warn("**********A WARN Message");
        logger.error("**********An ERROR Message");

        //System.out.println(request.getRemoteAddr());

        List<Item> items = new ArrayList<>();
        items = itemRepository.findAll();
//        if (cachedItems.isEmpty()) {
//            // TODO limit caching to an item limit
//            cachedItems.addAll(itemRepository.findAll());
//            items.addAll(cachedItems);
//        } else {
//            items.addAll(cachedItems);
//        }
        model.addAttribute("items", items);
        return "index";
    }

    // TODO remove /city mapping. blocked because there are no cities
    @GetMapping(value = {"/city", "/city/{city}"})
    public String displayItemsByCity() {
        return "local-listings";
    }

    @GetMapping(value = "/item/{id}")
    public String displayItemById(Model model, @PathVariable Long id) {
        Item item = itemRepository.findById(id).orElse(new Item());
        model.addAttribute("item", item);
        return "item";
    }

    @GetMapping(value = {"/about"})
    public String about() {
        return "about";
    }

    @GetMapping(value = {"/avoiding-scams"})
    public String avoidingScams() {
        return "avoiding-scams";
    }

    @GetMapping(value = {"/abuse"})
    public String abuse() {
        return "abuse";
    }

    @GetMapping(value = {"/terms-of-use"})
    public String termsOfUse() {
        return "terms-of-use";
    }


}
