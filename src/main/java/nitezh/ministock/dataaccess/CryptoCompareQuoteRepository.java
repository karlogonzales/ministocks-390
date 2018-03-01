package nitezh.ministock.dataaccess;

import org.json.JSONArray;
import org.json.JSONException;

import nitezh.ministock.utils.Cache;
import nitezh.ministock.utils.UrlDataTools;

public class CryptoCompareQuoteRepository {
    private static final String BASE_URL = "https://min-api.cryptocompare.com/data/";

    public JSONArray retrieveQuotesAsJson(Cache cache) throws JSONException {
        String url = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=JPY,EUR";

        String data = UrlDataTools.getCachedUrlData(url, cache, 300);

        String json = data.replace("//", "").replaceAll("\\\\", "");

        return new JSONArray("[" + json + "]");
    }
}
