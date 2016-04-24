package exchangecurrency;





import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rcool
 */
public class ExchangeRateWebService {
    
    private static final String baseUrl = "https://finance.yahoo.com/webservice/v1/symbols/allcurrencies/quote?format=json";
    
    //Method returns a single exhange rate 
    public Rate getRate(String symbol)
    {
        JSONParser jparse = new JSONParser();
        Rate returnRate = null;
        
        try{
            URL openURL = new URL(baseUrl);
            URLConnection urlConnect = openURL.openConnection();
            BufferedReader inReader = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));
            
            JSONObject jobj = (JSONObject)jparse.parse(inReader);
            JSONObject list = (JSONObject) jobj.get("list");
            JSONArray resources = (JSONArray) list.get("resources");
            
            Iterator i = resources.iterator();
            while(i.hasNext())
            {
                JSONObject j2 = (JSONObject)i.next();
            }
            System.out.println("success");
        } catch (IOException | ParseException e) {
            return null;
        }
        return returnRate;
    }    
}
