package com.example.currencyexchange.service;

import com.example.currencyexchange.dto.CurrencyLayerDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyLayerAPIServiceTest {

    @Autowired
    CurrencyLayerAPIService currencyLayerAPIService;

    @Test
    public void 환율정보_불러오기(){
        CurrencyLayerDto searchResult = currencyLayerAPIService.getCurrencyRate();
        Assert.assertNotNull(searchResult);
        System.out.println(searchResult + " - passed");
    }

    @Test
    public void 리프레쉬레이트_안에_같은_환율정보_불러오기(){
        CurrencyLayerDto currencyLayerDto1 = currencyLayerAPIService.getCurrencyRate();
        CurrencyLayerDto currencyLayerDto2 = currencyLayerAPIService.getCurrencyRate();
        Assert.assertSame(currencyLayerDto1, currencyLayerDto2);
        System.out.println(currencyLayerDto1);
        System.out.println(currencyLayerDto2);
    }
}
