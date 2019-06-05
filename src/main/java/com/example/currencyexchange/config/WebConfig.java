package com.example.currencyexchange.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

    @Autowired
    WebClient.Builder webClientBuild;

    @Bean
    public WebClient webClient() {
        WebClient webClient = webClientBuild.baseUrl("http://apilayer.net/api/").build();
        return webClient;
    }

}
