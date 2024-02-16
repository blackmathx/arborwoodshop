package com.arborwoodshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableMethodSecurity
public class ViewController {

    @GetMapping(value="/")
    public String index(){
        return "/index";
    }




}
