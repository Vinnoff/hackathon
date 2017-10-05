package com.industries.hackateam.hackathon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.industries.hackateam.hackathon.adapter.AdapterValidateTeacher;

public class QuestionValidateTeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_validate_teacher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        this.setTitle("Question Validée");
        TextView questionText = (TextView) this.findViewById(R.id.responseText);
        questionText.setText("Qui est Toto ?");
        ListView listResponse = (ListView) this.findViewById(R.id.listRespond);
        String[] listValue = new String[] { "Reponse1", "Reponse2", "Reponse3" };

        listResponse.setAdapter(new AdapterValidateTeacher(this, listValue));


    }

}
