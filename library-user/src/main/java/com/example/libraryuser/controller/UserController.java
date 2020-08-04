package com.example.libraryuser.controller;

import com.example.libraryuser.model.Account;
import com.example.libraryuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = { "/account/{id}", "/batch/account/{id}"})
    @PreAuthorize("hasAuthority('ADMIN')" + "|| hasAuthority('USER')")
    public Account selectAccount(@PathVariable int id) {
        Account account = userService.findById(id);
        //if (account == null) throw new UserNotFoundException("user not found");
        return account;
    }

    @GetMapping(value = "/principal/{username}")
    @PreAuthorize("hasAuthority('ADMIN')" + "|| hasAuthority('USER')")
    public Account findUsername(@PathVariable String username) {
        Account account = userService.findByUsername(username);
        return account;
    }

}
