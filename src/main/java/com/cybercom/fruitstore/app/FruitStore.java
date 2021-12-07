package com.cybercom.fruitstore.app;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages="com.cybercom.fruitstore")
@EnableJpaRepositories(basePackages="com.cybercom.fruitstore.dao")
@EntityScan(basePackages="com.cybercom.fruitstore.entity")
public class FruitStore {
	
	public static void main(String[] args) {
		SpringApplication.run(FruitStore.class, args);
	}
}
