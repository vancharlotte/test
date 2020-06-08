package com.example.clientui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WelcomeController {


    @GetMapping("/")
    public String accueil() {
        return "test";
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }



}
