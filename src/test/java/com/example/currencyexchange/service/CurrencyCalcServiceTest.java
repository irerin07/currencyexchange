package com.example.currencyexchange.service;

import com.example.currencyexchange.dto.CurrencyLayerDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyCalcServiceTest {

    @Autowired
    CurrencyLayerAPIService currencyLayerAPIService;
    @Autowired
    CurrencyCalcService currencyCalcService;

    @Test
    public void 환율정보_불러온뒤_비교(){
        Double currencyRate = currencyCalcService.getExchangeRate("USD", "JPY", 0.0);
        CurrencyLayerDto currencyLayerDto = currencyLayerAPIService.getCurrencyRate();
        Assert.assertEquals(currencyLayerDto.getQuotes().get("USDJPY"), currencyRate);
    }

    @Test
    public void 잘못된_국가_정보(){
        Double currencyRate = currencyCalcService.getExchangeRate("USD", "KRY", 100.0);
        Assert.assertNull(currencyRate);
    }

    @Test
    public void 잘못된_금액_정보(){
        Double currencyRate = currencyCalcService.getExchangeRate("USD", "KRY", 222343.0);
        Assert.assertNull(currencyRate);
    }
}
