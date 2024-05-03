package com.arborwoodshop.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestApiController {

    public record Something (String something){}

    @GetMapping("api/testing")
    public Something findall(){
        System.out.println("api/testing");
        Something sth = new Something("hello!");
        return sth;
    }
}
