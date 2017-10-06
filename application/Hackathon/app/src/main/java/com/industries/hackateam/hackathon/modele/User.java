package com.industries.hackateam.hackathon.modele;

/**
 * Created by madmax on 06/10/2017.
 */

public class User {

    public int idPersonne;
    public String Prenom;
    public String Nom;
    public String Mail;
    public String Mdp;
    public String IsTeacher;

    @Override
    public String toString() {
        return "User{" +
                "idPersonne=" + idPersonne +
                ", Prenom='" + Prenom + '\'' +
                ", Nom='" + Nom + '\'' +
                ", Mail='" + Mail + '\'' +
                ", Mdp='" + Mdp + '\'' +
                ", IsTeacher='" + IsTeacher + '\'' +
                '}';
    }
}
