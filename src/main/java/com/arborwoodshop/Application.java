package com.arborwoodshop;

import com.arborwoodshop.service.BatchApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EnableScheduling
public class Application {

	@Autowired
	BatchApplicationService batchService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Scheduled(initialDelay = 10000, fixedDelay = 3600000) // milliseconds, 3600000 = 1 hour
//	public void batchRunner() throws InterruptedException{
//		batchService.runner();
//	}

	@Bean
	CommandLineRunner commandLineRunner(PasswordEncoder encoder){
		return args -> {
			//System.out.println(encoder.encode("pw"));

		};
	}


}
