package com.example.currencyexchange.controller;

import com.example.currencyexchange.dto.CurrencyExchangeInputDto;
import com.example.currencyexchange.service.CurrencyCalcService;
import com.example.currencyexchange.util.DecimalFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        String stringCurrency = DecimalFormat.convertDoubleToString(currency);

        return new ResponseEntity(stringCurrency, HttpStatus.OK);
    }

    @GetMapping("/exchangedamount")
    public ResponseEntity getExchangedAmount(@Valid @ModelAttribute CurrencyExchangeInputDto currencyExchangeInputDto){
        Map<String, String> responseMap = new HashMap<>();

        Double currency = currencyCalcService.getExchangeRate(currencyExchangeInputDto.getFrom(), currencyExchangeInputDto.getTo(), currencyExchangeInputDto.getAmount());
        Double exchangedAmount = (currency * currencyExchangeInputDto.getAmount());

        String stringCurrency = DecimalFormat.convertDoubleToString(currency);
        String stringAmount = DecimalFormat.convertDoubleToString((exchangedAmount));

        responseMap.put("currency", stringCurrency);
        responseMap.put("stringAmount", stringAmount);

        return new ResponseEntity(responseMap, HttpStatus.OK);
    }
}
