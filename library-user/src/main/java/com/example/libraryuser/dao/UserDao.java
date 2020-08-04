package com.example.libraryuser.dao;

import com.example.libraryuser.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<Account, Integer> {

    Account findById(int id);

    Account findByUsername(String username);

}
