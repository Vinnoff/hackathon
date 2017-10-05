package com.industries.hackateam.hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by zeke on 04/10/2017.
 */

public class AdapterQuestion extends BaseAdapter
{
    Context context;
    String[] values;
    private static LayoutInflater inflater = null;

    public AdapterQuestion(Context context, String[] listValue)
    {
        this.context = context;
        this.values = listValue;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return values[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.response_cell, null);
        TextView text = (TextView) vi.findViewById(R.id.text);
        text.setText(values[position]);
        return vi;
    }

}
