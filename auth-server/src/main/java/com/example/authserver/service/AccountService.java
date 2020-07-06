package com.example.authserver.service;

import com.example.authserver.model.Account;
import com.example.authserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Optional<Account> findByUsername(String username){
        return accountRepository.findByUsername(username);
    }

    public Account findById(int id){
        return accountRepository.findById(id);
    }


}
