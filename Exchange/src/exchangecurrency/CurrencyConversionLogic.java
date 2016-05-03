/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangecurrency;

import java.util.List;

/**
 * University of Maryland: University College
 * CMSC495 Group 2 Class : CurrencyConversionLogic
 * Created on : Apr 24, 2016
 * Author : Jason Dudash
 *
 * Changes
 * Added Constructor, Reggie, May 1,2015
 * Added getCurrencyCode, Reggie, May 1, 2015
 * Adjusted rate calculation, Reggie, May 1, 2015
 *
 */
public class CurrencyConversionLogic {

    private Double fromRate;
    private Double toRate;

    ExchangeRateDB dbService = new ExchangeRateDB();
    ExchangeRateWebService webService = new ExchangeRateWebService();

    public CurrencyConversionLogic() {
        dbService.updateRates(webService.getRate());
    }

    public Double getRate(String fromCurrencyCode, String toCurrencyCode) {
        Double rate;

        this.fromRate = dbService.getRate(fromCurrencyCode);
        this.toRate = dbService.getRate(toCurrencyCode);
        rate = this.toRate / this.fromRate;
        return rate;
    }

    public Double convert(String fromCurrencyCode, String toCurrencyCode, Double amount) {
        Double newAmount;

        newAmount = amount * this.getRate(fromCurrencyCode, toCurrencyCode);
        return newAmount;
    }

    public List<String> getCurrencyCodes() {
        return dbService.getCurrencyCodes();
    }

    public Boolean getDecimalUsage(String currencyCode) {
        return dbService.getDecimalUsage(currencyCode);
    }

    public String getLastUpdatedDate(String sourceCurrencyCode) {
        return dbService.getUpdatedTime(sourceCurrencyCode);
    }

}
