package nitezh.ministock.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by k_onzale on 15-Mar-18.
 */
public class ServiceAccessTest {

    @Test
    public void testExecute() throws Exception {
        new ServiceAccess().execute("STOCK", "GOOG");

    }
}