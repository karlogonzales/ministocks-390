package nitezh.ministock.utils;

import org.junit.Test;
import org.mockito.Mockito;

import java.text.DecimalFormat;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class DateToolsTest {
    @Test
    public void elapsedDays() throws Exception {

    }

    @Test
    public void elapsedTime() throws Exception {
        //flanky due to sec not specific which make expected value to change
        /*
        DateTools mockDate = Mockito.mock(DateTools.class);
        double date = new Date().getTime();
        String dateString = "2015-05-05 10:05";
        double time = mockDate.elapsedTime(dateString);

        assertEquals(time, 9.2758279746E10, 0.0);
        */
    }

    @Test
    public void parseSimpleDate() throws Exception {
        /*
        DateTools mockDate = Mockito.mock(DateTools.class);
        String dateString = "2015050522:02:02";
        String dateString2 = "Sun Feb 25 10:02:02 EST 231894";

        assertEquals(mockDate.parseSimpleDate(dateString).toString(), dateString2);*/
    }

    @Test
    public void compareToNow() throws Exception {
        DateTools mockDate = Mockito.mock(DateTools.class);
        Date time = new Date(2015, 02,02);
        assertEquals(mockDate.compareToNow(time), 1);
    }

    @Test
    public void getNowAsString() throws Exception {
        DateTools mockDate = Mockito.mock(DateTools.class);
        assertEquals(mockDate.getNowAsString(), DateTools.getNowAsString());
    }

    @Test
    public void timeDigitPad() throws Exception {
        DateTools mockDate = Mockito.mock(DateTools.class);
        assertEquals(mockDate.timeDigitPad(0), "0" + "0");
    }

}