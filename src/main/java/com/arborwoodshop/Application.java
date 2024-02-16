package com.arborwoodshop;

import com.arborwoodshop.model.User;
import com.arborwoodshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		openHomePage();
	}

	private static void openHomePage() {
		try {
			Runtime rt = Runtime.getRuntime();
			rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080");
		} catch (Exception e){
			System.out.println(e);
		}
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepo){
		PasswordEncoder encoder = new BCryptPasswordEncoder();

		return args -> {
			if (userRepo.findByUsername("Bo").isEmpty()) {
				userRepo.save(new User("Dan Vega", encoder.encode("password"), "danvega@gmail.com", "ROLE_USER"));
				userRepo.save(new User("Bo", encoder.encode("pw"), "bo@gmail.com", "ROLE_ADMIN, ROLE_USER"));
			}
		};
	}


}
