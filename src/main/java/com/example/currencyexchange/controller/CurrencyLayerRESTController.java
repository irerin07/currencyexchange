package com.example.currencyexchange.controller;

import com.example.currencyexchange.dto.CurrencyLayerDto;
import com.example.currencyexchange.service.CurrencyLayerAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyLayerRESTController {
    @Autowired
    CurrencyLayerAPIService currencyLayerAPIService;

    private CurrencyLayerDto currencyLayerDto;

    @GetMapping("/exchangerate")
    public Mono<ResponseEntity> getExchangeRate(){
        Mono<CurrencyLayerDto> searchResult = currencyLayerAPIService.getCurrencyRate();

        Mono<ResponseEntity> response = searchResult.map(u -> new ResponseEntity(u.getQuotes(), HttpStatus.OK));

        return response;
    }
}
