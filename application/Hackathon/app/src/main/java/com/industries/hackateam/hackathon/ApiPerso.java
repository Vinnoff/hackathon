package com.industries.hackateam.hackathon;

import com.industries.hackateam.hackathon.modele.Cours;
import com.industries.hackateam.hackathon.modele.Matiere;
import com.industries.hackateam.hackathon.modele.Question;
import com.industries.hackateam.hackathon.modele.Reponse;
import com.industries.hackateam.hackathon.modele.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by madmax on 05/10/2017.
 */

public interface ApiPerso {
    public static final String ENDPOINT = "@string/server_url";

    @POST("/users/exist")
    Call<User[]> getUser(@Body Map user);

    @GET("/users/matieres/{id}")
    Call<Matiere[]> getMatiereOfUser(@Path("id") int id);

    @POST("/matieres")
    Call<Matiere[]> createMatiere(@Body Map mat);

    @GET("/matieres/getAllCours/{id}")
    Call<Cours[]> getAllCoursOfMatiere(@Path("id") int id);

    @POST("/cours")
    Call<Cours[]> createCours(@Body Map cours);

    @GET("/cours/allFromCours/{id}")
    Call<Question[]> getAllQuestionOfCours(@Path("id") int id);

    @DELETE("/cours/{id}")
    Call<Cours[]> deleteCours(@Path("id") int id);

    @POST("/question")
    Call<Question[]> createQuestion(@Body Map question);

    @DELETE("/question/{id}")
    Call<Question[]> deleteQuestion(@Path("id") int id);

    @GET("/question/allRes/{id}")
    Call<Reponse[]>  getReponseOfQuestion(@Path("id") int id);

    @POST("/reponse")
    Call<Question[]> createReponse(@Body Map reponse);

    @PUT("/reponse/validate/{id}")
    Call<Question[]> validateReponse(@Path("id") int id);

    @DELETE("/reponse/del/{id}")
    Call<Question[]> deleteReponse(@Path("id") int id);

    @GET("/cours/{id}")
    Call<Cours[]> getCoursById(@Path("id") int id);

}
