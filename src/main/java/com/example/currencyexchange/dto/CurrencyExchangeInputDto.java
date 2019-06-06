package com.example.currencyexchange.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CurrencyExchangeInputDto {
    @NotBlank
    private String from;
    @NotBlank
    private String to;
    @Min(0)
    @Max(10000)
    private double amountSending;
}
