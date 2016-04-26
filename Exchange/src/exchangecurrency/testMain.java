package exchangecurrency;



import java.util.ArrayList;
import java.util.List;




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
        List testRate = webserve.getRate();

        Gui.go();
    }
}
