package com.haanhgs.sqlitedatabaseroomdemo.model;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class Model extends AndroidViewModel {

    private RoomRepo repo;
    private LiveData<List<Person>> allPerson;
    private LiveData<List<Job>> allJob;


    public Model(@NonNull Application application) {
        super(application);
        repo = new RoomRepo(application);
        allJob = repo.getAllJob();
        allPerson = repo.getAllPerson();
    }

    public LiveData<List<Person>> getAllPerson() {
        return allPerson;
    }

    public LiveData<List<Job>> getAllJob() {
        return allJob;
    }

    public void insertPerson(Person person){
        repo.insertPerson(person);
    }

    public void insertJob(Job job){
        repo.insertJob(job);
    }

    public void deletePerson(Person person){
        repo.deletePerson(person);
    }

    public void deleteJob(Job job){
        repo.deleteJob(job);
    }

    public void updatePerson(Person person){
        repo.updatePerson(person);
    }

    public void updateJob(Job job){
        repo.updateJob(job);
    }


}
