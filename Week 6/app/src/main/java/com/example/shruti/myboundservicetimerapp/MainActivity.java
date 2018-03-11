package com.example.shruti.myboundservicetimerapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shruti.myboundservicetimerapp.BoundService.MyBinder;

public class MainActivity extends AppCompatActivity {
    Context context;
    Button startButton, stopButton, stopServiceButton, resetButton, startServiceButton;
    TextView timeTextView;

    long startTime;
    Handler handler;

    BoundService BoundService;
    boolean ServiceBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        startServiceButton = (Button) findViewById(R.id.startServiceButton);
        stopServiceButton = (Button) findViewById(R.id.stopServiceButton);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        resetButton = (Button) findViewById(R.id.resetButton);
        handler = new Handler();

        startButton.setEnabled(false);
        stopButton.setEnabled(false);
        resetButton.setEnabled(false);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ServiceBound) {
                    startTime = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    stopButton.setEnabled(true);
                    resetButton.setEnabled(true);
                    startButton.setEnabled(false);
                }
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
                timeTextView.setText("0:0:0:00");
                stopButton.setEnabled(false);
                resetButton.setEnabled(false);
            }
        });

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BoundService.class);
                startService(intent);
                bindService(intent, ServiceConnection, Context.BIND_AUTO_CREATE);
                startButton.setEnabled(true);
            }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
                if (ServiceBound) {
                    unbindService(ServiceConnection);
                    ServiceBound = false;
                }
                Intent intent = new Intent(context, BoundService.class);
                stopService(intent);

                startButton.setEnabled(false);
                stopButton.setEnabled(false);
                resetButton.setEnabled(false);
            }
        });

    }

    public Runnable runnable=new Runnable() {

        public void run() {
            timeTextView.setText(BoundService.getTimestamp(startTime));
            handler.postDelayed(this,0);
        }
    };

    private ServiceConnection ServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            MyBinder myBinder = (MyBinder) service;
            BoundService = myBinder.getService();
            ServiceBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            ServiceBound = false;

        }
    };

    protected void onStop(){
        super.onStop();
        if(ServiceBound){
            unbindService(ServiceConnection);
            ServiceBound = false;
        }
    }


}
