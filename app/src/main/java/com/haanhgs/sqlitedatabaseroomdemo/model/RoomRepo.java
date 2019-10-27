package com.haanhgs.sqlitedatabaseroomdemo.model;

import android.app.Application;
import android.os.AsyncTask;
import java.util.List;
import androidx.lifecycle.LiveData;

public class RoomRepo {

    private PersonDao personDao;
    private LiveData<List<Person>> allPerson;
    private LiveData<List<Job>> allJob;

    public LiveData<List<Person>> getAllPerson() {
        return allPerson;
    }

    public LiveData<List<Job>> getAllJob() {
        return allJob;
    }

    public RoomRepo(Application application){
        RoomDB roomDB = RoomDB.init(application);
        personDao = roomDB.personDao();
        allJob = personDao.getAllJob();
        allPerson = personDao.getAllPerson();
    }

    private static class InsertPersonAsync extends AsyncTask<Person, Void, Void> {
        private PersonDao dao;

        public InsertPersonAsync(PersonDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Person... persons) {
            dao.insertPerson(persons[0]);
            return null;
        }
    }

    public void insertPerson(Person person){
        new InsertPersonAsync(personDao).execute(person);
    }

    private static class InsertJobAsync extends AsyncTask<Job, Void, Void> {
        private PersonDao dao;

        public InsertJobAsync(PersonDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Job... jobs) {
            dao.insertJob(jobs[0]);
            return null;
        }
    }

    public void insertJob(Job job){
        new InsertJobAsync(personDao).execute(job);
    }

    private static class DeletePersonAsync extends AsyncTask<Person, Void, Void> {
        private PersonDao dao;

        public DeletePersonAsync(PersonDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Person... persons) {
            dao.deletePerson(persons[0]);
            return null;
        }
    }

    public void deletePerson(Person person){
        new DeletePersonAsync(personDao).execute(person);
    }

    private static class DeleteJobAsync extends AsyncTask<Job, Void, Void> {
        private PersonDao dao;

        public DeleteJobAsync(PersonDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Job... jobs) {
            dao.deleteJob(jobs[0]);
            return null;
        }
    }

    public void deleteJob(Job job){
        new DeleteJobAsync(personDao).execute(job);
    }

    private static class UpdatePersonAsync extends AsyncTask<Person, Void, Void> {
        private PersonDao dao;

        public UpdatePersonAsync(PersonDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Person... persons) {
            dao.update(persons[0]);
            return null;
        }
    }

    public void updatePerson(Person person){
        new UpdatePersonAsync(personDao).execute(person);
    }

    private static class UpdateJobAsync extends AsyncTask<Job, Void, Void> {
        private PersonDao dao;

        public UpdateJobAsync(PersonDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Job... jobs) {
            dao.update(jobs[0]);
            return null;
        }
    }

    public void updateJob(Job job){
        new UpdateJobAsync(personDao).execute(job);
    }



}
