package com.cybercom.fruitstore.repository;


import com.cybercom.fruitstore.entity.Fruit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends JpaRepository<Fruit,Integer> {

}