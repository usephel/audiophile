package com.audiophileproject.usermanagement.services;

import com.audiophileproject.usermanagement.models.User;
import com.audiophileproject.usermanagement.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

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

    /**
     * if user exist update it, else create it
     * @param user
     * @return
     */
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String username){
        userRepository.deleteByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found !"));
    }
}
