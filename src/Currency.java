


import java.util.Arrays;
import java.util.List;



/*
 * Class: Currency
 * 
 */


public class Currency {
    private String name;
    private String code;
    private Rate rate;
    private boolean smallestDenomination = false;
    private static List noDecimalCodes = Arrays.asList("BIF","DJF","JPY",
            "KRW","PYG","VND","XAF","XPF","CLP","GNF","KMF","MGA","RWF",
            "VUV","XOF");


    //Constructor for currency
    public Currency(String name, String code, Rate rate)
    {
        this.name=name;
        this.code = code;
        this.rate.setRate(rate);
        if(noDecimalCodes.contains(code))
            smallestDenomination=true;
    }
    
    //Constructor for currency object for rate storage only
    public Currency(Rate rate)
    {
        this.rate = rate;
    }
    
    //Method to update currency rate given a Rate object
    public void updateRate(Rate rate)
    {
        this.rate = rate;
    }
    
    //Method to get currency name
    public String getName()
    {
        return this.name;
    }
    
    //Method to get currency code
    public String getCode()
    {
        return this.code;
    }
    
    //Method to get currency Rate
    public Rate getRate()
    {
        return this.rate;
    }
    
    //Method to return boolean that defines decimal places or not
    public boolean isSmallestDenomination()
    {
        return this.smallestDenomination;
    }
}
