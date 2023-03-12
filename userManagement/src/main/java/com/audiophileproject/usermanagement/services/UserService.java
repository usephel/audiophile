package com.audiophileproject.usermanagement.services;

import com.audiophileproject.usermanagement.models.User;
import com.audiophileproject.usermanagement.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private  final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found !"));
    }
}
