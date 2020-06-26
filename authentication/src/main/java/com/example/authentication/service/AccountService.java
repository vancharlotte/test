package com.example.authentication.service;

import com.example.authentication.dao.AccountDao;
import com.example.authentication.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void init(){
        Account admin = new Account();
        admin.setUsername("admin");
        String pw = "123";
        admin.setPassword(passwordEncoder.encode(pw));
        admin.setRoles("ADMIN,USER");
        accountDao.save(admin);

        Account user = new Account();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode(pw));
        user.setRoles("USER");
        accountDao.save(user);

    }
}
