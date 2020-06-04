package com.example.librarybook.dao;

import com.example.librarybook.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CopyDao extends JpaRepository<Copy, Integer> {

    List<Copy> findByBook(int book);

    Copy findById(int id);


}
