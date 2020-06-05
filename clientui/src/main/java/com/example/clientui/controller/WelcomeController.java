package com.example.clientui.controller;

import com.example.clientui.beans.BookBean;
import com.example.clientui.beans.CopyBean;
import com.example.clientui.beans.LoanBean;
import com.example.clientui.client.LibraryBookClient;
import com.example.clientui.client.LibraryLoanClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WelcomeController {



    @RequestMapping("/")
    public String accueil() {
        return "Accueil";
    }



}
