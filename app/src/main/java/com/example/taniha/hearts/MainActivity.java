package com.example.taniha.hearts;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

public class MainActivity extends Activity {
    Button button;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mDevice;
    BluetoothSocket mSocket;
    InputStream mInStream;
    ParcelUuid[] uuids;
    Handler bluetoothIn;
    final int handlerState = 0;
    private ConnectedThread mConnectedThread;
    TextView txtString,txtStringLength,sensorView;
    StringBuilder recDataString = new StringBuilder();
    String sensor,dataInPrint,readMessage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.button);

        // Capture button clicks\



        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
               try{
//                   bluetoothIn = new Handler() {
//                       public void handleMessage(android.os.Message msg) {
//                           if (msg.what == handlerState) {                                     //if message is what we want
//                               readMessage = (String) msg.obj;                                                                // msg.arg1 = bytes from connect thread
//                               recDataString.append(readMessage);                                      //keep appending to string until ~
//                               int endOfLineIndex = recDataString.indexOf("~");                    // determine the end-of-line
//                               if (endOfLineIndex > 0) {                                           // make sure there data before ~
//                                   dataInPrint = recDataString.substring(0, endOfLineIndex);    // extract string
//                                   txtString.setText("Data Received = " + dataInPrint);
//                                   int dataLength = dataInPrint.length();                          //get length of data received
//                                   txtStringLength.setText("String Length = " + String.valueOf(dataLength));
//
//                                   if (recDataString.charAt(0) == '#')                             //if it starts with # we know it is what we are looking for
//                                   {
//                                       sensor = recDataString.substring(1, 5);             //get sensor value from string between indices 1-5
//
//
//                                       sensorView.setText(" Value " + sensor );    //update the textviews with sensor values
//
//                                   }
//                                   recDataString.delete(0, recDataString.length());                    //clear all string data
//                                   // strIncom =" ";
//                                   dataInPrint = " ";
//                               }
//                           }
//                       }
//                   };
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                if (mBluetoothAdapter.isEnabled()) {
                    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
                    if (pairedDevices.size() > 0) {
                        for (BluetoothDevice device : pairedDevices) {
                            mDevice = device;
                            uuids = mDevice.getUuids();
                            mSocket = mDevice.createRfcommSocketToServiceRecord(uuids[0].getUuid());
                            mSocket.connect();
                            mInStream=mSocket.getInputStream();
                            mConnectedThread = new ConnectedThread(mSocket);
                            mConnectedThread.start();

                        }

                    }}}
                    catch(IOException e){

                    }

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        NewActivity.class);
                Bundle bundle1=new Bundle();
                bundle1.putString("s",readMessage);
                myIntent.putExtras(bundle1);
                startActivity(myIntent);
                }

        });
    }
    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        //private final OutputStream mmOutStream;

        //creation of the connect thread
        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            //OutputStream tmpOut = null;

            try {
                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                //tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            //mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Keep looping to listen for received messages
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);            //read bytes from input buffer
                    readMessage = new String(buffer, 0, bytes);
                    // Send the obtained bytes to the UI Activity via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }


    }
}
