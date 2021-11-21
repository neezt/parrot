package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@EnableAutoConfiguration
@ComponentScan(basePackages="com.example")
public class ParrotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParrotApplication.class, args);
	}

}
