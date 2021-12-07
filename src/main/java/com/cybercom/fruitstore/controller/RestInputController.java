package com.cybercom.fruitstore.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybercom.fruitstore.entity.FruitObject;
import com.cybercom.fruitstore.svc.FruitH2Service;

@RestController
@Validated
@RequestMapping(path="/fruitstore")
public class RestInputController extends HttpServlet {

	@Autowired
	private FruitH2Service fruitSvc;
	
	public static final String APP_JSON = "application/json";
	private static final long serialVersionUID = 7439730560730816379L;
	
	@GetMapping(path="/", produces=APP_JSON)
	public ResponseEntity<List<FruitObject>> getAllFruits() {
		LOG.debug("GET /fruitstore/");
		
		return ResponseEntity.ok(fruitSvc.getAllFruits());
	}
	
	@GetMapping(path="/{id}", produces=APP_JSON)
	public ResponseEntity<FruitObject> getFruit(@PathVariable @Min(1) long id) {
		LOG.debug(String.format("GET /fruitstore/%d", id));
		
		Optional<FruitObject> f = Optional.ofNullable(fruitSvc.getFruit(id));
		return f.isPresent() ?
				ResponseEntity.ok(f.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping(path="/", consumes=APP_JSON)
	public ResponseEntity<FruitObject> storeFruit(@RequestBody FruitObject f) {
		LOG.debug(String.format("POST /fruitstore/\t%s", f));
		
		return ResponseEntity.ok(fruitSvc.storeFruit(f));
	}
	
	@PutMapping(path="/")
	public ResponseEntity<FruitObject> storeOrUpdateFruit(@RequestBody FruitObject f) {
		LOG.debug(String.format("PUT /fruitstore/\t%s", f));
		
		Optional<FruitObject> fo = Optional.ofNullable(fruitSvc.updateFruit(f));
		return fo.isPresent() ?
				ResponseEntity.ok(fo.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping(path="/{id}")
	public ResponseEntity<FruitObject> updateFruit(
			@PathVariable @Min(1) long id, @RequestBody FruitObject f) {
		LOG.debug(String.format("POST /fruitstore/%d\t%s", id, f));
		
		f.setId(id);
		return storeOrUpdateFruit(f);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Integer> deleteFruit(@PathVariable @Min(1) long id) {
		LOG.debug(String.format("DELETE /fruitstore/%d", id));
		
		return ResponseEntity.ok(fruitSvc.deleteFruit(id));
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(RestInputController.class);
}
