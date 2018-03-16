package nitezh.ministock.utils;

import android.content.Context;
import android.os.AsyncTask;

import java.net.ConnectException;

public class ServiceTask extends AsyncTask<String, String, String> {

    private Exception exception;

    private Context mView;

    public ServiceTask(Context v) {
        mView = v;
    }


    protected String doInBackground(String... data) {
        try {


            return new ServiceAccess().execute(data[0], data[1]);

        } catch (Exception e) {
            this.exception = e;
            e.printStackTrace();
            return null;
        }
    }

    protected void onPostExecute(String data) {
        // TODO: check this.exception
        // TODO: do something with the feed

        if (this.exception instanceof ConnectException) {
        }

    }
}