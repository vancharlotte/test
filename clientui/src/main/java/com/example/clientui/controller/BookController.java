package com.example.clientui.controller;

import com.example.clientui.beans.BookBean;
import com.example.clientui.beans.CopyBean;
import com.example.clientui.client.LibraryBookClient;
import com.example.clientui.client.LibraryLoanClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);


    @Autowired
    private LibraryBookClient bookClient;

    @Autowired
    private LibraryLoanClient loanClient;

    @RequestMapping("/books")
    public String ListBooks(Model model) {
        List<BookBean> books = bookClient.listBooks();
        model.addAttribute("books", books);

        return "SearchBooks";
    }

    @RequestMapping("/books/{id}")
    public String selectBook(@PathVariable int id, Model model) {
        BookBean book = bookClient.displayBook(id);
        model.addAttribute("book", book);

        List <CopyBean> copies = bookClient.listCopies(book.getId());
        List <CopyBean> copiesAvailable = new ArrayList<>();

        for (int i = 0; i < copies.size(); i++) {
            boolean copyAvailable = loanClient.copyAvailable(copies.get(i).getId());
            if (copyAvailable){                copiesAvailable.add(copies.get(i));            }
        }

        model.addAttribute("nbCopy", copies.size());
        model.addAttribute("nbCopyAvailable", copiesAvailable.size());

        return "Book";
    }


}
