package com.example.librarybook.controller;

import com.example.librarybook.dao.BookDao;
import com.example.librarybook.model.Book;
import com.example.librarybook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookRestController {

    @Autowired
    private BookService bookService;


    @GetMapping(value ="/books")
    public List<Book> listBooks (){
        return bookService.findAll();
    }

    @GetMapping(value="/books/{id}")
    public Book displayBook(@PathVariable int id) {
        return bookService.findById(id);
    }


}
