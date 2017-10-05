package com.industries.hackateam.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LessonActivity.this, QuestionActivity.class);
                intent.putExtra("title",(listView.getItemAtPosition(position).toString()));
                startActivity(intent);
            }
        });
    }
}
