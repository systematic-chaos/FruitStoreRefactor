package com.cybercom.fruitstore;

import com.cybercom.fruitstore.config.FruitMessagesConfig;
import com.cybercom.fruitstore.domain.FruitService;
import com.cybercom.fruitstore.entity.Fruit;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FruitMessages implements MqttCallback {
    private static final String LOG_TAG = "[FruitMessages]";

    @Autowired
    private FruitService fruitService;
    private MqttClient mqttClient;

    @Autowired
    public FruitMessages(FruitMessagesConfig fruitMessagesConfig) throws MqttException {
        mqttClient = new MqttClient(fruitMessagesConfig.getConnectionUrl(),
                                               MqttClient.generateClientId());

        mqttClient.setCallback(this);
        mqttClient.connect();
        mqttClient.subscribe(fruitMessagesConfig.getNewFruitTopic());
    }

    @Override
    public void connectionLost(Throwable cause) {
        log.error("{} Connection lost", LOG_TAG);

        try {
            mqttClient.close();
        } catch (MqttException exception) {
            log.error("{} Error while closing connection: {}",
                LOG_TAG, exception.getStackTrace());
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // would be nice to save the fruits..
        log.debug("{} New Message: {} ",LOG_TAG, message.getPayload().toString());
        String fruitStr = message.getPayload().toString();
        log.info("Message arrived {}", fruitStr);

        ObjectMapper mapper = new ObjectMapper();
        Fruit fruitReceived = mapper.readValue(fruitStr, Fruit.class);
        fruitService.save(fruitReceived);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.debug("{} deliveryComplete", LOG_TAG);
    }

}