package com.arborwoodshop.controller;

import com.arborwoodshop.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
public class UserControllerAPI {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public record Something (String something){}

    private final S3Service s3Service;

    @Value("${spring.profiles.active}")
    private String environment;

    public UserControllerAPI(S3Service s3Service){
        this.s3Service = s3Service;
    }


//    @GetMapping("api/testing")
//    public Something list(){
//        System.out.println("api/testing");
//        return new Something("hello arbor woodshop!");
//    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/user/api/get-upload-url")
    public String getPresignedUrl(@RequestParam String type, @RequestParam String size){
        // TODO resize and compress the images before uploading them to S3
        /*
            Here are some tips for image sizes for Craigslist uploads:
                1. Image size: Images on Craigslist should be no larger than 2 MB in size.
                2. File size: Images that are larger than 20 MB can significantly slow down a website.
                   Aspect ratio: A 3:2 aspect ratio, or 1200 x 800, displays without padding. However, the industry standard is 1200 x 627.
         */

        double imageSize = Double.parseDouble(size) * 0.000001; // in MB

        logger.debug("image type: {}", type);
        logger.debug("image size: {}MB", imageSize);

        if(imageSize > 1.0){
            logger.info("image size > 1.0MB: {}MB", imageSize);
            return "null";
        }

        return s3Service.generatePresignedUrl(type);
    }



}
