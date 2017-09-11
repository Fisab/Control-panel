package com.example.fisab.control;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Fisab on 11.09.2017.
 */

public class Requests {
    public Integer status_code;
    private HttpURLConnection conn = null;
    private TextView log = null;
    private Thread thread_for_get;
    public Requests(TextView log) {
        this.log = log;
    }
    public Requests(){}

    public String get_text() {
        if(conn == null){
            return null;
        }
        try {
            thread_for_get.join();
        }
        catch (Exception e){}

        String result = null;
        StringBuffer sb = new StringBuffer();
        InputStream is = null;

        try {
            is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            result = sb.toString();
        }
        catch (Exception e) {
            result = null;
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                }
                catch (Exception e) {}
            }
        }
        if(log != null){
            log.setText(result);
        }
        return result;
    }

    public void get(final String link, final Integer timeout){
        thread_for_get = new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL(link);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(timeout);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                    conn.connect();
                    status_code = conn.getResponseCode();
                } catch (Exception e) {
                    Log.e("Read test error: ", e.getMessage());
                } finally {
                    conn.disconnect();
                }
            }
        });
        thread_for_get.start();
    }
}
