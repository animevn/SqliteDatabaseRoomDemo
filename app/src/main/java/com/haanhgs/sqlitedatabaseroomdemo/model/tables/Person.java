package com.haanhgs.sqlitedatabaseroomdemo.model.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_table")
public class Person {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "person_id")
    private int personId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "joib_id")
    private int jobId;

    @ColumnInfo(name = "age")
    private int age;

    public Person(int personId, String name, int jobId, int age) {
        this.personId = personId;
        this.name = name;
        this.age = age;
        this.jobId = jobId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
