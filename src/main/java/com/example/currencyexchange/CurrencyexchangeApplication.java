package com.example.currencyexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CurrencyexchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyexchangeApplication.class, args);
    }

}
