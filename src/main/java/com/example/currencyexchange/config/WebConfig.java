package com.example.currencyexchange.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;

@Configuration
public class WebConfig {
    @Value("${currencyLayer.endPoint}")
    private String endPoint;

    @Autowired
    WebClient.Builder webClientBuild;

    @Bean
    public WebClient webClient() {
        WebClient webClient = webClientBuild.baseUrl(endPoint).build();
        return webClient;
    }

}
