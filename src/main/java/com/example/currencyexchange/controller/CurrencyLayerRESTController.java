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
    public ResponseEntity getExchangeRate(){
        currencyLayerDto = currencyLayerAPIService.getCurrencyRate();

        return new ResponseEntity(currencyLayerDto, HttpStatus.OK);
    }

//    @GetMapping("/receiving-amount")
//    public ResponseEntity getReceivingAmount(@Valid @ModelAttribute InputDto inputDto) {
//       return null;
//    }

//    @GetMapping("/exchangerate")
//    public Mono<ResponseEntity<CurrencyLayerDto>> getExchangeRate(){
////        Mono<CurrencyLayerDto> searchResult = currencyLayerAPIService.getCurrencyRate();
////
////
////        return new ResponseEntity<>(searchResult, HttpStatus.OK);
//        return currencyLayerAPIService.getCurrencyRate().map(savedTweet -> ResponseEntity.ok(savedTweet)).defaultIfEmpty(ResponseEntity.notFound().build());
//    }
}
