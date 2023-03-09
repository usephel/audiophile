package com.audiophileproject.usermanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.audiophileproject.usermanagement.repos.RoleRepository;;
import com.audiophileproject.usermanagement.models.Role;
import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepo;

    @Autowired
    public RoleService(RoleRepository roleRepo){
        this.roleRepo = roleRepo;
    }

    public List<Role> getRoles(){
        return roleRepo.findAll();
    }

    public Role createRole(@RequestBody Role role){
        return roleRepo.save(role);
    }
    
}
