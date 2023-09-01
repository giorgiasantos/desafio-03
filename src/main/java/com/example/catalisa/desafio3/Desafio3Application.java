package com.example.catalisa.desafio3;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API para gerenciamento de estoque", version = "1", description = "Api para gerenciamento de produtos de um estoque"))

public class Desafio3Application {

	public static void main(String[] args) {
		SpringApplication.run(Desafio3Application.class, args);
	}

}
