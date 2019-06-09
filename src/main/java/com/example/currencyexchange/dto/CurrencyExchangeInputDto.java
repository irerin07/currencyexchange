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
    @Min(value = 0, message = "송금 금액은 0USD미만일 수 없습니다.")
    @Max(value = 10000, message = "송금 금액은 10,000USD를 넘길 수 없습니다.")
    private double amount;
}
