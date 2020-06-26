package com.example.authentication.service;

import com.example.authentication.dao.AccountDao;
import com.example.authentication.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountDao.findByUsername(username);

        if (account == null) {
            System.out.println("no user found");

            throw new UsernameNotFoundException("No user found for " + username + ".");

        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        account.getRolesList().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(/*"ROLE_" + */role));
        });

        User user = new User(account.getUsername(), account.getPassword(), authorities);
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("authorities : " + user.getAuthorities().toString());

        return user;
    }
}
