package com.arborwoodshop.controller;

import com.arborwoodshop.model.Item;
import com.arborwoodshop.repository.ItemRepository;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@EnableMethodSecurity
public class ViewController {

    private final ItemRepository itemRepository;

    private static List<Item> cachedItems = new ArrayList<>();

    public ViewController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        List<Item> stash = itemRepository.findAll();
        cachedItems.addAll(stash);
    }


    @GetMapping(value="/")
    public String index(Model model){

        List<Item> items = new ArrayList<>();

        if(cachedItems.isEmpty()){
            cachedItems.addAll(itemRepository.findAll());
            items.addAll(cachedItems);
        }  else {
            items.addAll(cachedItems);
        }

        model.addAttribute("items", items);
        return "index";
    }


    @GetMapping(value={"/city", "/city/{city}"})
    public String displayItemsByCity(){
        return "local-listings";
    }


    @GetMapping(value="/item/{id}")
    public String displayItemById(Model model, @PathVariable Long id){
        Item item = itemRepository.findById(id).orElse(new Item());
        model.addAttribute("item", item);
        return "item";

    }


    /********   Footer content  *******/
    @GetMapping(value={"/about"})
    public String about(){
        return "footer/about";
    }
    @GetMapping(value={"/avoiding-scams"})
    public String avoidingScams(){
        return "footer/avoiding-scams";
    }
    @GetMapping(value={"/abuse"})
    public String abuse(){
        return "footer/abuse";
    }
    @GetMapping(value={"/terms-of-use"})
    public String termsOfUse(){
        return "footer/terms-of-use";
    }



}
