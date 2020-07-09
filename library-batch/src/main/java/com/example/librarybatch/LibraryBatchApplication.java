package com.example.librarybatch;

import com.example.librarybatch.config.BatchConfig;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.example.librarybatch")
public class LibraryBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryBatchApplication.class, args);


	}

}
