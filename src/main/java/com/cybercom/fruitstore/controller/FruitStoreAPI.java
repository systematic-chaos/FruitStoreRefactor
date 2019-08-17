package com.cybercom.fruitstore.controller;

import com.cybercom.fruitstore.entity.Fruit;

import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;

//Since this project is a little example, this kind of layer creates a sinkhole.
//However, in larger projects, is important to delimite properly the bounds of each layer
@Api(value = "FruitStore", description = "Basic operations to get or add new fruits")
public interface FruitStoreAPI {
    ResponseEntity<?> getFruit(int id);

    ResponseEntity<?> getFruitList();

    ResponseEntity<Fruit> updateFruit(Fruit fruit, int id);

    ResponseEntity<Fruit> storeFruit(Fruit fruit);
}