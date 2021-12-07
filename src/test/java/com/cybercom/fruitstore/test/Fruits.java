package com.cybercom.fruitstore.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.cybercom.fruitstore.entity.Banana;
import com.cybercom.fruitstore.entity.FruitObject;
import com.cybercom.fruitstore.entity.Watermelon;

public class Fruits {
	
	private static final Random rand = new Random(System.currentTimeMillis());
	
	public static FruitObject generateFruit() {
		return rand.nextBoolean() ? generateBanana() : generateWatermelon();
	}
	
	public static Banana generateBanana() {
		final String[] names = { "Cavendish" };
		final float[] prices = { 8f };
		
		return new Banana(names[rand.nextInt(names.length)],
				BigDecimal.valueOf((double) prices[rand.nextInt(prices.length)]));
	}
	
	public static Watermelon generateWatermelon() {
		final String[] names = { "Black Diamond" };
		final float[] prices = { 3.5f };
		
		return new Watermelon(names[rand.nextInt(names.length)],
				BigDecimal.valueOf((double) prices[rand.nextInt(prices.length)]));
	}
	
	public static Iterator<FruitObject> iterator() {
		return iterator(3);
	}
	
	public static Iterator<FruitObject> iterator(int size) {
		ArrayList<FruitObject> list = new ArrayList<>(size);
		for (int n = 0; n < size; n++) {
			list.add(generateFruit());
		}
		return list.iterator();
	}
}
