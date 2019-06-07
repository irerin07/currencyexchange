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
//    @Pattern(regexp = "[+-]?([0-9]*[.])?[0-9]+", message = "숫자만 입력해 주세요.")
    private double amount;
}
