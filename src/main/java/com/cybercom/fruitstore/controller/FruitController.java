package com.cybercom.fruitstore.controller;

import java.net.URI;
import java.util.Optional;

import com.cybercom.fruitstore.domain.FruitService;
import com.cybercom.fruitstore.entity.Fruit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/fruitstore")
@Slf4j
public class FruitController implements FruitStoreAPI {

    @Autowired
    private FruitService fruitService;

    @Override
    public ResponseEntity<?> getFruit(@PathVariable("id") int id) {
        log.debug("getFruit. Id: {}", id);
        Optional<Fruit> fruit =  fruitService.findById(id);

        return fruit.isPresent()?
                ResponseEntity.ok(fruit.get()) :
                ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> getFruitList() {
        log.debug("getFruitList");

        return ResponseEntity.ok(fruitService.findAll());
    }

    @Override
    public ResponseEntity<Fruit> updateFruit(@RequestBody Fruit fruit,
                                             @PathVariable("id") int id) {
        log.debug("updateFruit. fruit: {} id: {}", fruit.toString(), id);

        Fruit updatedFruit = fruitService.update(fruit, id);
        return ResponseEntity
                .created(getLocation("/getFruit/update/{id}", updatedFruit.getId()))
                .build();
    }

    @Override
	public ResponseEntity<Fruit> storeFruit(Fruit fruit) {
        log.debug("saveFruit. Fruit: {}", fruit);

        Fruit savedFruit = fruitService.save(fruit);

        return ResponseEntity
                .created(getLocation("{id}", savedFruit.getId()))
                .build();
	}

    private URI getLocation(String path, int id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path(path)
        .buildAndExpand(id).toUri();
    }

}