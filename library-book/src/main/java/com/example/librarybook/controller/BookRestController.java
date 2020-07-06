package com.example.librarybook.controller;

import com.example.librarybook.exception.BookNotFoundException;
import com.example.librarybook.model.Book;
import com.example.librarybook.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/library-book")
public class BookRestController {

    private Logger logger = LoggerFactory.getLogger(BookRestController.class);


    @Autowired
    private BookService bookService;


    @GetMapping(value ="/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> listBooks (){
        return bookService.findAll();
    }

    @GetMapping(value="/books/{id}")
    public Book displayBook(@PathVariable int id) {
        Book book = bookService.findById(id);
        if(book==null) throw new BookNotFoundException("book not found.");
        return book;
    }

    @GetMapping(value="/books/search/{word}")
    public List<Book> searchBooks(@PathVariable("word") String word){
        return bookService.findByString(word);
    }

    @GetMapping(value = "/books/search")
    public List<Book> getBooks(@RequestParam(value = "title", required = false, defaultValue="") String title) {
        List<Book> bookList = bookService.findByString(title);
        logger.info("Getting list books : " + bookList.size());
        return bookList;
    }
}
