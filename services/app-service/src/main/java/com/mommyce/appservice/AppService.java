package com.mommyce.appservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.mommyce" })
public class AppService {

	public static void main(String[] args) {
		SpringApplication.run(AppService.class, args);
	}
}
