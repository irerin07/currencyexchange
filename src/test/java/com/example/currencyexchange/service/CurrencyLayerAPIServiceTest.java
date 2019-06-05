package com.example.currencyexchange.service;

import com.example.currencyexchange.dto.CurrencyLayerDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyLayerAPIServiceTest {

    @Autowired
    CurrencyLayerAPIService currencyLayerAPIService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyLayerAPIService.class);

    @Test
    public void 환율정보_불러오기(){
//        Mono<CurrencyLayerDto> searchResult = currencyLayerAPIService.getCurrencyRate();
//        Assert.assertNotNull(searchResult);
//        searchResult.doOnNext(r->{
//            System.out.println("result: " + r.getQuotes());
//        } ).subscribe();
////        LOGGER.info("test result: {}", searchResult.block());
    }

}
