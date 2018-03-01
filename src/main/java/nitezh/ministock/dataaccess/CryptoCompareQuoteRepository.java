package nitezh.ministock.dataaccess;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import nitezh.ministock.utils.Cache;
import nitezh.ministock.utils.UrlDataTools;

public class CryptoCompareQuoteRepository {
    private static final String BASE_URL = "https://min-api.cryptocompare.com/data/pricemulti?";

    private String buildRequestUrl(List<String> symbols){
        StringBuilder sQuery = new StringBuilder();

        for (String s : symbols){
            if (!s.equals("")){
                if(!sQuery.toString().equals("")){
                    sQuery.append(",");
                }
                sQuery.append(s);
            }
        }
        return String.format("%s%s%s", BASE_URL,sQuery, "&tsyms=USD");
    }
    public JSONArray retrieveQuotesAsJson(Cache cache) throws JSONException {
        String url = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=JPY,EUR";

        String data = UrlDataTools.getCachedUrlData(url, cache, 300);

        String json = data.replace("//", "").replaceAll("\\\\", "");

        return new JSONArray("[" + json + "]");
    }
}
