package com.audiophileproject.usermanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.audiophileproject.usermanagement.models.User;
import com.audiophileproject.usermanagement.services.UserService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(path="api/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("create")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathParam("id") Long id){
        userService.deleteUser(id);
    }

    @PutMapping("update")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }


}
