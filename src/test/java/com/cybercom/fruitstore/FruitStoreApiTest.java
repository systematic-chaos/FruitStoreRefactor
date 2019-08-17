package com.cybercom.fruitstore;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.cybercom.fruitstore.controller.FruitController;
import com.cybercom.fruitstore.domain.FruitService;
import com.cybercom.fruitstore.entity.Fruit;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@WebMvcTest(value = FruitController.class, secure = false)
public class FruitStoreApiTest {

    @MockBean
    private FruitService fruitService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void insertOneFruitAndGotOne() throws Exception {
        Fruit mockFruit = new Fruit("Bannana", "Cavendish", 3);
        List<Fruit> fruitList = new ArrayList<Fruit>();
        fruitList.add(mockFruit);

        String expectedFruitList = "[Fruit(id=0, type=Bannana, name=Cavendish, price=3.0)]";

        when(fruitService.findAll()).thenReturn(fruitList);

        MvcResult mvcResultGetList = mockMvc
                .perform(MockMvcRequestBuilders.get("/fruitstore/getFruitList").accept(MediaType.APPLICATION_JSON))
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        Class<?> clz = Fruit.class;
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clz);
        List<Fruit> resultList = new ObjectMapper().readValue(mvcResultGetList.getResponse().getContentAsString(),
                type);
        log.debug("response save: {}", resultList.toString());

        assertEquals("The size of the list should be one", 1, resultList.size());
    }
}