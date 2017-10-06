package com.industries.hackateam.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.industries.hackateam.hackathon.modele.Cours;
import com.industries.hackateam.hackathon.modele.User;

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

public class DescriptionFragment extends Fragment{
    private static final String TAG = "QuestionFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description_fragment, container, false);
        final TextView textDescription = (TextView) view.findViewById(R.id.descriptionText);
        Bundle bundle = this.getArguments();
        int idCoursInt = -1;
        if (bundle != null) {
            idCoursInt = bundle.getInt("idcours", -1);
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.server_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiPerso service = retrofit.create(ApiPerso.class);


        final Call<Cours[]> cours = service.getCoursById(idCoursInt);

        cours.enqueue(new Callback<Cours[]>() {
            @Override
            public void onResponse(Call<Cours[]> call, Response<Cours[]> response) {
                if (response.code() == 200) {
                    textDescription.setText(response.body()[0].Description);

                } else if (response.code() == 204){
                    Toast.makeText(getContext(), "Identifiants invalides", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Un problème est survenu", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Cours[]> call, Throwable t) {
                Log.i("ttt", "Fail = "+t.toString());
                Toast.makeText(getContext(), "Problème de serveur",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
