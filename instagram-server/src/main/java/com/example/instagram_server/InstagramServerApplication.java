package com.example.instagram_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class InstagramServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstagramServerApplication.class, args);
	}

}
