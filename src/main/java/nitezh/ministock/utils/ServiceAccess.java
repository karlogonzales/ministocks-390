package nitezh.ministock.utils;

import android.os.AsyncTask;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Rohana on 8/13/2017.
 */

public class ServiceAccess{

//    final String baseURL = "https://www.alphavantage.co/query?";
//    final String apiKey = "VDP0T5TTSS2C30O8";
//
//    public String stockURLStringBuilder(String stock){
//        return baseURL + "function=TIME_SERIES_INTRADAY&symbol=" + stock + "&interval=1min&apikey=" + apiKey;
//    }
//
//    public String cryptoURLStringBuilder(String crypto){
//        return baseURL+ "function=DIGITAL_CURRENCY_INTRADAY&symbol=" + crypto + "&market=CAD&apikey=" +apiKey;
//
//    https://api.iextrading.com/1.0/stock/goog/price
//    }

    public String execute(String target,String method) throws Exception{

            OkHttpClient client = new OkHttpClient();

            String jsonData = "";

            if (method.equals("GET")) {
                Request request = new Request.Builder()
                        .url(target)
                        .get()
                        .addHeader("content-type", "application/json")
                        .addHeader("cache-control", "no-cache")
                        .build();
                Response response = client.newCall(request).execute();
                jsonData = response.body().string();
            }

            return jsonData;


// https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=VDP0T5TTSS2C30O8
    }


}