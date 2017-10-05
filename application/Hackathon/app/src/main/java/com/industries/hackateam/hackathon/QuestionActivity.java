package com.industries.hackateam.hackathon;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBarLayout);
        setSupportActionBar(toolbar);

        this.setTitle("Question");
        TextView questionText = (TextView) this.findViewById(R.id.questionText);
        questionText.setText("Question: Qui est Obi-Wan");
        ListView listResponse = (ListView) this.findViewById(R.id.listResponse);
        String[] listValue = new String[] { "R1", "R2", "R3" };

        listResponse.setAdapter(new AdapterQuestion(this, listValue));


    }

}
