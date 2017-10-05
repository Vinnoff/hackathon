package com.industries.hackateam.hackathon.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.industries.hackateam.hackathon.R;

/**
 * Created by zeke on 05/10/2017.
 */

public class AdapterValidateTeacher extends BaseAdapter
{
    Context context;
    String[] values;
    private static LayoutInflater inflater = null;


    public AdapterValidateTeacher(Context context, String[] listValue)
    {
        this.context = context;
        this.values = listValue;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return values[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        if (vi == null)
            vi = inflater.inflate(R.layout.response_cell_author, null);
        TextView text = (TextView) vi.findViewById(R.id.responseText);
        text.setText(values[i]);
        if (i == 0)
            text.setBackgroundColor(Color.RED);
        return vi;
    }
}
