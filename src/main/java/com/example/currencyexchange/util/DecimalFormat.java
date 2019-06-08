package com.example.currencyexchange.util;

public class DecimalFormat {
    private static final java.text.DecimalFormat doubleFormat = new java.text.DecimalFormat("#,##0.00");

    public static String convertDoubleToString(Double currency){
        return doubleFormat.format(currency);
    }
}
