package com.example.libraryrole.controller;

import com.example.libraryrole.dao.RoleDao;
import com.example.libraryrole.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleRestController {

    @Autowired
    RoleDao roleDao;

    @GetMapping(value = "/role/{id}")
    public Role getRole(@PathVariable int id){
        return roleDao.findById(id);
    }


}
