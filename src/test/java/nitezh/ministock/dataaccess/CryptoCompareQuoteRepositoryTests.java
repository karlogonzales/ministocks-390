package nitezh.ministock.dataaccess;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import nitezh.ministock.domain.StockQuote;
import nitezh.ministock.mocks.MockCache;

import static org.junit.Assert.assertEquals;

public class CryptoCompareQuoteRepositoryTests {

    private CryptoCompareQuoteRepository cryptoRepository;

    @Before
    public void setUp() {
        this.cryptoRepository = new CryptoCompareQuoteRepository();
    }

    @Test
    public void getQuotes() {
        //Skipif
        Assume.assumeTrue(System.getenv("TRAVIS_CI") == null);

        //Arrange
        List<String> symbols = Arrays.asList("BTC", "ETH");
        MockCache mockCache = new MockCache();

        // Act
        HashMap<String, StockQuote> stockQuotes = cryptoRepository.getQuotes(mockCache, symbols);

        //Assert
        assertEquals(2, stockQuotes.size());

        StockQuote btcQuote = stockQuotes.get("BTC");
        assertEquals("BTC", btcQuote.getSymbol());
        assertEquals("CCCAGG", btcQuote.getExchange()); //CCCAGG refers to Crypto Compare Aggregate or the aggregate of all exchanges the API gets its price from

        StockQuote ethQuote = stockQuotes.get("ETH");
        assertEquals("ETH", ethQuote.getSymbol());
        assertEquals("CCCAGG", ethQuote.getExchange()); //CCCAGG refers to Crypto Compare Aggregate or the aggregate of all exchanges the API gets its price from
    }

    @Test
    public void buildRequestUrl() {
        List<String> symbols = Arrays.asList("BTC", "ETH");
        String expected = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=BTC,ETH&tsyms=CAD";

        assertEquals(expected, this.cryptoRepository.buildRequestUrl(symbols));
    }


}
