package com.example.fisab.control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private void setButtonListener(final Button btn, final int typeButton){
        final int PLAY = 0;
        final int MUTE = 1;
        final int UNMUTE = 2;

        btn.setOnClickListener(new View.OnClickListener() {
            Requests r = new Requests();
            @Override
            public void onClick(View v) {
                switch (typeButton){
                    case PLAY:
                        r.get("http://192.168.0.12:5000/play", 5000);
                    case MUTE:
                        r.get("http://192.168.0.12:5000/mute", 5000);
                    case UNMUTE:
                        r.get("http://192.168.0.12:5000/unmute", 5000);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = (Button) findViewById(R.id.play);
        Button mute = (Button) findViewById(R.id.mute);
        Button unmute = (Button) findViewById(R.id.unmute);

        setButtonListener(play, 0);
        setButtonListener(mute, 1);
        setButtonListener(unmute, 2);
    }
}
