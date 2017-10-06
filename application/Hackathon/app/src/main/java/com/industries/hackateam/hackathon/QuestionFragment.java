package com.industries.hackateam.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.industries.hackateam.hackathon.modele.Cours;
import com.industries.hackateam.hackathon.modele.Question;
import com.industries.hackateam.hackathon.modele.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vincent on 04/10/2017.
 */

public class QuestionFragment extends Fragment{
    private static final String TAG = "QuestionFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.questions_fragment, container, false);
        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        final ListView listView = (ListView) view.findViewById(R.id.listView);

        // API settings
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.server_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiPerso service = retrofit.create(ApiPerso.class);


        Bundle bundle = this.getArguments();
        int idCoursInt = -1;
        if (bundle != null) {
            idCoursInt = bundle.getInt("idcours", -1);
        }
        Call<Question[]> listQuestion = service.getAllQuestionOfCours(idCoursInt);

        listQuestion.enqueue(new Callback<Question[]>() {
            @Override
            public void onResponse(Call<Question[]> call, Response<Question[]> response) {
                if (response.code() == 200) {
                    Log.i("ttt", "Res = "+response.body()[0].toString());

                    String[] values = getTitleFromQuestion(response);

                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(values)));
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent;

                            if(true/*isProf*/){
                                if(true/*close*/){
                                    //Si la personne est prof et question est fermée
                                    intent = new Intent(getContext(), QuestionValidateTeacherActivity.class);
                                } else {
                                    //Si la personne est prof et question est ouverte
                                    intent = new Intent(getContext(), QuestionActivity.class);
                                }
                            } else {
                                if(true/*close*/){
                                    //Si la personne est élève et question est fermée
                                    intent = new Intent(getContext(), QuestionValidateStudentActivity.class);
                                } else {
                                    //Si la personne est prof et question est ouverte
                                    intent = new Intent(getContext(), QuestionNoValidateStudentActivity.class);
                                }
                            }
                            intent.putExtra("question", listView.getItemAtPosition(position).toString());
                            startActivity(intent);
                        }
                    });


                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                            View popView = getActivity().getLayoutInflater().inflate(R.layout.pop_new_question, null);
                            final EditText question = (EditText) popView.findViewById(R.id.question);
                            Button submitButton = (Button) popView.findViewById(R.id.submit) ;


                            adb.setView(popView);
                            final AlertDialog dialog = adb.create();
                            dialog.show();

                            submitButton.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {
                                    if(question.length() <= 10){
                                        Toast.makeText(getActivity(),"Question non valide",Toast.LENGTH_SHORT).show();
                                    } else {

                                        adapter.setNotifyOnChange(true);
                                        adapter.add(question.getText().toString());
                                        Toast.makeText(getActivity(),"Question envoyée !",Toast.LENGTH_SHORT).show();
                                        dialog.hide();
                                    }
                                }
                            });
                        }
                    });





                } else if (response.code() == 204){
                    Toast.makeText(getContext(), "Identifiants invalides", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Un problème est survenu", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Question[]> call, Throwable t) {
                Log.i("ttt", "Fail = "+t.toString());

                Toast.makeText(getContext(), "Problème de serveur",Toast.LENGTH_LONG).show();
            }
        });



        return view;
    }

    public String[] getTitleFromQuestion(Response<Question[]> response)
    {
        String[] values = new String[response.body().length];
        int index = 0;
        for (Question q : response.body())
        {
            values[index] = q.Text;
            index++;
        }
        return values;
    }
}