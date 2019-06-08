package com.example.currencyexchange.service;

import com.example.currencyexchange.dto.CurrencyLayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Locale;

@Service
public class CurrencyCalcServiceImpl implements CurrencyCalcService{

    @Autowired
    CurrencyLayerAPIService currencyLayerAPIService;

    @Override
    public Double getExchangeRate(String from, String to, Double amount) {
        CurrencyLayerDto currencyLayerDto = currencyLayerAPIService.getCurrencyRate();
        Double currency = currencyLayerDto.getQuotes().get(from+to);

        return currency;
    }
}
