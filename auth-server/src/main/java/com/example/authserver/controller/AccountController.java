package com.example.authserver.controller;

import com.example.authserver.exception.UserNotFoundException;
import com.example.authserver.model.Account;
import com.example.authserver.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);


    @Autowired
    private AccountService accountService;

    @GetMapping(value = { "/account/{id}", "/batch/account/{id}"})
    public Account selectAccount(@PathVariable int id) {
        Account account = accountService.findById(id);
        if (account == null) throw new UserNotFoundException("user not found");
        return account;
    }



    @GetMapping(value = "/principal/{username}")
    public Account findUsername(@PathVariable String username) {
        Optional<Account> account = accountService.findByUsername(username);
        return account.get();
    }

    @GetMapping(value = "/user/me")
    public Principal user(Principal principal) {
        return principal;
    }

    @DeleteMapping(value = "/revokeToken")
    public void revokeToken(String token) {

    }


}

