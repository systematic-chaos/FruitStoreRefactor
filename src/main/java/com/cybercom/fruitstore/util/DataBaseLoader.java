package com.cybercom.fruitstore.util;

import com.cybercom.fruitstore.domain.FruitService;
import com.cybercom.fruitstore.entity.Fruit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DataBaseLoader {
    @Bean
    CommandLineRunner initDatabase(FruitService service) {
	    return args -> {
            storeFruit(new Fruit("Banana", "Cavendish", 3), service);
            storeFruit(new Fruit("Watermelon", "Black Diamond", 5), service);
            storeFruit(new Fruit("Orange", "Seville", 2), service);
            storeFruit(new Fruit("Grape", "Tempranillo", 2), service);
	    };
    }

    private void storeFruit(Fruit fruit, FruitService service) {
        log.info("Loading fruit: " + fruit.getName());
        service.save(fruit);
    }
}