package nitezh.ministock.dataaccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import nitezh.ministock.mocks.MockCache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CryptoCompareQuoteRepositoryTests {

    private CryptoCompareQuoteRepository cryptoRepository;

    @Before
    public void setUp() {
        this.cryptoRepository = new CryptoCompareQuoteRepository();
    }

    @Test
    public void buildRequestUrl(){
        List<String> symbols = Arrays.asList("BTC", "ETH");
        String expected = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD";

        assertEquals(expected, this.cryptoRepository.buildRequestUrl(symbols));
    }
}
