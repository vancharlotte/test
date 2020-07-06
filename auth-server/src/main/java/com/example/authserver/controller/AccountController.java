package com.example.authserver.controller;

import com.example.authserver.exception.UserNotFoundException;
import com.example.authserver.model.Account;
import com.example.authserver.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);


    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/account/{id}")
    public Account selectAccount(@PathVariable int id){
        Account account = accountService.findById(id);
        if(account==null) throw new UserNotFoundException("user not found");
        return account;
    }

    @GetMapping(value = "/principal/{username}")
    public Account findUsername(@PathVariable String username){
        Optional<Account> account = accountService.findByUsername(username);
        return account.get();
    }


    }

