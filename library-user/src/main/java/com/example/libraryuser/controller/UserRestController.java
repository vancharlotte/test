package com.example.libraryuser.controller;

import com.example.libraryuser.dao.UserDao;
import com.example.libraryuser.exception.UserNotFoundException;
import com.example.libraryuser.model.User;
import com.example.libraryuser.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    private Logger logger = LoggerFactory.getLogger(UserRestController.class);


    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{id}")
    public User selectUser(@PathVariable int id){
        User user = userService.findById(id);
        if(user==null) throw new UserNotFoundException("user not found");
        return user;
    }

    @GetMapping(value = "/user/email/{email}")
    public User findUserByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }

    @PostMapping(value= "/login")
    public String login(@RequestParam String email, @RequestParam String password){
        String result = userService.login(email, password);
        logger.info(result);
        return result;
    }


}
