package com.example.librarybook.controller;

import com.example.librarybook.dao.CopyDao;
import com.example.librarybook.model.Copy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CopyRestController {

    @Autowired
    private CopyDao copyDao;

//list of copies for one book
    @GetMapping(value = "/copies/{book}")
        public List<Copy> listCopies(@PathVariable int book) {
            return copyDao.findByBook(book);
        }

// select one copy
    @GetMapping(value="/copy/{id}")
    public Copy selectCopy(@PathVariable int id) {
        return copyDao.findById(id);
    }
}
