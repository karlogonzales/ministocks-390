package nitezh.ministock.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServiceAccess {


    public String execute(String target, String method) throws Exception {

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

    }

}
