package com.example.currencyexchange.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CurrencyExchangeInputDto {
    @NotBlank
    private String from;
    @NotBlank
    private String to;

    @NotNull
    @Min(0)
    @Max(10000)
    private double amount;
}
