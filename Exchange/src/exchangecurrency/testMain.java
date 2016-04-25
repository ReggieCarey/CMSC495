package exchangecurrency;


import exchangecurrency.ExchangeRateWebService;
import exchangecurrency.ExchangeRateDB;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rcool
 */
public class testMain {
    public static void main(String[] args) {
        ExchangeRateWebService webserve = new ExchangeRateWebService();
        ArrayList<Currency> currentRate = null;
        
        ExchangeRateDB database = new ExchangeRateDB();
        
        database.updateRates(currentRate);
        System.out.println(database.getRate("XAG"));
        System.out.println(database.getUpdatedTime("XAG"));
    }
}
