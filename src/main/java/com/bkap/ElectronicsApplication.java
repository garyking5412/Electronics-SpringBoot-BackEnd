package com.bkap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.bkap" })
public class ElectronicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectronicsApplication.class, args);

	}

}
