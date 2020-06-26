package com.example.authentication;

import com.example.authentication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthenticationApplication {

	public AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);

	}

}
