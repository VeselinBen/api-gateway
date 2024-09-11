package com.example.gateway.service;

import com.example.gateway.dto.EmailRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service

public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = Logger.getLogger(RabbitMQJsonProducer.class.getName());

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(EmailRequest email) {
        LOGGER.info(String.format("Sending json: %s", email.toString()));
        rabbitTemplate.convertAndSend(exchangeName, routingJsonKey, email);
    }
}
