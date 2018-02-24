package com.example.shruti.slideshow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public SeekBar seekBar;
    Button Click;
    public TextView progresscomp;
    int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Click = (Button)findViewById(R.id.button1);
        seekBar = (SeekBar)findViewById(R.id.seekbar);
        progresscomp = (TextView)findViewById(R.id.progresstext);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress=progressValue;
                progresscomp.setText(progress + "/" + (seekBar.getMax()) + "Seconds");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                progresscomp.setText(progress + "/" + (seekBar.getMax()) + "Seconds");
            }
        });
        Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), My2ndActivity.class);
                intent.putExtra("Progress",progress);
                startActivity(intent);
            }
        });

    }
}
