package com.example.fshowta.postservice;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fshowta on 2017/02/16.
 */

public class AsyncHttp extends AsyncTask<String, String, Boolean> {
    HttpURLConnection urlConnection = null;
    Boolean flg = false;

    String name;
    String value;

    public AsyncHttp(String name, String value){
        this.name = name;
        this.value = value;
    }

    //非同期処理ここから
    @Override
    protected  Boolean doInBackground(String... contents){
        String urlinput = "http://10.0.2.2/upload/post.php";
        try{
            URL url = new URL(urlinput);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            //POST用パラメータ
            String postDataSample = "name=" + name + "&text=" + value;
            //POSTパラメータ設定
            OutputStream out = urlConnection.getOutputStream();
            out.write(postDataSample.getBytes());
            out.flush();
            out.close();
            Log.d("test", postDataSample);
            //レスポンスを受け取る
            urlConnection.getInputStream();

            flg = true;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return flg;
    }
}
