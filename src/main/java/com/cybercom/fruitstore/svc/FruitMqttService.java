package com.cybercom.fruitstore.svc;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FruitMqttService implements MqttCallback {
	
	private MqttClient mqtt;
	private ObjectMapper mapper;
	private Map<String, MqttCallbackSetting<?>> messageCallbacks;
	
	public FruitMqttService() throws MqttException, URISyntaxException {
		this.messageCallbacks = new HashMap<>();
		
		setupObjectMapper();
		setupMqttClient(new URI("tcp://localhost:1883"));
	}
	
	private void setupObjectMapper() {
		this.mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, Boolean.TRUE);
	}
	
	private void setupMqttClient(URI serverURI) throws MqttException {
		this.mqtt = new MqttClient(serverURI.toString(), MqttClient.generateClientId());
		mqtt.setCallback(this);
		mqtt.connect();
		
		LOG.info("Connected to MQTT broker - " + serverURI);
	}
	
	public <M> void subscribeTopic(String topic, FruitMessageCallback<M> callback, Class<M> type)
			throws MqttException {
		messageCallbacks.put(topic, new MqttCallbackSetting<M>(callback, type));
		mqtt.subscribe(topic);
		
		LOG.info("Subscribed to topic " + topic);
	}
	
	public void publishMessage(String topic, Object msg)
			throws JsonProcessingException, MqttException {
		mqtt.publish(topic, new MqttMessage(mapper.writeValueAsBytes(msg)));
		
		LOG.info(String.format("Published message to topic %s: %s", topic, msg));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void messageArrived(String topic, MqttMessage message) {
		if (!messageCallbacks.containsKey(topic)) return;
		
		LOG.debug("Message received: " + new String(message.getPayload()));
		
		// It is nice indeed to save the fruits!
		MqttCallbackSetting<?> cb = messageCallbacks.get(topic);
		try {
			cb.getCallback().messageArrived(
					mapper.readValue(message.getPayload(), cb.getType()));
		} catch (Exception e) {
			LOG.warn(String.format("%s: %s", e.getClass(), e.getMessage()));
		}
	}
	
	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
	}
	
	@Override
	public void connectionLost(Throwable cause) {
		LOG.error("Connection lost");
	}
	
	private static class MqttCallbackSetting<M> {
		
		public MqttCallbackSetting(FruitMessageCallback<M> callback, Class<M> type) {
			this.callback = callback;
			this.type = type;
		}
		
		private FruitMessageCallback<M> callback;
		private Class<M> type;
		
		@SuppressWarnings("rawtypes")
		public FruitMessageCallback getCallback() {
			return callback;
		}
		
		public Class<M> getType() {
			return type;
		}
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(FruitMqttService.class);
}
