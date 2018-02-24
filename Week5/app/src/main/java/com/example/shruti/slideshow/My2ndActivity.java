package com.example.shruti.slideshow;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shruti on 2/24/2018.
 */

public class My2ndActivity extends AppCompatActivity {

    private Bundle extras;
    private long timer;
    private int time;

    private ImageView imageView;
    private TextView head;
    private TextView desc;
    private Handler customHandler = new Handler();

    Integer[] images = new Integer[]{
            R.drawable.boston, R.drawable.chicago,R.drawable.california, R.drawable.delhi, R.drawable.london,
            R.drawable.mumbai,R.drawable.paris, R.drawable.singapore, R.drawable.thailand, R.drawable.toronto
    };
    String[] description = new String[]{
            "Planning to visit this summer", "Resident of this beautiful city","City of IT hub" ,"Capital of India famous for shopping", "Planning to visit in October",
            "City of Indian Chats", "French Food and WINE!", "Been to this city in 2014", "Beach Resort, Pattaya <3", "Wanna visit to view Niagra falls"
    };
    String[] heading = new String[]{
            "Boston", "Chicago","California" ,"Delhi", "London", "Mumbai",
            "Paris", "Singapore", "Thailand", "Toronto"
    };

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2nd);
        try {
            extras = getIntent().getExtras();
            time = extras.getInt("progress");

            imageView = (ImageView) findViewById(R.id.image);
            head = (TextView) findViewById(R.id.heading);
            desc = (TextView) findViewById(R.id.description);
            customHandler.postDelayed(runnable, time * 1000);
        } catch (Exception ex) {
            Log.d("EX0001", ex.getMessage());
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (i < images.length) {
                imageView.setImageResource(images[i]);
                head.setText(heading[i]);
                desc.setText(description[i]);
                i++;
            }
            if (i == images.length) {
                i = 0;
            }
            customHandler.postDelayed(this, time * 1000);
        }
    };
    @Override
    protected  void onResume(){
        super.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }


}
