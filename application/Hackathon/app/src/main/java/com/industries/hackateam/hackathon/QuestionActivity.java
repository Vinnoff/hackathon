package com.industries.hackateam.hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.industries.hackateam.hackathon.adapter.AdapterValidateTeacher;

//Question Activity for the Teacher
public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBarLayout);
        setSupportActionBar(toolbar);

        this.setTitle("Question");
        TextView questionText = (TextView) this.findViewById(R.id.responseText);
        questionText.setText("Question: Qui est Obi-Wan");
        ListView listResponse = (ListView) this.findViewById(R.id.listResponse);
        String[] listValue = new String[] { "R1", "R2", "R3" };

        listResponse.setAdapter(new AdapterQuestion(this, listValue));


    }
}
