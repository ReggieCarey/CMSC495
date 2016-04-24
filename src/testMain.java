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
        Rate testRate = webserve.getRate("MXN");
        System.out.println(testRate.getRate());
    }
}
