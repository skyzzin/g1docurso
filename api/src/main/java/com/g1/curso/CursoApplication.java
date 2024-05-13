package com.g1.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class CursoApplication {
	public static void main(String[] args) {
		SpringApplication.run(CursoApplication.class, args);
	}
}
