package com.example.assignment2_musicplayer.first;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment2_musicplayer.R;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MySimpleArrayAdapter(Context context, String[] values) {
        super(context, R.layout.listview_layout, values);
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.listview_layout, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.musicName);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.picture);

        textView.setText(values[position]);

        String s = values[position];
        imageView.setImageResource(R.drawable.musicalnote);

        return rowView;
    }
}
