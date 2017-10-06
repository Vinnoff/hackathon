package com.industries.hackateam.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.industries.hackateam.hackathon.modele.Matiere;
import com.industries.hackateam.hackathon.modele.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubjectsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Matières");
        setContentView(R.layout.activity_subjects2);

        final ListView listView = (ListView) findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.server_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiPerso service = retrofit.create(ApiPerso.class);

        //TO FIX : change idPerson
        Call<Matiere[]> req = service.getMatiereOfUser(1);

        req.enqueue(new Callback<Matiere[]>() {
            @Override
            public void onResponse(Call<Matiere[]> call, final Response<Matiere[]> response) {
                if (response.code() == 200) {
                    Log.i("ttt", "Res = "+response.body()[0].toString());


                    String[] values = getTitleFromMatiere(response);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SubjectsActivity.this, android.R.layout.simple_list_item_1, values);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(SubjectsActivity.this, LessonActivity.class);
                            intent.putExtra("title",(listView.getItemAtPosition(position).toString()));
                            Matiere m = response.body()[position];
                            intent.putExtra("idmatiere", m.idMatiere.toString());
                            startActivity(intent);
                        }
                    });


                } else if (response.code() == 204){
                    //showProgress(false);
                    Toast.makeText(SubjectsActivity.this, "Aucunes matières affiliées", Toast.LENGTH_LONG).show();
                } else {
                    //showProgress(false);
                    Toast.makeText(SubjectsActivity.this, "Un problème est survenu" + response.code(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Matiere[]> call, Throwable t) {
                Log.i("ttt", "Fail = "+t.toString());
                //showProgress(false);
                Toast.makeText(SubjectsActivity.this, "Problème de serveur",Toast.LENGTH_LONG).show();
            }
        });






    }

    public String[] getTitleFromMatiere(Response<Matiere[]> response)
    {
        String[] values = new String[response.body().length];
        int index = 0;
        for ( Matiere m : response.body())
        {
            values[index] = m.Labelle;
            index++;
        }
        return values;
    }
}
