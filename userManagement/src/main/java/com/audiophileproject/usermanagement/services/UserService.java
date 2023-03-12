package com.audiophileproject.usermanagement.services;

import com.audiophileproject.usermanagement.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    private  final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

}
