package com.example.libraryloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class LibraryLoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryLoanApplication.class, args);
	}

}
