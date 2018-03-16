package nitezh.ministock.utils;

import android.content.Context;

import junit.framework.TestCase;

import org.mockito.Mockito;

/**
 * Created by karlo on 2018-03-16.
 */
public class ListProviderTest extends TestCase {
    public void testStockURLStringBuilder() throws Exception {
        ListProvider mockListProvider = Mockito.mock(ListProvider.class);
        Mockito.when(mockListProvider.stockURLStringBuilder("GOOG")).thenReturn("https:api.iextrading.com/1.0/stock/GOOG/price");

        String cryptoURL = mockListProvider.stockURLStringBuilder("GOOG");
        Mockito.verify(mockListProvider).stockURLStringBuilder("GOOG");
        assertEquals(cryptoURL, "https:api.iextrading.com/1.0/stock/GOOG/price");

    }

    public void testCryptoURLStringBuilder() throws Exception {
        ListProvider mockListProvider = Mockito.mock(ListProvider.class);
        Mockito.when(mockListProvider.cryptoURLStringBuilder("BTC")).thenReturn("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=CAD");

        String cryptoURL = mockListProvider.cryptoURLStringBuilder("BTC");
        Mockito.verify(mockListProvider).cryptoURLStringBuilder("BTC");
        assertEquals(cryptoURL, "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=CAD");
    }

}