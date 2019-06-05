package com.example.currencyexchange.service;

import com.example.currencyexchange.dto.CurrencyLayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrencyLayerAPIServiceImpl implements CurrencyLayerAPIService {
    @Value("${currencyLayer.accessKey}")
    private String accessKey;
    @Value("${currencyLayer.endPoint}")
    private String endPoint;
    @Value("${currencyLayer.source}")
    private String source;
    @Value("${currencyLayer.currencies}")
    private String currencies;
    @Value("${currencyLayer.refreshRate}")
    private int refreshRate;

    @Autowired
    WebClient webClient;

    private CurrencyLayerDto currencyLayerDto;

    @Override
    public Mono<CurrencyLayerDto> getCurrencyRate() {
        Mono<CurrencyLayerDto> currencyLayerDtoMono = webClient.get().uri("/live?access_key=" + accessKey+ "&source=" + source + "&currencies=" + currencies)
                .retrieve()
                .bodyToMono(CurrencyLayerDto.class);


        return currencyLayerDtoMono;
    }
}
