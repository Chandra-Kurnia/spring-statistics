package com.application.clasementAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClasementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClasementApplication.class, args);
		System.out.println("Listening spring boot application port:1234");
		System.out.println("Swagger access : http://localhost:1234/swagger-ui.html");
	}

}
