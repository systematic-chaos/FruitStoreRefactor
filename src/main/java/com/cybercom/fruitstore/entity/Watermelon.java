package com.cybercom.fruitstore.entity;

import java.math.BigDecimal;

/** @class watermelon */
public class Watermelon extends FruitObject {
	
	protected static final String TYPE = "Watermelon";
	
	public Watermelon() {
		setType(TYPE);
	}
	
	public Watermelon(FruitObject f) {
		this(f.getName(), f.getPrice());
	}
	
	public Watermelon(String name, BigDecimal price) {
		super(TYPE, name, price);
	}
	
	public Watermelon(String name, BigDecimal price, long id) {
		super(TYPE, name, price, id);
	}
}
