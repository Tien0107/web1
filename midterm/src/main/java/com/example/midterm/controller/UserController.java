package com.example.midterm.controller;

// import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PatchMapping;

// import com.example.midterm.repository.UserRepository;
import com.example.midterm.service.UserService;
import com.example.midterm.model.User;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(){
    }

    // Get all Users    
    @GetMapping("/users")
    @ResponseBody
    public List<User> getUserList() {
        return userService.findAll();
    }

    // Get user by id
    @GetMapping("users/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserbyId(@PathVariable("id") int userId) {
        for (User user : userService.findAll()) {
            if (user.getId() == userId) {
                return ResponseEntity.status(200).body(user);
            }
        }
        return ResponseEntity.status(404).body(null);
    }
    
    // create new user
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        userService.save(newUser);
        return ResponseEntity.status(201).body(newUser);
    }
    //put
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody User updateuser){
        User user = userService.findById(userId);
        if (user != null ){
            user.setName(updateuser.getName());
            user.setEmail(updateuser.getEmail());
            userService.save(user);
            return ResponseEntity.status(200).body(user);
        }
        return ResponseEntity.status(404).body(null);
    }

    //patch
    @PatchMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<User> patchUser(@PathVariable("id") Long userId, @RequestBody User patchupdate){
        User user = userService.findById(userId);
        if (user != null ){
            if(patchupdate.getName() != null){
                user.setName(patchupdate.getName());
            }
            if(patchupdate.getEmail() != null){
                user.setEmail(patchupdate.getEmail());
            }
            userService.save(user);
            return ResponseEntity.status(200).body(user);
    }
    return ResponseEntity.status(404).body(null);

    }
     //delete
    @DeleteMapping("/users/{id}")
    @ResponseBody
    public List<User> removeUserById(@PathVariable("id") Long userId){
        userService.delete(userId);
        return userService.findAll();
    }
}