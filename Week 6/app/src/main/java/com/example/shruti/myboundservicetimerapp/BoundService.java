package com.example.shruti.myboundservicetimerapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Binder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.Toast;

/**
 * Created by shruti on 3/8/2018.
 */

public class BoundService extends Service {
    private static String LOG_TAG = "BoundService";
    private IBinder Binder = new MyBinder();
    private Chronometer Chronometer;

    public class MyBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
    public String getTimestamp(long startTime) {
        long elapsedMillis = SystemClock.elapsedRealtime() - startTime;
        int hours = (int) (elapsedMillis / 3600000);
        int minutes = (int) (elapsedMillis - hours * 3600000) / 60000;
        int seconds = (int) (elapsedMillis - hours * 3600000 - minutes * 60000) / 1000;
        int millis = (int) (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000);
        return hours + ":" + minutes + ":" + seconds + ":" + millis;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(LOG_TAG, "in onCreate");
        Chronometer = new Chronometer(this);
        Chronometer.setBase(SystemClock.elapsedRealtime());
        Chronometer.start();
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(LOG_TAG, "in onBind");
        return Binder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v(LOG_TAG, "in onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(LOG_TAG, "in onUnbind");
        return true;
    }

    @Override     public void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG, "in onDestroy");
        Toast.makeText(this,"Service Stopped",Toast.LENGTH_SHORT).show();
        Chronometer.stop();
    }

    public void stopChronometer(){
        Chronometer.stop();
    }

}
