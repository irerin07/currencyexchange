package com.example.currencyexchange.controller;

import com.example.currencyexchange.dto.CurrencyExchangeInputDto;
import com.example.currencyexchange.dto.CurrencyLayerDto;
import com.example.currencyexchange.service.CurrencyCalcService;
import com.example.currencyexchange.service.CurrencyLayerAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyLayerRESTController {

    @Autowired
    CurrencyCalcService currencyCalcService;


    @GetMapping("/exchangerate")
    public ResponseEntity getExchangeRate(@Valid @ModelAttribute CurrencyExchangeInputDto currencyExchangeInputDto){
        Double currency = currencyCalcService.getExchangeRate(currencyExchangeInputDto.getFrom(), currencyExchangeInputDto.getTo(), currencyExchangeInputDto.getAmount());
        return new ResponseEntity(currency, HttpStatus.OK);
    }

    @GetMapping("/exchangedamount")
    public ResponseEntity getExchangedAmount(@Valid @ModelAttribute CurrencyExchangeInputDto currencyExchangeInputDto){

        Map<String, Double> responseMap = new HashMap<>();
        System.out.println(currencyExchangeInputDto.getFrom() + " : " + currencyExchangeInputDto.getTo() + " : " + currencyExchangeInputDto.getAmount());

        Double currency = currencyCalcService.getExchangeRate(currencyExchangeInputDto.getFrom(), currencyExchangeInputDto.getTo(), currencyExchangeInputDto.getAmount());
        Double exchangedAmount = (currency * currencyExchangeInputDto.getAmount());

        System.out.println(currency + " : " + exchangedAmount);

        responseMap.put("currency", currency);
        responseMap.put("exchangedAmount", exchangedAmount);

        return new ResponseEntity(responseMap, HttpStatus.OK);
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
