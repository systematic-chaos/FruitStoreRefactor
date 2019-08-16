package com.cybercom.fruitstore.domain;

import java.util.List;
import java.util.Optional;

import com.cybercom.fruitstore.entity.Fruit;
import com.cybercom.fruitstore.repository.FruitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Delegate;

@Service
class FruitServiceImpl implements FruitService {

    @Autowired
    @Delegate(excludes = FruitService.class)
    private FruitRepository fruitRepository;


    @Override
    public Optional<Fruit> findById(int id) {
        return fruitRepository.findById(id);
    }

    @Override
    public List<Fruit> findAll() {
        return fruitRepository.findAll();
    }

    @Override
    public Fruit update(Fruit fruit, int id) {
        return fruitRepository.findById(id).map(updatedFruit -> {
            updatedFruit.setName(fruit.getName());
            updatedFruit.setPrice(fruit.getPrice());
            updatedFruit.setType(fruit.getType());

            return fruitRepository.save(updatedFruit);
        }).orElseGet(() -> {
            return fruitRepository.save(fruit);
        });
    }

    @Override
    public Fruit save(Fruit fruit) {
        return fruitRepository.save(fruit);
    }
}