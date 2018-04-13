package nitezh.ministock.utils;

import org.json.JSONObject;
import org.junit.Assume;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class UrlDataToolsTests {

    @Test
    public void testDataRetrievalFromIex() throws IOException {
        // SkipIf
        Assume.assumeTrue(System.getenv("TRAVIS_CI") == null);

        // Arrange
        String url = "https://api.iextrading.com/1.0/stock/market/batch?symbols=aapl&types=quote";

        // Act
        String result = UrlDataTools.urlToString(url).substring(0, 33);

        // Assert
        String expected = "{\"AAPL\":{\"quote\":{\"symbol\":\"AAPL\"";
        assertEquals(expected, result);
    }

    @Test
    public void testGetCachedUrlData() throws Exception{
        /*
        // SkipIf
        Assume.assumeTrue(System.getenv("TRAVIS_CI") == null);

        // Arrange
        String url = "https://api.iextrading.com/1.0/stock/market/batch?symbols=aapl&types=quote";
        Cache cache = new Cache() {
            @Override
            protected JSONObject loadCache() {
                return null;
            }

            @Override
            protected void persistCache(JSONObject cache) {

            }
        };

        //Act
        String result = UrlDataTools.getCachedUrlData(url, cache, 300);

        //Assert
        String expected = "";
        assertEquals(expected, result);
*/
    }

}
