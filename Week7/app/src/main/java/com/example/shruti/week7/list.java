package com.example.shruti.week7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shruti on 4/3/2018.
 */

public class list extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        Intent intent1 = getIntent();
        SqlHelper db = new SqlHelper(this);

        List<Book> list = db.getAllBooks();
        ListView listContent = (ListView) findViewById(R.id.list);
        List<Book> books = new ArrayList<Book>();
        books=db.getAllBooks();

        ListAdapter customAdapter = new customlistadapter(this,R.layout.customlist, books);
        listContent.setAdapter(customAdapter);

        db.getAllBooks();
    }
}
