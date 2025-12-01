package com.in28minutes.restful_web_services_springboot.app01.controllers;

import com.in28minutes.restful_web_services_springboot.app01.ErrorHandling.UserNotFoundException;
import com.in28minutes.restful_web_services_springboot.app01.User;
import com.in28minutes.restful_web_services_springboot.app01.jpa.UserJPARepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserControllerJPA {
    private UserJPARepository userJPARepository;

    @Autowired
    public UserControllerJPA(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    @GetMapping("/jpa/users")
    public List<User> getUsers() {
        return userJPARepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        Optional<User> user = userJPARepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id=" + id);
        }
        return user;
    }

    @PostMapping("/jpa/users")
    public void createUser(@RequestBody @Valid User user) {
        userJPARepository.save(user);
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        userJPARepository.deleteById(id);
    }
}
