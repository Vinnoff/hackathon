package com.industries.hackateam.hackathon.objects;

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
    private String password;
    private boolean isProf;
    private RealmList<Subject> subjects;
}
