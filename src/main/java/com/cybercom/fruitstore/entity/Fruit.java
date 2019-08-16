package com.cybercom.fruitstore.entity;

import java.util.Random;

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
	private String type;

	@NotBlank
	private String name;

	@Min(0)
	private float price;

	public Fruit(String type, String name, int price) {
		this.type = type;
		this.name = name;
		this.price = price;
	}
}