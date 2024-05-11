package com.arborwoodshop.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiController {

    public record Something (String something){}

    @GetMapping("api/testing")
    public Something list(){
        System.out.println("api/testing");
        return new Something("hello arbor woodshop!");
    }
}
