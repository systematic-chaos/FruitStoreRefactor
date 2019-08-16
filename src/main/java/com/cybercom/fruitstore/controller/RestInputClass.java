package com.cybercom.fruitstore.controller;

import java.util.Optional;

import javax.servlet.http.HttpServlet;

import com.cybercom.fruitstore.entity.FruitObject;
import com.cybercom.fruitstore.repository.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Repository
@RequestMapping(path="/fruitstore")
public class RestInputClass extends HttpServlet {
@Autowired
  Database db;

  public static String JSON="application/json";

	private static final long serialVersionUID = 4279154482819675728L;
@RequestMapping(method=RequestMethod.GET,path="/getFruit/{id}", produces="application/json")
public ResponseEntity<FruitObject> getFruit(@PathVariable("id") String id){
    int idi = new Integer(id);
    Optional<FruitObject> f = db.findById(idi);
    return ResponseEntity.ok(f.get());
}

@PostMapping
@RequestMapping(path="/store/fruit", consumes="application/json")
public ResponseEntity<FruitObject> storeFruit(@RequestBody FruitObject f){


    FruitObject f2 = db.save(f);

    return ResponseEntity.ok(f2);

}
@RequestMapping(path="/update")
public void update(@RequestHeader FruitObject f){
db.save(f);
}





}