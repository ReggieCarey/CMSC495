package exchangecurrency;


import java.util.List;



/**
 *
 * @author rcool
 */
public class testMain {
    public static void main(String[] args) {
        ExchangeRateWebService webserve = new ExchangeRateWebService();
        List testRate = webserve.getRate();
        Gui gui = new Gui();
        gui.startGui();
    }
}
