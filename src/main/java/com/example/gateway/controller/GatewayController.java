package com.example.gateway.controller;


import com.example.gateway.dto.EmailRequest;
import com.example.gateway.service.RabbitMQJsonProducer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GatewayController {

    private final RabbitMQJsonProducer rabbitMQProducer;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        rabbitMQProducer.sendJsonMessage(emailRequest);
        return "Email request sent to RabbitMQ!";
    }
}