package com.example.authentication.dao;

import com.example.authentication.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {

    Account findByUsername (String username);
}
