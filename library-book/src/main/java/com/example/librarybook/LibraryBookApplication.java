package com.example.librarybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LibraryBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryBookApplication.class, args);
	}

}
