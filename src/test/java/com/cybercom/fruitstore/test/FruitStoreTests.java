package com.cybercom.fruitstore.test;

import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cybercom.fruitstore.app.FruitStore;
import com.cybercom.fruitstore.entity.FruitObject;
import com.cybercom.fruitstore.svc.FruitException;

@SpringBootTest(classes=FruitStore.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FruitStoreTests {
	
	private static List<FruitObject> fruits;
	private static final int nFruits = 4;
	
	@BeforeClass
	public static void contextLoad() {
		fruits = new ArrayList<>(nFruits);
		Fruits.iterator(nFruits).forEachRemaining(f -> fruits.add(f));
	}
	
	@Test
	public void listFruits() throws FruitException {
		assertEquals(nFruits, fruits.size());
	}
	
	@Test
	public void fruitType() throws FruitException {
		final String[] types = { "Banana", "Watermelon" };
		
		for (String fruitType : fruits.stream()
				.map(f -> f.getType()).collect(Collectors.toList())) {
			assertThat(fruitType, isIn(types));
		}
	}
	
	@Test
	public void unexistingFruit() throws FruitException {
		final int fruitId = nFruits - 1;
		FruitObject auxFruit = fruits.get(fruitId);
		
		assertNotNull(auxFruit);
		fruits.remove(fruitId);
		fruits.add(null);
		assertNull(fruits.get(fruitId));
		fruits.set(fruitId, auxFruit);
		assertNotNull(auxFruit);
	}
}
