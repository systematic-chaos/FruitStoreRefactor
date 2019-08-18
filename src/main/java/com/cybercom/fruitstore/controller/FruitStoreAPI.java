package com.cybercom.fruitstore.controller;

import com.cybercom.fruitstore.entity.Fruit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

//Since this project is a little example, this kind of layer creates a sinkhole.
//However, in larger projects, is important to delimite properly the bounds of each layer
@Api(value = "FruitStore", description = "Basic operations to get or add new fruits")
@RequestMapping(path = "/fruitstore")
public interface FruitStoreAPI {

    @GetMapping(path = "/fruits/{id}")
    ResponseEntity<?> getFruit(int id);

    @GetMapping(path = "/fruits")
    ResponseEntity<?> getFruitList();

    @PostMapping(path = "update/{id}")
    ResponseEntity<Fruit> updateFruit(@RequestBody Fruit fruit, @PathVariable("id") int id);

    @PostMapping(path = "store")
    ResponseEntity<Fruit> storeFruit(@RequestBody Fruit fruit);
}