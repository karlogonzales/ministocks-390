package nitezh.ministock.utils;

import junit.framework.TestCase;

import org.junit.Assume;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class ServiceAccessTest extends TestCase {
    public void testExecute() throws Exception {
        ServiceAccess mockServiceAccess = mock(ServiceAccess.class);
        Mockito.when(mockServiceAccess.execute("https:api.iextrading.com/1.0/stock/GOOG/price", "GET")).thenReturn("{1000.00}");
        String res = mockServiceAccess.execute("https:api.iextrading.com/1.0/stock/GOOG/price", "GET");

        Mockito.verify(mockServiceAccess).execute("https:api.iextrading.com/1.0/stock/GOOG/price", "GET");
        assertEquals(res, "{1000.00}");
    }

    public void testResponse() throws Exception {
        Assume.assumeTrue(System.getenv("TRAVIS_CI") == null);

        ServiceAccess serviceAccess = new ServiceAccess();
        String res = serviceAccess.execute("https:api.iextrading.com/1.0/stock/GOOG/price", "GET");

        assertEquals(res.isEmpty(), false);
    }
}