package com.haanhgs.sqlitedatabaseroomdemo.model;

import android.app.Application;

import com.haanhgs.sqlitedatabaseroomdemo.model.tables.Job;
import com.haanhgs.sqlitedatabaseroomdemo.model.tables.Person;
import com.haanhgs.sqlitedatabaseroomdemo.model.tables.PersonWithJob;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class Model extends AndroidViewModel {

    private final RoomRepo repo;
    private final LiveData<List<PersonWithJob>> allPerson;
    private final LiveData<List<Job>> allJob;



    public Model(@NonNull Application application) {
        super(application);
        repo = new RoomRepo(application);
        allJob = repo.getAllJob();
        allPerson = repo.getAllPerson();
    }

    public LiveData<List<PersonWithJob>> getAllPerson() {
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

    public void deletePerson(int personId){
        repo.deletePerson(personId);
    }

    public void deletePersonByName(String string){
        repo.deletePersonByName(string);
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

    public LiveData<Job> findJobById(int jobId){
        return repo.findJobById(jobId);
    }

    public LiveData<Person> findPersonById(int personId){
        return repo.findPersonById(personId);
    }

    public LiveData<List<Person>> findPersonByName(String name){
        return repo.findPersonByName(name);
    }

}
