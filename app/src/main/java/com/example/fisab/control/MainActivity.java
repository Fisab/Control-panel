package com.example.fisab.control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public TextView log;


    public android.view.View.OnClickListener myButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Requests r = new Requests(log);
            r.get("http://192.168.1.65:5000/mute", 5000);
            r.get_text();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(myButtonClickListener);
        log = (TextView) findViewById(R.id.textView);


    }
}
