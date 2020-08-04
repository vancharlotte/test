package com.example.libraryuser.service;

import com.example.libraryuser.dao.UserDao;
import com.example.libraryuser.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public Account findById(int id){
        return userDao.findById(id);
    }

    public Account findByUsername(String username){
        return userDao.findByUsername(username);
    }



}
