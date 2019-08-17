package com.cybercom.fruitstore.config;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "fruitstore.messages")
@Data
public class FruitMessagesConfig {
    @NotBlank
    private String connectionUrl;

    @NotBlank
    private String newFruitTopic;
}