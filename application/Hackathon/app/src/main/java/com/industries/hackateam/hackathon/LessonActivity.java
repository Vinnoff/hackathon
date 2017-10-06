package com.industries.hackateam.hackathon;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.industries.hackateam.hackathon.modele.Cours;
import com.industries.hackateam.hackathon.modele.Matiere;
import com.industries.hackateam.hackathon.modele.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        Intent intent = getIntent();
        this.setTitle(intent.getStringExtra("title"));
        final String dayTitleOne = intent.getStringExtra("title") + " - ";
        final ListView listView = (ListView) findViewById(R.id.listView);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        if (false/*isPasProf*/) {
            fab.hide();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.server_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiPerso service = retrofit.create(ApiPerso.class);


        int idMatiere;
        try {
            idMatiere = Integer.parseInt(intent.getStringExtra("idmatiere"));
        } catch (NumberFormatException e) {
            idMatiere = -1;
        }


        Call<Cours[]> listCours = service.getAllCoursOfMatiere(idMatiere);

        listCours.enqueue(new Callback<Cours[]>() {
            @Override
            public void onResponse(Call<Cours[]> call, Response<Cours[]> response) {
                if (response.code() == 200) {
                    Log.i("ttt", "Res = " + response.body()[0].toString());

                    //String[] values = new String[] { "17/04/12" , "18/04/12" , "19/04/12" , "20/04/12" , "21/04/12" };

                    String[] values = getTitleFromCours(response);
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


                            final String date = new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime());
                            boolean exist = false;
                            for (int i = 0; i < adapter.getCount(); i++) {
                                if (adapter.getItem(i).toString().equals(date)) {
                                    exist = true;
                                    break;
                                }
                            }
                            if (!exist) {

                                Toast.makeText(LessonActivity.this, "Cours ajouté", Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder adb = new AlertDialog.Builder(LessonActivity.this);
                                View popView = getLayoutInflater().inflate(R.layout.pop_new_lesson, null);
                                final EditText question = (EditText) popView.findViewById(R.id.question);
                                Button submitButton = (Button) popView.findViewById(R.id.submit);
                                adb.setView(popView);
                                final AlertDialog dialog = adb.create();

                                dialog.show();

                                submitButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (question.length() <= 10) {
                                            Toast.makeText(LessonActivity.this, "Notions non valides", Toast.LENGTH_SHORT).show();
                                        } else {
                                            adapter.setNotifyOnChange(true);
                                            adapter.add(date);
                                            Toast.makeText(LessonActivity.this, "Question envoyée !", Toast.LENGTH_SHORT).show();
                                            dialog.hide();
                                            //TODO : POST new lesson
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(LessonActivity.this, "Le cours existe déjà !", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    //startActivity(new Intent(LessonActivity.this, DayLessonActivity.class));

                } else if (response.code() == 204) {

                }


            }

            @Override
            public void onFailure(Call<Cours[]> call, Throwable t) {
                Log.i("ttt", "Fail = " + t.toString());
                Toast.makeText(LessonActivity.this, "Problème de serveur", Toast.LENGTH_LONG).show();
            }

        });


    }

    public String[] getTitleFromCours(Response<Cours[]> response) {
        String[] values = new String[response.body().length];
        int index = 0;
        for (Cours c : response.body()) {
            values[index] = c.Titre;
            index++;
        }
        return values;
    }


}
