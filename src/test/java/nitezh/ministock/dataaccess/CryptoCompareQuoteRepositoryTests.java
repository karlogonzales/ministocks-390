package nitezh.ministock.dataaccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import nitezh.ministock.mocks.MockCache;
import nitezh.ministock.utils.UrlDataTools;

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

    @Test
    public void responseTest(){
        MockCache mockCache1 = new MockCache();
        MockCache mockCache2 = new MockCache();
        List<String> symbols = Arrays.asList("BTC");
        String responseTest = UrlDataTools.getCachedUrlData("https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC&tsyms=USD",mockCache1,300);
        assertEquals(UrlDataTools.getCachedUrlData(this.cryptoRepository.buildRequestUrl(symbols),mockCache2,300), responseTest);

    }
}
