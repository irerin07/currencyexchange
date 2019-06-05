package com.example.currencyexchange.service;

import com.example.currencyexchange.dto.CurrencyLayerDto;
import reactor.core.publisher.Mono;

public interface CurrencyLayerAPIService {
    public Mono<CurrencyLayerDto> getCurrencyRate();
}
