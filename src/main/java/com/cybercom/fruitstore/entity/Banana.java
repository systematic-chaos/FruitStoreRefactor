package com.cybercom.fruitstore.entity;

import java.math.BigDecimal;

/** @class banana */
public class Banana extends FruitObject {
	
	protected static final String TYPE = "Banana";
	
	public Banana() {
		setType(TYPE);
	}
	
	public Banana(FruitObject f) {
		this(f.getName(), f.getPrice());
	}
	
	public Banana(String name, BigDecimal price) {
		super(TYPE, name, price);
	}
	
	public Banana(String name, BigDecimal price, long id) {
		super(TYPE, name, price, id);
	}
}
