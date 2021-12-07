package com.cybercom.fruitstore.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/** @class Fruit */
@Entity
public class FruitObject {
	
	@Id
	@GeneratedValue
	@Min(1)
	protected Long id;
	
	/** Type */
	@NotEmpty(message="Please provide a type")
	protected String type;
	
	/** Name */
	@NotEmpty(message="Please provide a name")
	protected String name;
	
	/** Price */
	@DecimalMin(value="0.01", message="Price must be a positive value")
	@Digits(integer=3, fraction=2)
	protected BigDecimal price;
	
	public FruitObject() {
	}
	
	public FruitObject(String type, String name, BigDecimal price) {
		setType(type);
		setPrice(price);
		setName(name);
	}
	
	public FruitObject(String type, String name, BigDecimal price, long id) {
		this(type, name, price);
		setId(id);
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format("{ id: %d, name: %s, type: %s, price: %.2f }",
				id, name, type, price);
	}
	
	@Override
	public FruitObject clone() {
		return id == null ? new FruitObject(type, name, price)
				: new FruitObject(type, name, price, id);
	}
	
	public static FruitObject clone(FruitObject f) {
		return f.clone();
	}
}
