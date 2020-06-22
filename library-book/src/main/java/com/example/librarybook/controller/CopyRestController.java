package com.example.librarybook.controller;

import com.example.librarybook.exception.CopyNotFoundException;
import com.example.librarybook.model.Copy;
import com.example.librarybook.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CopyRestController {

    @Autowired
    private CopyService copyService;

    //list of copies for one book
    //exception : not found ou empty
    @GetMapping(value = "/copies/{book}")
    public List<Copy> listCopies(@PathVariable int book) {
        return copyService.findByBook(book);
    }

    // select one copy
    @GetMapping(value = "/copy/{id}")
    public Copy selectCopy(@PathVariable int id) {
        Copy copy = copyService.findById(id);
        if (copy==null) throw new CopyNotFoundException ("copy not found");
        return copy;
    }

}
