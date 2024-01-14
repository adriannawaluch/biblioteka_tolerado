package com.example.biblioteka_tolerado;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableAsync
@EntityScan("com.example.biblioteka_tolerado")
public class BibliotekaToleradoApplication {
	public static void main(String[] args) {
		SpringApplication.run(BibliotekaToleradoApplication.class, args);

	}

}
