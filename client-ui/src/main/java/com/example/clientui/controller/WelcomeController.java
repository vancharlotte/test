package com.example.clientui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {


    @GetMapping("/")
    public String accueil(Model model) {
        return "Homepage";
    }

    @GetMapping("/login")
    public String accueil2(Model model) {
        return "Homepage";
    }



}
