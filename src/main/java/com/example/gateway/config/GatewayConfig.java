package com.example.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${spring.cloud.gateway.routes[0].uri}")
    private String formsServiceUri;

    @Value("${spring.cloud.gateway.routes[1].uri}")
    private String emailServiceUri;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("forms-service", r -> r.path("/forms/**")
                        .uri(formsServiceUri))
                .route("email-service", r -> r.path("/emails/**")
                        .uri(emailServiceUri))
                .build();
    }
}
