package com.example.librarybook.dao;

import com.example.librarybook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

    List<Book> findAll();

    Book findById(int id);



}
