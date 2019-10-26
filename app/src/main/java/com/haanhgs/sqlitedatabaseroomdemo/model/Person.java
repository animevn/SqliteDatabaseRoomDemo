package com.haanhgs.sqlitedatabaseroomdemo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_table")
public class Person {

    @PrimaryKey(autoGenerate = true)
    private int PersonId;

    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    @ColumnInfo(name = "job")
    private Job job;

    @ColumnInfo(name = "age")
    private int age;

    public Person(int id, @NonNull String name, Job job, int age) {
        PersonId = id;
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
