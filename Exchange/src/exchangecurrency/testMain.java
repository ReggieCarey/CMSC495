package exchangecurrency;


<<<<<<< HEAD
import exchangecurrency.ExchangeRateWebService;
import exchangecurrency.ExchangeRateDB;
import java.sql.SQLException;
import java.util.ArrayList;
=======
import java.util.List;
>>>>>>> origin/master



/**
 *
 * @author rcool
 */
public class testMain {
    public static void main(String[] args) {
        ExchangeRateWebService webserve = new ExchangeRateWebService();
<<<<<<< HEAD
        ArrayList<Currency> currentRate = null;
        
        ExchangeRateDB database = new ExchangeRateDB();
        
        database.updateRates(currentRate);
        System.out.println(database.getRate("XAG"));
        System.out.println(database.getUpdatedTime("XAG"));
=======
        List testRate = webserve.getRate();
        Gui gui = new Gui();
        gui.startGui();
>>>>>>> origin/master
    }
}
