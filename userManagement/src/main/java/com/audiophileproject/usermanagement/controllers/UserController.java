package com.audiophileproject.usermanagement.controllers;

import com.audiophileproject.usermanagement.models.User;
import com.audiophileproject.usermanagement.models.UserResponse;
import com.audiophileproject.usermanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping
    public UserResponse getCurrentUser(){
     User currUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

     return UserResponse.builder()
             .email(currUser.getEmail())
             .username(currUser.getUsername())
             .firstname(currUser.getFirstname())
             .lastname(currUser.getLastname())
             .lastname(currUser.getLastname())
             .build();
    }

    @PutMapping
    public UserResponse updateUser(@RequestBody User user){
        User newUser = userService.updateUser(user).orElseThrow(()->new UsernameNotFoundException("User Not Found"));

        return UserResponse.builder()
                .email(newUser.getEmail())
                .username(newUser.getUsername())
                .firstname(newUser.getFirstname())
                .lastname(newUser.getLastname())
                .build();
    }
    @DeleteMapping("/{username}")
    public String deleteUser(@RequestParam String username){
        userService.deleteUser(username);
        return "user deleted successfully";
    }
}
