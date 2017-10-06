package com.industries.hackateam.hackathon.objects;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vincent on 05/10/2017.
 */

public class Person extends RealmObject {
    @PrimaryKey
    private long id;
    private String lastname;
    private String firstname;
    private String mail;
    private boolean isProf;

    public Person() {
    }

    public Person(Long id, String lastname, String firstname, String mail, boolean isProf) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.mail = mail;
        this.isProf = isProf;
    }

    public long getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMail() {
        return mail;
    }

    public boolean isProf() {
        return isProf;
    }
}
