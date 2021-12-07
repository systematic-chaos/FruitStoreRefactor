package com.cybercom.fruitstore.svc;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cybercom.fruitstore.dao.Database;
import com.cybercom.fruitstore.entity.FruitObject;

@Service
@Transactional(isolation=Isolation.READ_COMMITTED)
public class FruitH2Service {
	
	@Autowired
	private Database db;
	
	public FruitObject getFruit(long id) {
		LOG.debug(String.format("[getFruit %d]", id));
		
		return db.existsById(id) ? db.getOne(id).clone() : null;
	}
	
	public List<FruitObject> getAllFruits() {
		LOG.debug("[getAllFruits]");
		
		return db.findAll().stream().map(f -> f.clone())
				.collect(Collectors.toList());
	}
	
	public FruitObject storeFruit(FruitObject f) {
		LOG.debug("[storeFruit]");
		
		return db.save(
				new FruitObject(f.getType(), f.getName(), f.getPrice()))
				.clone();
	}
	
	public FruitObject updateFruit(FruitObject f) {
		LOG.debug("[updateFruit]");
		
		return f.getId() != null && db.existsById(f.getId()) ?
				db.save(f).clone() : null;
	}
	
	public int deleteFruit(long id) {
		LOG.debug(String.format("[deleteFruit %d]", id));
		int count = db.existsById(id) ? 1 : 0;
		db.deleteById(id);
		return count;
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(FruitH2Service.class);
}
