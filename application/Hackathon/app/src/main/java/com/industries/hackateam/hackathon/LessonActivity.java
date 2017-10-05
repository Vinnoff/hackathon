package com.industries.hackateam.hackathon;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class LessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        Intent intent = getIntent();
        this.setTitle(intent.getStringExtra("title"));
        final String dayTitleOne = intent.getStringExtra("title") + " - ";
        final ListView listView = (ListView) findViewById(R.id.listView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(false/*isPasProf*/){
            fab.hide();
        }
        String[] values = new String[] { "17/04/12" , "18/04/12" , "19/04/12" , "20/04/12" , "21/04/12" };


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(LessonActivity.this, android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(values)));
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LessonActivity.this, DayLessonActivity.class);
                String dayTitleSecond = listView.getItemAtPosition(position).toString();
                String dayTitle = dayTitleOne + dayTitleSecond;
                intent.putExtra("title", dayTitle);
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : post Nouveau cours
                String date = new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime());
                boolean exist = false;
                for(int i = 0; i<adapter.getCount();i++){
                    if(adapter.getItem(i).toString().equals(date)){
                        exist = true;
                        break;
                    }
                }
                if (!exist){
                    adapter.setNotifyOnChange(true);
                    adapter.add(date);
                    Toast.makeText(LessonActivity.this, "Cours ajouté", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LessonActivity.this, "Le cours existe déjà !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
