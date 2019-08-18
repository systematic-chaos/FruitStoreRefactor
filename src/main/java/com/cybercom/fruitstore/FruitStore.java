package com.cybercom.fruitstore;

import com.cybercom.fruitstore.config.FruitMessagesConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(FruitMessagesConfig.class)
@SpringBootApplication(scanBasePackages = {"com.cybercom.fruitstore.integration",
"com.cybercom.fruitstore.util",
"com.cybercom.fruitstore.domain",
"com.cybercom.fruitstore.controller"})
public class FruitStore {
	public static void main(String[] args) {
		SpringApplication.run(FruitStore.class, args);
	}
}
