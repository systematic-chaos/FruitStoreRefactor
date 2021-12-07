package com.cybercom.fruitstore.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cybercom.fruitstore.entity.FruitObject;
import com.cybercom.fruitstore.svc.FruitException;
import com.cybercom.fruitstore.svc.FruitMessageCallback;
import com.cybercom.fruitstore.svc.FruitService;

@Controller
public class MqttMessageController {

	@Autowired
	private FruitService fruitSvc;

	@PostConstruct
	private void postConstruct() {
		try {
			fruitSvc.subscribe(new FruitMessageCallback<FruitObject>() {

				@Override
				public void messageArrived(FruitObject fruit) throws IOException {
					try {
						LOG.debug("New fruit:\t" + fruit);
						fruitSvc.storeFruit(fruit);
					} catch (FruitException fe) {
						LOG.error("FruitException: " + fe.getMessage(), fe);
						throw new IOException(fe.getMessage(), fe);
					}
				}
			});
			LOG.info("Got subscribed to new fruits topic");
		} catch (FruitException fe) {
			LOG.error("FruitException: " + fe.getMessage(), fe);
		}
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(MqttMessageController.class);
}
