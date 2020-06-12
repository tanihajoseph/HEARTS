package com.example.taniha.hearts;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Timer;
import java.util.TimerTask;

public class NewActivity extends Activity {
    Button button;
    //Button button1;
   EditText etext1;
   EditText etext2;
   EditText etext3;
   String phoneno1;
   String phoneno2;
   String phoneno3;
   Timer timer;
   TimerTask timerTask;
    String s;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.page2);
        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.button11);
        //button1 = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(View arg0) {
                Intent intent1=getIntent();
                Bundle bundle0=intent1.getExtras();

                s=bundle0.getString("s");
                etext1 = (EditText) findViewById(R.id.editText7);
                phoneno1 = etext1.getText().toString();
                etext2 = (EditText) findViewById(R.id.editText3);
                phoneno2 = etext2.getText().toString();
                etext3 = (EditText) findViewById(R.id.editText4);
                phoneno3 = etext3.getText().toString();
                Intent myIntent = new Intent(NewActivity.this, NewActivity2.class);
                Bundle bundle1=new Bundle();
                bundle1.putString("ph1",phoneno1);
                bundle1.putString("ph2",phoneno2);
                bundle1.putString("ph3",phoneno3);
                bundle1.putString("s",s);
                bundle1.putInt("flag_int",1);
                myIntent.putExtras(bundle1);
                startActivity(myIntent);
               /* try {

                    //timerTask=new TimerTask(){
                      // public void run() {
                            /*SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneno1, "null", " check", null, null);
                            smsManager.sendTextMessage(phoneno2, "null", "check", null, null);
                            smsManager.sendTextMessage(phoneno3, "null", "check", null, null);

                        //}
                    //};
                   // timer = new Timer();
                   //timer.schedule(timerTask, 1000, 300000);

                }*/
                /*
                catch (Exception e) {
                    System.out.println(e);
                    System.out.println("Not working");


                }*/


            }



    });

}
}












