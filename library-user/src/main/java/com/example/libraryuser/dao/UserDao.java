package com.example.libraryuser.dao;

import com.example.libraryuser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findAll();

    User findById(int id);

    User findByEmail(String email);
}
