package nitezh.ministock.dataaccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import nitezh.ministock.mocks.MockCache;

import static org.junit.Assert.assertNotNull;

public class CryptoCompareQuoteRepositoryTests {

    private CryptoCompareQuoteRepository cryptoRepository;

    @Before
    public void setUp() {
        this.cryptoRepository = new CryptoCompareQuoteRepository();
    }

    @Test
    public void getQuotesAsJson() throws JSONException {
        JSONArray stockQuotes = cryptoRepository.retrieveQuotesAsJson(new MockCache());

        assertNotNull(stockQuotes);
    }
}
