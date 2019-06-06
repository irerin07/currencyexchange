package com.example.currencyexchange.dto;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/*
{
success: true,
terms: "https://currencylayer.com/terms",
privacy: "https://currencylayer.com/privacy",
timestamp: 1559562905,
source: "USD",
quotes: {}
}

{
    "success": false,
    "error": {
        "code": 104,
        "info": "Your monthly usage limit has been reached. Please upgrade your subscription plan."
  }
}
 */

@Data
public class CurrencyLayerDto {
    private boolean success;
    private String terms;
    private String privacy;
    private int timestamp; //unix timestamp로 저장이 된다.
    private String source;
    private HashMap<String, Double> quotes; //Contains all exchange rate values, consisting of the currency pairs and their respective conversion rates.
    private HashMap<String, Double> error;


}
