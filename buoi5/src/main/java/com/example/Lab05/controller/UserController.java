package com.example.Lab05.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.service.annotation.PatchExchange;

import com.example.Lab05.models.User;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
public class UserController {
    List<User> ListUser = new ArrayList<>();

    public UserController() {
        User u1 = new User("1", "Duc Tien", "tien@gmail.com");
        User u2 = new User("2", "Duc Tien", "tien@gmail.com");
        ListUser.add(u1);
        ListUser.add(u2);
    }
    // Get all Users    
    @GetMapping("/users")
    @ResponseBody
    public List<User> getListUsers() {
        return ListUser;
    }

    // Get user by id
    @GetMapping("users/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserbyId(@PathVariable("id") String userId) {
        for (User user : ListUser) {
            if (user.getId().equals(userId)) {
                return ResponseEntity.status(200).body(user);
            }
        }
        return ResponseEntity.status(404).body(null);
    }
    
    // create new user
    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        ListUser.add(newUser);
        return ResponseEntity.status(201).body(newUser);
    }
    // put one user
    // @PutMapping("/users/{id}")
    // public String putUser(@PathVariable String id, @RequestBody String userId) {
    //     //TODO: process PUT request
        
    //     return userId;
    // }
}