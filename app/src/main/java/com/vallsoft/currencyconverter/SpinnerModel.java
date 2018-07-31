package com.vallsoft.currencyconverter;


public class SpinnerModel {
    private String currencyCode, currencyCountry;
    private int countryFlag;

    public SpinnerModel(String currencyCode, String currencyCountry, int countryFlag) {
        this.currencyCode = currencyCode;
        this.currencyCountry = currencyCountry;
        this.countryFlag = countryFlag;
    }

    public int getCountryFlag() {
        return countryFlag;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyCountry() {
        return currencyCountry;
    }
}

