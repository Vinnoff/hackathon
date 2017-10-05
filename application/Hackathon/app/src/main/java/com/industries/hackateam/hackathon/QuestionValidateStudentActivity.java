package com.industries.hackateam.hackathon;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.industries.hackateam.hackathon.adapter.AdapterValidateStudent;

public class QuestionValidateStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_validate_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        this.setTitle("Question Validée Student");
        TextView questionText = (TextView) this.findViewById(R.id.questionText);
        questionText.setText("Qui est Vador ?");
        ListView listResponse = (ListView) this.findViewById(R.id.listResponse);
        String[] listValue = new String[] {"Response num 1", "Response num 2", "Response num 3"};

        AdapterValidateStudent adapter = new AdapterValidateStudent(this, listValue);

        listResponse.setAdapter(adapter);

    }



}
