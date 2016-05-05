/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangecurrency;

import org.junit.After;
import static org.junit.Assert.assertEquals;
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
    public void testIsDateOld() {
        System.out.println("isDateOld");
        String dateString = "May 3, 2016 8:38 PM";
        CurrencyConversionLogic instance = new CurrencyConversionLogic();
        Boolean expResult = true;
        Boolean result = instance.isDateOld(dateString);
        assertEquals(expResult, result);
    }

}
