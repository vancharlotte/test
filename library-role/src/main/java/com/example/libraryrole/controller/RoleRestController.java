package com.example.libraryrole.controller;

import com.example.libraryrole.exception.RoleNotFoundException;
import com.example.libraryrole.model.Role;
import com.example.libraryrole.service.RoleService;
import com.google.inject.internal.cglib.proxy.$UndeclaredThrowableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/role/{id}")
    public ResponseEntity<Role> getRole(@PathVariable int id) {
        Role role = roleService.findById(id);
        if (role==null) {
            throw new RoleNotFoundException("role not found");
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }


}
