package com.cybercom.fruitstore.repository;


import com.cybercom.fruitstore.entity.FruitObject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Database extends JpaRepository<FruitObject,Integer> {

}