package com.arborwoodshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BatchApplicationService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void runner() throws InterruptedException {

        logger.info("batch running...");
        //Thread.sleep(120000);
        System.out.println("batch running.... " + LocalDateTime.now());
    }

}
