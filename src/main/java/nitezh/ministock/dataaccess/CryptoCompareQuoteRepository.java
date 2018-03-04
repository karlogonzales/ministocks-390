package nitezh.ministock.dataaccess;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import nitezh.ministock.domain.StockQuote;
import nitezh.ministock.utils.Cache;
import nitezh.ministock.utils.UrlDataTools;

public class CryptoCompareQuoteRepository {
    private static final String BASE_URL = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=";

    public HashMap<String, StockQuote> getQuotes(Cache cache, List<String> symbols) {
        HashMap<String, StockQuote> quotes = new HashMap<>();
        JSONObject jsonResponse;
        JSONObject jsonStock;

        try{
            jsonResponse = retrieveQuotesAsJson(cache, symbols).getJSONObject("RAW");

            for (String symbol : symbols) {
                jsonStock = jsonResponse.getJSONObject(symbol).getJSONObject("CAD");
                StockQuote quote = new StockQuote(
                        jsonStock.optString("FROMSYMBOL"),
                        jsonStock.optString("PRICE"),
                        jsonStock.optString("CHANGE24HOUR"),
                        jsonStock.optString("CHANGEPCT24HOUR"),
                        jsonStock.optString("MARKET"),
                        jsonStock.optString("VOLUMEDAY"),
                        jsonStock.optString("FROMSYMBOL"),
                        Locale.US

                );

                quotes.put(quote.getSymbol(), quote);
            }
        } catch (JSONException ignored){

        }

        return quotes;
    }

    public String buildRequestUrl(List<String> symbols){
        StringBuilder sQuery = new StringBuilder();

        for (String s : symbols){
            if (!s.equals("")){
                if(!sQuery.toString().equals("")){
                    sQuery.append(",");
                }
                sQuery.append(s);
            }
        }
        return String.format("%s%s%s", BASE_URL,sQuery, "&tsyms=CAD");
    }

    public JSONObject retrieveQuotesAsJson(Cache cache, List<String> symbols) throws JSONException {
        String url = this.buildRequestUrl(symbols);
        String data = UrlDataTools.getCachedUrlData(url, cache, 300);

        String json = data.replace("//", "").replaceAll("\\\\", "");

        return new JSONObject(json);
    }
}
