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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyLayerRESTController {

    @Autowired
    CurrencyCalcService currencyCalcService;


    @GetMapping("/exchangerate")
    public ResponseEntity getExchangeRate(@Valid @ModelAttribute CurrencyExchangeInputDto currencyExchangeInputDto){
        DecimalFormat doubleFormat = new DecimalFormat("#,##0.00");
        Double currency = currencyCalcService.getExchangeRate(currencyExchangeInputDto.getFrom(), currencyExchangeInputDto.getTo(), currencyExchangeInputDto.getAmount());
        String stringCurrency = doubleFormat.format(currency);

        return new ResponseEntity(stringCurrency, HttpStatus.OK);
    }

    @GetMapping("/exchangedamount")
    public ResponseEntity getExchangedAmount(@Valid @ModelAttribute CurrencyExchangeInputDto currencyExchangeInputDto){

        Map<String, String> responseMap = new HashMap<>();
        DecimalFormat doubleFormat = new DecimalFormat("#,##0.00");

        Double currency = currencyCalcService.getExchangeRate(currencyExchangeInputDto.getFrom(), currencyExchangeInputDto.getTo(), currencyExchangeInputDto.getAmount());
        Double exchangedAmount = (currency * currencyExchangeInputDto.getAmount());

        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.KOREA);
        String stringAmount = doubleFormat.format((exchangedAmount));
        String stringCurrency = doubleFormat.format(currency);

        responseMap.put("currency", stringCurrency);
        responseMap.put("stringAmount", stringAmount);

        return new ResponseEntity(responseMap, HttpStatus.OK);
    }
}
