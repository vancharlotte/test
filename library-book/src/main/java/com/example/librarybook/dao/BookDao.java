package com.example.librarybook.dao;

import com.example.librarybook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

    List<Book> findAll();

    Book findById(int id);

    List<Book> findByTitleContains(String title);
    List<Book> findByAuthorContains(String author);
    List<Book> findByGenreContains(String genre);


    @Query("select b from Book b where lower(b.title) like %:word% or lower(b.author)like %:word% or lower(b.genre)like %:word%")
    List<Book> findByTitleOrAuthorOrGenre(String word);
}
