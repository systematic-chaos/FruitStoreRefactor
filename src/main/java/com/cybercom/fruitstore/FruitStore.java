package com.cybercom.fruitstore;

import com.cybercom.fruitstore.config.FruitMessagesConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(FruitMessagesConfig.class)
@SpringBootApplication
public class FruitStore {
	public static void main(String[] args) {
		SpringApplication.run(FruitStore.class, args);
	}
}
