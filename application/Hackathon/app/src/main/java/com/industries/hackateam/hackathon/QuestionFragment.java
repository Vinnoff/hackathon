package com.industries.hackateam.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by vincent on 04/10/2017.
 */

public class QuestionFragment extends Fragment{
    private static final String TAG = "QuestionFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.questions_fragment, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        ListView listView = (ListView) view.findViewById(R.id.listView);

        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, values);
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
                    Toast.makeText(getActivity(),"Question envoyée !",Toast.LENGTH_SHORT).show();
                    dialog.hide();
                }
                }
            });
            }
        });

        return view;
    }
}
