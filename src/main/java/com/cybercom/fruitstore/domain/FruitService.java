package com.cybercom.fruitstore.domain;

import java.util.List;
import java.util.Optional;

import com.cybercom.fruitstore.entity.Fruit;

public interface FruitService {
    Optional<Fruit> findById(int id);

    List<Fruit> findAll();

    Fruit update(Fruit fruit, int id);

    Fruit save(Fruit fruit);
}