package com.industries.hackateam.hackathon;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionNoValidateStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_no_validate_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        this.setTitle("Question Non Validée");

        TextView questionTexte = (TextView) this.findViewById(R.id.questionText);
        questionTexte.setText("Qui est Monsieur Maul ?");
        ListView listRes = (ListView) this.findViewById(R.id.listResponse);
        String[] listValue = new String[] {"Reponse etudiant 1", "Reponse etudiant 2"};

        listRes.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listValue));

        FloatingActionButton buttonAddReponse = (FloatingActionButton) findViewById(R.id.buttonAddReponse);
        buttonAddReponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder adb = new AlertDialog.Builder(QuestionNoValidateStudentActivity.this);
                View popView = getLayoutInflater().inflate(R.layout.pop_new_question, null);
                final EditText question = (EditText) popView.findViewById(R.id.question);
                Button submitButton = (Button) popView.findViewById(R.id.submit) ;


                adb.setView(popView);
                final AlertDialog dialog = adb.create();
                dialog.show();

                submitButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if(question.length() <= 10){
                            Toast.makeText(QuestionNoValidateStudentActivity.this,"Question non valide",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(QuestionNoValidateStudentActivity.this,"Question envoyée !",Toast.LENGTH_SHORT).show();
                            dialog.hide();
                        }
                    }
                });
            }
        });
    }

}
