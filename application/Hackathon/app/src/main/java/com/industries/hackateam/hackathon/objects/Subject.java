package com.industries.hackateam.hackathon.objects;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vincent on 05/10/2017.
 */

public class Subject extends RealmObject {
    @PrimaryKey
    private long id;
    private String date;
    private String promotion;
    private String subjectName;
    private RealmList<Person> persons;
}
