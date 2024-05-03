package com.arborwoodshop;

import com.arborwoodshop.model.Item;
import com.arborwoodshop.model.User;
import com.arborwoodshop.repository.ItemRepository;
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

	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepo, ItemRepository itemRepo){
		PasswordEncoder encoder = new BCryptPasswordEncoder();

		return args -> {

		};
	}


}
