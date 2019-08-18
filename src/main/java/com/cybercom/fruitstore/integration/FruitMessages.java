package com.cybercom.fruitstore.integration;

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

    private String topic = "";

    @Autowired
    public FruitMessages(FruitMessagesConfig fruitMessagesConfig) throws MqttException {
        String clientID = MqttClient.generateClientId();

        log.debug("{} creating FruitMessages Client to {}:{}", LOG_TAG,
            fruitMessagesConfig.getConnectionUrl(), clientID);

        mqttClient = new MqttClient(fruitMessagesConfig.getConnectionUrl(),
                                               clientID);

        topic = fruitMessagesConfig.getNewFruitTopic();
        configureMqttClient();
    }

    private void configureMqttClient() {
        mqttClient.setCallback(this);
        try {
            mqttClient.connect();
            mqttClient.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        log.error("{} Connection lost. Cause: {} {}", LOG_TAG,
        cause.getMessage(), cause.getStackTrace());

        /*try {
            mqttClient.close();
        } catch (MqttException exception) {
            log.error("{} Error while closing connection: {}",
                LOG_TAG, exception.getStackTrace());
        }

        configureMqttClient();*/
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
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