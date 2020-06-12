package com.example.taniha.hearts;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


public class NewActivity4 extends Activity {
    Button button;
    Button button2;
    //int flag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the view from activity_main.xml
        setContentView(R.layout.page4);
        button = (Button) findViewById(R.id.button5);
        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                System.exit(0);


            }
        });

        button2=(Button) findViewById(R.id.button12);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            finishAffinity();
            System.exit(0);

            }
        });
    }
}

