package nitezh.ministock.utils;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Locale;

import static org.junit.Assert.*;


public class CurrencyToolsTest {

    @Test
    public void TestCurrencySymbol(){

        CurrencyTools mockCurrencyTools = Mockito.mock(CurrencyTools.class);
        assertEquals(mockCurrencyTools.addCurrencyToSymbol("100", "$"), "$100");

    }

}