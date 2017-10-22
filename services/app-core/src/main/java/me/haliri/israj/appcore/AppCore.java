package me.haliri.israj.appcore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppCore implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AppCore.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
