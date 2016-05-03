/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangecurrency;

import java.util.Arrays;
import java.util.List;

/**
 * University of Maryland: University College
 * CMSC495 Group 2
 * Class      : ExchangeCurrencyDB
 * Created on : Apr 23, 2016
 * Author     : Brandon Trexler
 */
public class Currency {

    private String code;
    private Double rate;
    private String lastUpdated;
    private boolean usesDecimals = true;
    private static List<String> noDecimalCodes = Arrays.asList("BIF", "DJF", "JPY",
            "KRW", "PYG", "VND", "XAF", "XPF", "CLP", "GNF", "KMF", "MGA", "RWF",
            "VUV", "XOF");

    //Default constructor

    public Currency() {

    }

    //Real constructor
    public Currency(String code, Double rate, String lastUpdated) {
        this.code = code;
        this.rate = rate;
        this.lastUpdated = lastUpdated;
        if (noDecimalCodes.contains(code)) {
            this.usesDecimals = false;
        }
    }

    //Setters
    public void setCode(String code) {
        this.code = code;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setDate(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    //Getters
    public String getCode() {
        return this.code;
    }

    public Double getRate() {
        return this.rate;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public boolean getDecimalUsage() {
        return this.usesDecimals;
    }
}
