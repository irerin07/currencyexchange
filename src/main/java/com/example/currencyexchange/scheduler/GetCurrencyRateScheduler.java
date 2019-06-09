package com.example.currencyexchange.scheduler;

import com.example.currencyexchange.service.CurrencyLayerAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetCurrencyRateScheduler {

    @Autowired
    CurrencyLayerAPIService currencyLayerAPIService;

    @Scheduled(fixedDelay = 3600000)
    public void updateCurrencyRates() {
        currencyLayerAPIService.getCurrencyRate();
    }
}
