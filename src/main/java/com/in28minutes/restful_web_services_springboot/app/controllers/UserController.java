package com.in28minutes.restful_web_services_springboot.app.controllers;

import com.in28minutes.restful_web_services_springboot.app.ErrorHandling.UserNotFoundException;
import com.in28minutes.restful_web_services_springboot.app.UseDaoService;
import com.in28minutes.restful_web_services_springboot.app.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UseDaoService userDaoService;

    @Autowired
    public UserController(UseDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveUserById(@PathVariable int id) {
        User user =  userDaoService.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("id" + id);
        }
        return user;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userDaoService.deleteById(id);
    }
}
