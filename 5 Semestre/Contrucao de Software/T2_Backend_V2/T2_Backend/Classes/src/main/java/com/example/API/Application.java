package com.example.API;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "T2_Backend", version = "1", description = "API desenvolvida para o trabalho 2 da disciplina Construção de Software"))
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

