package com.industries.hackateam.hackathon.modele;

/**
 * Created by madmax on 06/10/2017.
 */

public class Matiere {
    public String idMatiere;
    public String Date;
    public String Promo;
    public String Labelle;

    @Override
    public String toString() {
        return "Matiere{" +
                "idMatiere='" + idMatiere + '\'' +
                ", Date='" + Date + '\'' +
                ", Promo='" + Promo + '\'' +
                ", Labelle='" + Labelle + '\'' +
                '}';
    }
}
