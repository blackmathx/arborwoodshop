package com.arborwoodshop.service;

import com.arborwoodshop.model.User;
import com.arborwoodshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public User create(String email, String password){
        User user = new User(null, email, password, true, null, null, LocalDateTime.now(), false, "ROLE_USER");
        user = userRepo.save(user);
        return user;
    }

    public User findById(Long id){
        Optional<User> user = userRepo.findById(id);
        return user.orElse(null);
    }

    public boolean userExistsByEmail(String email){
        Optional<User> user = userRepo.findByEmail(email);
        return user.isPresent();
    }

    public void delete(){
    }

    public void update(){
    }
}
