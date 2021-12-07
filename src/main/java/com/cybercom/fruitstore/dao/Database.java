package com.cybercom.fruitstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybercom.fruitstore.entity.FruitObject;

@Repository
public interface Database extends JpaRepository<FruitObject, Long> {
}
