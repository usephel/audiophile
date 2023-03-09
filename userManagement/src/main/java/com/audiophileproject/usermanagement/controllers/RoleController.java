package com.audiophileproject.usermanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.audiophileproject.usermanagement.models.Role;
import com.audiophileproject.usermanagement.services.RoleService;
import java.util.List;

@RestController
@RequestMapping("api/role")
public class RoleController {

    private RoleService roleService;


    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getRoles(){
        return roleService.getRoles(); 
    }
    
}