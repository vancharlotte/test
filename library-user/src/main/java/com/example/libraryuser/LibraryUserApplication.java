package com.example.libraryuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LibraryUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryUserApplication.class, args);
	}

}
