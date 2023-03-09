package com.audiophileproject.usermanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.audiophileproject.usermanagement.models.User;
import com.audiophileproject.usermanagement.repos.UserRepository;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public User createUser(User user){
        return userRepo.save(user);
    }

    public User updateUser(User user){
        return userRepo.save(user);
    }
    public void deleteUser(Long id){
        userRepo.deleteById(id);;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }


    public List<User> getDumpUsers(){
        return List.of(
            new User(1L,"mehdi.hali@mail.com","mehdi hali","1234"),
            new User(2L,"noor.hali@gmail.com","noor hali","1234"),
            new User(3L,"hossam.hali@gmail.com","hossam hali","1234"),
            new User(4L,"mail","username","pass1234")
        ); 
    }
}
