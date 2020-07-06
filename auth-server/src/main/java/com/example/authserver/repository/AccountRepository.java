package com.example.authserver.repository;

import com.example.authserver.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUsername(String username);

    List<Account> findAll();

    Account findById(int id);

    Account findByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);
}