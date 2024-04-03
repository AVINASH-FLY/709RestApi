package com.tka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductRestApiWithoutDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductRestApiWithoutDbApplication.class, args);
		
		System.out.println("started");
	}

}
