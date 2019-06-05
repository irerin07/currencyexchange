package com.example.currencyexchange.service;

import com.example.currencyexchange.dto.CurrencyLayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;
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

    private Mono<CurrencyLayerDto> currencyLayerDtoMono;
    private CurrencyLayerDto currencyLayerDto;

    @Override
    public CurrencyLayerDto getCurrencyRate() {
            currencyLayerDtoMono = webClient.get().uri("/live?access_key=" + accessKey + "&source=" + source + "&currencies=" + currencies)
                    .retrieve()
                    .bodyToMono(CurrencyLayerDto.class);
            currencyLayerDto = currencyLayerDtoMono.block();

            if(currencyLayerDto == null){
                throw new RestClientException("환율정보를 불러오지 못했습니다.");
            }else if(!currencyLayerDto.isSuccess()){
                throw new RestClientException("API 호출간 에러가 발생했습니다."
                        + currencyLayerDto.getError().get("code") + " : "
                        + currencyLayerDto.getError().get("type"));
            }
            return currencyLayerDto;
    }

}
