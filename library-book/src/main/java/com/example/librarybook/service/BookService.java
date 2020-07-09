package com.example.librarybook.service;

import com.example.librarybook.dao.BookDao;
import com.example.librarybook.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public  List<Book> findAll(){
        return bookDao.findAll();
    }

    public Book findById(int id){
        return bookDao.findById(id);
    }

    public List<Book> findByString(String word){
        return bookDao.findByTitleOrAuthorOrGenre(word.toLowerCase());

    }
}
