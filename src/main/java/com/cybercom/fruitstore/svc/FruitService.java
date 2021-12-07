package com.cybercom.fruitstore.svc;

import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybercom.fruitstore.entity.FruitObject;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class FruitService {
	
	@Autowired
	private FruitH2Service h2Svc;
	
	@Autowired
	private FruitMqttService mqttSvc;
	
	private static final String NEW_FRUIT_TOPIC = "new/fruit";
	
	public FruitObject retrieveFruit(long id) throws FruitException {
		LOG.debug("[retrieveFruit]");
		return h2Svc.getFruit(id);
	}
	
	public List<FruitObject> retrieveAllFruits() throws FruitException {
		LOG.debug("[retrieveAllFruits]");
		return h2Svc.getAllFruits();
	}
	
	public void storeFruit(FruitObject f) throws FruitException {
		LOG.debug("[storeFruit]");
		h2Svc.storeFruit(f);
	}
	
	public void updateFruit(FruitObject f) throws FruitException {
		LOG.debug("[updateFruit]");
		h2Svc.updateFruit(f);
	}
	
	public void deleteFruit(long id) throws FruitException {
		LOG.debug("[deleteFruit]");
		h2Svc.deleteFruit(id);
	}
	
	public void publishFruit(FruitObject f) throws FruitException {
		LOG.debug("[publishFruit]");
		
		try {
			mqttSvc.publishMessage(NEW_FRUIT_TOPIC, f);
		} catch (JsonProcessingException | MqttException e) {
			LOG.error(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()), e);
			throw new FruitException(e);
		}
	}
	
	public void subscribe(FruitMessageCallback<FruitObject> callback) throws FruitException {
		LOG.debug("[subscribe]");
		
		try {
			mqttSvc.subscribeTopic(NEW_FRUIT_TOPIC, callback, FruitObject.class);
		} catch (MqttException e) {
			LOG.error(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()), e);
			throw new FruitException(e);
		}
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(FruitService.class);
}
