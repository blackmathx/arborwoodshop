package com.arborwoodshop;

import com.arborwoodshop.model.Listing;
import com.arborwoodshop.model.User;
import com.arborwoodshop.repository.ListingRepository;
import com.arborwoodshop.repository.UserRepository;
import com.arborwoodshop.service.BatchApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

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
	CommandLineRunner commandLineRunner(UserRepository userRepo, ListingRepository listingRepo, PasswordEncoder encoder){
		return args -> {
			//System.out.println(encoder.encode("pw"));
			//Optional<Listing> listing = listingRepo.findById(1L);
			//listing.ifPresent(i -> System.out.println(i.getUser()));





			Optional<User> u = userRepo.findByEmail("ozzie@gmail.com");
			u.ifPresent( i -> {
				for(Listing j : i.getListings()){
					System.out.println(j.getTitle() + ":  " + j.getDescription());
				}
			});
		};
	}


}
