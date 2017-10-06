package com.industries.hackateam.hackathon.modele;

/**
 * Created by madmax on 06/10/2017.
 */

public class Reponse {
    public String idReponse;
    public String Question_idQuestion;
    public String Personne_idPersonne;
    public String Text;
    public String Status;

    @Override
    public String toString() {
        return "Reponse{" +
                "idReponse='" + idReponse + '\'' +
                ", Question_idQuestion='" + Question_idQuestion + '\'' +
                ", Personne_idPersonne='" + Personne_idPersonne + '\'' +
                ", Text='" + Text + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
