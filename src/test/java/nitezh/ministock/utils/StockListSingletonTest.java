package nitezh.ministock.utils;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by k_onzale on 30-Mar-18.
 */
public class StockListSingletonTest {

    @Test
    public void testGetData() throws Exception {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("GOOG");
        arrayList.add("FB");

        StockListSingleton mockSingleton = Mockito.mock(StockListSingleton.class);
        Mockito.when(mockSingleton.getData()).thenReturn(arrayList);

        ArrayList<String> stockList = mockSingleton.getData();
    }

    @Test
    public void testAddData() throws Exception {

    }

    @Test
    public void testGetInstance() throws Exception {

    }
}