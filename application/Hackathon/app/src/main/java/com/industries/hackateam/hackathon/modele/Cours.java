package com.industries.hackateam.hackathon.modele;

/**
 * Created by madmax on 06/10/2017.
 */

public class Cours {
    public String idCours;
    public String Matiere_idMatiere;
    public String Description;
    public String Titre;
    public String Date;

    @Override
    public String toString() {
        return "Cours{" +
                "idCours='" + idCours + '\'' +
                ", Matiere_idMatiere='" + Matiere_idMatiere + '\'' +
                ", Description='" + Description + '\'' +
                ", Titre='" + Titre + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
