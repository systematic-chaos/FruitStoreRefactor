package com.cybercom.fruitstore.test;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalMatchers.and;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.hamcrest.MockitoHamcrest.intThat;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cybercom.fruitstore.app.FruitStore;
import com.cybercom.fruitstore.entity.FruitObject;
import com.cybercom.fruitstore.svc.FruitException;

@SpringBootTest(classes=FruitStore.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RestInputClassTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	List<FruitObject> fruits;
	
	private static final int nFruits = 4;
	
	@Test
	public void listFruits() throws FruitException {
		when(fruits.size()).thenReturn(nFruits);
		
		assertThat(fruits, hasSize(nFruits));
	}
	
	@Test
	public void fruitType() throws FruitException {
		final String[] types = { "Banana", "Watermelon" };
		
		when(fruits.get(anyInt())).thenReturn(Fruits.generateFruit());
		
		FruitObject f;
		for (int n = 0; n < nFruits; n++) {
			f = fruits.get(n);
			assertNotNull(f);
			assertThat(types, hasItemInArray(f.getType()));
		}
	}
	
	@Test
	public void unexistingFruit() throws FruitException {
		when(fruits.get(and(intThat(greaterThanOrEqualTo(0)),
				intThat(lessThan(nFruits)))))
			.thenReturn(Fruits.generateFruit());
		when(fruits.get(or(intThat(lessThan(0)),
				intThat(greaterThanOrEqualTo(nFruits)))))
			.thenReturn(null);
		
		FruitObject f1 = fruits.get(nFruits - 1);
		FruitObject f2 = fruits.get(nFruits - 2);
		assertNotNull(f1);
		assertNotNull(f2);
		
		when(fruits.get(intThat(greaterThanOrEqualTo(nFruits - 1))))
			.thenReturn(null);
		when(fruits.get(nFruits - 2)).thenReturn(f1);
		
		assertNull(fruits.get(nFruits));
		assertNull(fruits.get(nFruits - 1));
		assertEquals(f1, fruits.get(nFruits - 2));
		
		when(fruits.get(nFruits - 1)).thenReturn(f2);
		
		assertNotNull(fruits.get(nFruits - 2));
		assertEquals(f2, fruits.get(nFruits - 1));
		assertNull(fruits.get(nFruits));
	}
}
