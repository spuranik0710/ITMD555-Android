package com.example.shruti.week7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by shruti on 4/3/2018.
 */

public class spin extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    Set<String> set;
    SqlHelper db;
    boolean blnFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);
        Intent intent = getIntent();
        db = new SqlHelper(this);

        db.addBook(new Book("Professional Android 4 Application Development","Reto Meier","1.0"));
        db.addBook(new Book("Beginning Android 4 Application Development","Wei-Meng Lee","3.5"));
        db.addBook(new Book("Programming Android", "Wallace Jackson","2.5"));
        db.addBook(new Book("Hello, Android", "Wallace Jackson","4.0"));

        Spinner spinner;

        spinner = (Spinner) findViewById(R.id.spinner1);
        set = db.getTitle();
        List<String> tlist = new ArrayList<String>(set);
        Collections.sort(tlist, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });
        tlist.add(0,"Select title...");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setWillNotDraw(false);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        if(blnFlag){
            if(arg2>0){
                String title = arg0.getItemAtPosition(arg2).toString();
                Toast.makeText(this," Author's Name :: " + db.getAuthor(title),Toast.LENGTH_LONG).show();
            }
        }
        blnFlag = true;
    }
}
