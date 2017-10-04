package com.industries.hackateam.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DayLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        this.setTitle(intent.getStringExtra("title"));
        if(/*isProf*/true){
            setContentView(R.layout.activity_day_lesson_professor);
        } else {
            setContentView(R.layout.activity_day_lesson_student);
        }
    }
}
