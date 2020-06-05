package com.example.libraryuser.controller;

import com.example.libraryuser.dao.UserDao;
import com.example.libraryuser.model.User;
import com.example.libraryuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{id}")
    public User selectUser(@PathVariable int id){
        return userService.findById(id);
    }

    @GetMapping(value = "/user/email/{email}")
    public User findUserByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }



}
