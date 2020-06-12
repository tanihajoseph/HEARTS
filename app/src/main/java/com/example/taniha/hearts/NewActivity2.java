package com.example.taniha.hearts;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class NewActivity2 extends Activity {

    Button button;
    String phoneno1;
    String phoneno2;
    String phoneno3;
    String s;
    String fstr1,fstr2;
    Timer timer;
    TimerTask timerTask;
    int flag1,flag2;
    //BluetoothDevice mDevice;
    //Handler mHandler;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        // Get the view from activity_main.xml
        setContentView(R.layout.page3);
        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.button2);
        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent1=getIntent();
                Bundle bundle1=intent1.getExtras();

                flag1=bundle1.getInt("flag_int",3);
                fstr1=Integer.toString(flag1);

                try {
                    timer = new Timer();
                    if(flag1==1) {
                        phoneno1=bundle1.getString("ph1",null);
                        phoneno2=bundle1.getString("ph2",null);
                        phoneno3=bundle1.getString("ph3",null);
                        s=bundle1.getString("s");
                        timerTask = new TimerTask() {
                            public void run() {

                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(phoneno1, "null", " check"+s, null, null);
                                //smsManager.sendTextMessage(phoneno2, "null", "check", null, null);
                                //smsManager.sendTextMessage(phoneno3, "null", "check", null, null);
                            }
                        };

                        timer.schedule(timerTask, 10000, 30000);
                    }

                    }

                    catch (Exception e) {
                        System.out.println(e);
                        System.out.println("Not working");
                        }
                        Intent myIntent = new Intent(NewActivity2.this, NewActivity4.class);

                startActivity(myIntent);
                }

        });
    }



}