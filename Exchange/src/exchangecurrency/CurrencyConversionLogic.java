/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangecurrency;

/**
    University of Maryland: University College
    CMSC495 Group 2
    Class      : CurrencyConversionLogic
    Created on : Apr 24, 2016
    Author     : Jason Dudash

 */
public class CurrencyConversionLogic {
    private Double conversionRate;

    private Double fromRate;
    private Double toRate;

    ExchangeRateDB dbService = new ExchangeRateDB();

    public Double getRate(String fromCurrencyCode, String toCurrencyCode) {
        Double rate;

        this.fromRate = dbService.getRate(fromCurrencyCode);
        this.toRate = dbService.getRate(toCurrencyCode);
        rate = this.toRate / this.fromRate;
        return rate;
    }

    public Double convert(String fromCurrencyCode, String toCurrencyCode, Double amount) {
        Double newAmount;

        this.conversionRate = this.getRate(fromCurrencyCode, toCurrencyCode);
        newAmount = amount * this.conversionRate;
        return newAmount;
    }

    public Double getConversionRate() {
        return this.conversionRate;
    }

}
