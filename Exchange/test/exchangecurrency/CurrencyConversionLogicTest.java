/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangecurrency;

import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ReginaldCarey
 */
public class CurrencyConversionLogicTest {

    public CurrencyConversionLogicTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetRate() {
        System.out.println("getRate");
        String fromCurrencyCode = "";
        String toCurrencyCode = "";
        CurrencyConversionLogic instance = new CurrencyConversionLogic();
        Double expResult = null;
        Double result = instance.getRate(fromCurrencyCode, toCurrencyCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testConvert() {
        System.out.println("convert");
        String fromCurrencyCode = "";
        String toCurrencyCode = "";
        Double amount = null;
        CurrencyConversionLogic instance = new CurrencyConversionLogic();
        Double expResult = null;
        Double result = instance.convert(fromCurrencyCode, toCurrencyCode, amount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCurrencyCodes() {
        System.out.println("getCurrencyCodes");
        CurrencyConversionLogic instance = new CurrencyConversionLogic();
        List<String> expResult = null;
        List<String> result = instance.getCurrencyCodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDecimalUsage() {
        System.out.println("getDecimalUsage");
        String currencyCode = "";
        CurrencyConversionLogic instance = new CurrencyConversionLogic();
        Boolean expResult = null;
        Boolean result = instance.getDecimalUsage(currencyCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetLastUpdatedDate() {
        System.out.println("getLastUpdatedDate");
        String sourceCurrencyCode = "";
        CurrencyConversionLogic instance = new CurrencyConversionLogic();
        String expResult = "";
        String result = instance.getLastUpdatedDate(sourceCurrencyCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsDateOld() {
        System.out.println("isDateOld");
        String dateString = "";
        CurrencyConversionLogic instance = new CurrencyConversionLogic();
        Boolean expResult = null;
        Boolean result = instance.isDateOld(dateString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
