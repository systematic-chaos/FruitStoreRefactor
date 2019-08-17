package com.cybercom.fruitstore.controller;

import com.cybercom.fruitstore.entity.Fruit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import io.swagger.annotations.Api;

//Since this project is a little example, this kind of layer creates a sinkhole.
//However, in larger projects, is important to delimite properly the bounds of each layer
@Api(value = "FruitStore", description = "Basic operations to get or add new fruits")
public interface FruitStoreAPI {

    @GetMapping(path = "/getFruit/{id}")
    ResponseEntity<?> getFruit(int id);

    @GetMapping(path = "/getFruitList")
    ResponseEntity<?> getFruitList();

    @PostMapping(path = "/getFruit/update/{id}")
    ResponseEntity<Fruit> updateFruit(Fruit fruit, int id);

    @PostMapping(path = "getFruit/store")
    ResponseEntity<Fruit> storeFruit(Fruit fruit);
}