package com.arborwoodshop.controller_api;

import com.arborwoodshop.persistence.MessageRepo;
import com.arborwoodshop.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
public class AppControllerAPI {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final S3Service s3Service;
    private final MessageRepo messageRepo;
//    @Value("${spring.profiles.active}")
//    private String environment;

    public AppControllerAPI(S3Service s3Service, MessageRepo messageRepo){
        this.s3Service = s3Service;
        this.messageRepo = messageRepo;
    }




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

        String statePath = "IN";
        String cityPath = "FORT-WAYNE";
        return s3Service.generatePresignedUrl(statePath, cityPath, type);
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @CrossOrigin(origins = "*")
    @PostMapping("/message")
    public void sendMessage(@RequestBody String requestBody){

        //String regex = "\\{|\\}";
        requestBody = requestBody.replaceAll("[{}]", "");

        String[] str = requestBody.split(",");
        String fromUserId = str[0].substring(str[0].indexOf(":") + 2, str[0].length()-1);
        String toUserId = str[1].substring(str[1].indexOf(":") + 2, str[1].length()-1);
        String listingId = str[2].substring(str[2].indexOf(":") + 2, str[2].length()-1);
        String message = str[3].substring(str[3].indexOf(":") + 2, str[3].length()-1);

        System.out.println(fromUserId + ", " + toUserId + ", " + listingId + ", " + message);

        messageRepo.create(Long.valueOf(fromUserId), Long.valueOf(toUserId), Long.valueOf(listingId), message);
        //messageRepo.create(Long.valueOf("3"), Long.valueOf("4"), Long.valueOf("19"), "test");

    }


}
