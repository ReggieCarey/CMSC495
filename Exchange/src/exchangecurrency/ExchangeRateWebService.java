package exchangecurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
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

    public ArrayList<Currency> getRate() {
        JSONParser jparse = new JSONParser();
        ArrayList<Currency> returnRates = new ArrayList<>();

        try {
            URL openURL = new URL(baseUrl);
            URLConnection urlConnect = openURL.openConnection();
            urlConnect.setConnectTimeout(500);
            urlConnect.setReadTimeout(500);
            BufferedReader inReader = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));

            JSONObject jobj = (JSONObject) jparse.parse(inReader);
            JSONObject list = (JSONObject) jobj.get("list");
            JSONArray resources = (JSONArray) list.get("resources");

            for (Object i : resources) {
                JSONObject j2 = (JSONObject) i;
                JSONObject j3 = (JSONObject) j2.get("resource");
                JSONObject j4 = (JSONObject) j3.get("fields");
                String code = ((String) j4.get("symbol")).substring(0, 3);
                double rate = Double.parseDouble((String) j4.get("price"));
                returnRates.add(new Currency(code, rate, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())));
            }
        } catch (IOException | ParseException e) {
            JOptionPane.showMessageDialog(null, "Problem encountered refreshing data from the internet", "Data Feed Problem", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return returnRates;
    }
}
