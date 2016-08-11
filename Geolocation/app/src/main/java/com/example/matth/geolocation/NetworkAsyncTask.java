package com.example.matth.geolocation;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by matth on 7/16/2016.
 */
public class NetworkAsyncTask extends AsyncTask<String, Void, Void>
{
    @Override
    protected Void doInBackground(String... params)
    {
        OkHttpClient client = new OkHttpClient();
        String url = "http://10.10.31.165:420";
        String lati = params[0];
        String longi = params[1];

        RequestBody body = new FormBody.Builder().add("a", "b").build();
        //Error checker
        Log.d("1", "output");
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("latitude: ", lati)
                .addHeader("longitude: ", longi)
                .build();
        Log.d("build completed", "output");
        try{
            Response response = client.newCall(request).execute();
        }catch (IOException e){
            Log.e("Error", "response");
        }
        return null;
    }
}
