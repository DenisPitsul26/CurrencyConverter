package com.example.zar.currencyconverter;


public class ExchangeListData {
    private String countryCode, countryName, currentTime, amount;
    private int countryFlag;

    public ExchangeListData(String countryCode, String countryName, int countryFlag, String currentTime, String amount) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.countryFlag = countryFlag;
        this.currentTime = currentTime;
        this.amount = amount;

    }


    public int getCountryFlag() {
        return countryFlag;
    }

    public String getAmount() {
        return amount;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getCountryName() {
        return countryName;
    }

}
