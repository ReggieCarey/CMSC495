
package exchangecurrency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
    University of Maryland: University College
    CMSC495 Group 2
    Class      : ExchangeCurrencyDB
    Created on : Apr 23, 2016
    Author     : Brandon Trexler

 */
public class ExchangeRateDB {

    public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String JDBC_URL = "jdbc:derby:exchangedb;create=true";
    public DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public Date currentDate = new Date();

    //This method updates the exchange table in derby.
    public void updateRates(ArrayList<Currency> currentPull) {

        Connection conn = getConnection();

        /*
            This if statement determines if there is an internet connection and cancels
            the update if there isn't one.
        */
        if(currentPull == null) {

            System.out.println("No internet Connection, cannot update rates.");

        /*
            This else statement executes if there is an internet connection dropping the current table
            and recreating the new one.
        */
        } else {
            try {
                conn.createStatement().execute("DROP TABLE exchange");
                conn.createStatement().execute("create table exchange(code VARCHAR(4), rate DOUBLE, date VARCHAR(11))");
                System.out.println("table created.");
            } catch(Exception e) {
                
                e.printStackTrace();
                
            }

            for(int i = 1; i < currentPull.size(); i++) {
                try {
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO exchange"
                            + "(CODE, RATE, DATE) VALUES "
                            + "(?, ?, ?)");
                    Currency tempCurrency = currentPull.get(i);

                    ps.setString(1, tempCurrency.getCode());
                    ps.setDouble(2, tempCurrency.getRate());
                    ps.setString(3, dateFormat.format(currentDate));
                    ps.executeUpdate();
                    
                    System.out.println("row add " + tempCurrency.getCode());                    
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        try {
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM exchange");
            ResultSet res = null;
            int count = 1;
            
            res = prep.executeQuery();
            
            while(res.next()) {
                System.out.println("from DB: " + res.getString("date"));
            }
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
        //Closing the connection to the database.
        try {
            if(!conn.isClosed())
            conn.close();
        } catch (Exception e) {

        }
    }

    /*
        Getter that returns an ArrayList of the currency codes.
    */
    public ArrayList getCurrencyCodes() {
        Connection conn = getConnection();
        ArrayList codes = null;

        try{
           PreparedStatement ps = conn.prepareStatement("SELECT code FROM exchange" );

           ResultSet results = null;
           results = ps.executeQuery();

           while(results.next()) {
               codes.add(results.getString("code"));
           }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(!conn.isClosed())
                    conn.close();
            } catch (Exception e) {

            }
        }


        return codes;
    }

    /*
        Getter that returns a rate based upon a currency code provided.
    */
    public Double getRate(String code) {
        Connection conn = getConnection();
        Double rate = null;

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT rate FROM exchange WHERE code = '" + code + "'");

            ResultSet results = null;
            results = ps.executeQuery();

            while(results.next()) {
                rate = results.getDouble("rate");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(!conn.isClosed())
                    conn.close();
            } catch (Exception e) {

            }
        }

        return rate;
    }

    /*
        Getter that returns the last time the code was updated based upong
        a code provided.
    */
    public String getUpdatedTime(String code) {
        Connection conn = getConnection();
        String updatedTime = new String();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT date FROM exchange WHERE code = " + code + "");
            ResultSet results = null;

            results = ps.executeQuery();

            while(results.next()) {
                updatedTime = results.getString("date");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(!conn.isClosed())
                    conn.close();
            } catch (Exception e) {

            }
        }

        return updatedTime;
    }

    //This method creates a connection to the Derby database which is embedded.
    private Connection getConnection() {
        Connection c = null;

        try {

            Class.forName(DRIVER);
            c = DriverManager.getConnection(JDBC_URL);
            if (c != null) {
                System.out.println("Connected to database.");
            }
            
        } catch (ClassNotFoundException e){

            System.out.println("Where is your Derby driver?");
            e.printStackTrace();

        } catch (SQLException ex) {
            Logger.getLogger(ExchangeRateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return c;
    }

}
