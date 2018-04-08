package com.example.shruti.week7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shruti on 4/3/2018.
 */

public class customlistadapter extends ArrayAdapter<Book> {

    private List<Book>items;

    public customlistadapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public customlistadapter(Context context, int resource, List<Book> items) {
        super(context, resource, items);
        this.items = items;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.customlist,null);
        }
        Book p = getItem(position);

        if(p != null){
            TextView ti = (TextView) v.findViewById(R.id._id);
            TextView tt = (TextView) v.findViewById(R.id.title);
            TextView ta = (TextView) v.findViewById(R.id.author);
            RatingBar rb = (RatingBar) v.findViewById(R.id.rating);

            if(ti != null){
                ti.setText("" + p.getId());
            }
            if(tt != null){
                tt.setText(p.getTitle());
            }
            if(ta != null){
                ta.setText(p.getAuthor());
            }
            if(rb != null){
                float rating = Float.parseFloat(p.getRating());
                rb.setRating(rating);
            }
        }
        return v;
    }
}
