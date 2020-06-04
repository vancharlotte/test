package com.example.clientui.controller;

import com.example.clientui.beans.BookBean;
import com.example.clientui.client.LibraryBookClient;
import com.example.librarybook.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WelcomeController {


    @Autowired
    private LibraryBookClient bookClient;

    @RequestMapping("/")
    public String accueil(Model model) {
        List<BookBean> books = bookClient.listBooks();
        model.addAttribute("books", books);
        return "Accueil";
    }

    @RequestMapping("/books")
    public List<BookBean>  listBooks(Model model) {
        List<BookBean> test = bookClient.listBooks();
        return test;
    }

}
