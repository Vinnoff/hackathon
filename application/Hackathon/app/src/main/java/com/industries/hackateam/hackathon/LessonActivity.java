package com.industries.hackateam.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LessonActivity extends AppCompatActivity {
    public String title = "Subject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        Intent intent = getIntent();
        this.setTitle(intent.getStringExtra("title"));
        final ListView listView = (ListView) findViewById(R.id.listView);
        String[] values = new String[] { "Cours 1" };


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(LessonActivity.this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
    }
}
