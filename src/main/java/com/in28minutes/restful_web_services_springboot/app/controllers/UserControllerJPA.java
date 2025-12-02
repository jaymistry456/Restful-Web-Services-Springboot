package com.in28minutes.restful_web_services_springboot.app.controllers;

import com.in28minutes.restful_web_services_springboot.app.ErrorHandling.PostNotFoundException;
import com.in28minutes.restful_web_services_springboot.app.ErrorHandling.UserNotFoundException;
import com.in28minutes.restful_web_services_springboot.app.Post;
import com.in28minutes.restful_web_services_springboot.app.User;
import com.in28minutes.restful_web_services_springboot.app.jpa.PostJPARepository;
import com.in28minutes.restful_web_services_springboot.app.jpa.UserJPARepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserControllerJPA {
    private UserJPARepository userJPARepository;
    private PostJPARepository postJPARepository;

    @Autowired
    public UserControllerJPA(UserJPARepository userJPARepository, PostJPARepository postJPARepository) {
        this.userJPARepository = userJPARepository;
        this.postJPARepository = postJPARepository;
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

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getPostsForUser(@PathVariable int id) {
        Optional<User> user = userJPARepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id=" + id);
        }
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public void createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userJPARepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id=" + id);
        }
        post.setUser(user.get());
        postJPARepository.save(post);
    }

    @DeleteMapping("/jpa/users/{userId}/posts/{postId}")
    public void deletePostForUser(@PathVariable int userId, @PathVariable int postId) {
        Optional<Post> post = postJPARepository.findById(postId);
        if(post.isEmpty()) {
            throw new PostNotFoundException("id=" + postId);
        }
        if(!post.get().getUser().getId().equals(userId)) {
            throw new RuntimeException("The post does not belong to the user!");
        }
        postJPARepository.deleteById(postId);
    }
}
