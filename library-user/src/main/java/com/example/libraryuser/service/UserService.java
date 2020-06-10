package com.example.libraryuser.service;

import com.example.libraryuser.dao.UserDao;
import com.example.libraryuser.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findById(int id){
        return userDao.findById(id);
    }

    public User findByEmail(String email){
        return  userDao.findByEmail(email);
    }

    public String login(String email, String password) {
        if (userDao.existsByEmailAndPassword(email,password)){
            return "success";
        }
        else{
            return "echec";
        }
    }
}
