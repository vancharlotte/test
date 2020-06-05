package com.example.clientui.controller;

import com.example.clientui.beans.BookBean;
import com.example.clientui.beans.CopyBean;
import com.example.clientui.client.LibraryBookClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private LibraryBookClient bookClient;

    @RequestMapping("/books")
    public String ListBooks(Model model) {
        List<BookBean> books = bookClient.listBooks();
        model.addAttribute("books", books);
        return "SearchBooks";
    }

    @RequestMapping("/books/{id}")
    public String selectBook(@PathVariable int id, Model model) {
        BookBean book = bookClient.displayBook(id);
        List <CopyBean> copies = bookClient.listCopies(book.getId());
        model.addAttribute("book", book);
        model.addAttribute("nbCopy", copies.size());
        return "Book";
    }
}
