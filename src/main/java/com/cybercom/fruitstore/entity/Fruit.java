package com.cybercom.fruitstore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
*  Class that represents a Fruit
*/
@Entity
@Data
@NoArgsConstructor
public class Fruit {
	@Id
	@GeneratedValue
	private int id;

	@NotBlank
	private String name;

	@NotBlank
	private String type;

	@Min(0)
	private float price;

	public Fruit(String name, String type, int price) {
		this.type = type;
		this.name = name;
		this.price = price;
	}

	public void update(Fruit fruit) {
		this.name = fruit.getName();
		this.type = fruit.type;
		this.price = fruit.price;
	}
}