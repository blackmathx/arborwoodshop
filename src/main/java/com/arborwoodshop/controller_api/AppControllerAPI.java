package com.arborwoodshop.controller_api;

import com.arborwoodshop.persistence.MessageRepo;
import com.arborwoodshop.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
public class AppControllerAPI {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final S3Service s3Service;
    private final MessageRepo messageRepo;
    @Value("${spring.profiles.active}")
    private String environment;

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

        System.out.println(requestBody);

        messageRepo.create(4L, 3L, 19L, "api created at " + System.currentTimeMillis());
        System.out.println("MESSAGE SENT...");
    }


}
