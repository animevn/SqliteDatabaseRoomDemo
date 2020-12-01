package com.haanhgs.sqlitedatabaseroomdemo.model;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.haanhgs.sqlitedatabaseroomdemo.model.tables.Job;
import com.haanhgs.sqlitedatabaseroomdemo.model.tables.Person;
import com.haanhgs.sqlitedatabaseroomdemo.model.tables.PersonWithJob;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import androidx.lifecycle.LiveData;
import static java.util.concurrent.TimeUnit.SECONDS;

public class RoomRepo {

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, 8, 60, SECONDS,
            new LinkedBlockingQueue<>()
    );

    private final PersonDao personDao;
    private final LiveData<List<PersonWithJob>> allPerson;
    private final LiveData<List<Job>> allJob;

    public LiveData<List<PersonWithJob>> getAllPerson() {
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

    public void insertPerson(Person person){
        executor.execute(() -> personDao.insertPerson(person));
    }

    public void insertJob(Job job){
        executor.execute(() -> personDao.insertJob(job));
    }

    public void deletePerson(Person person){
        executor.execute(() -> personDao.deletePerson(person));
    }

    public void deletePerson(int personId){
        executor.execute(() -> personDao.deletePerson(personId));
    }

    public void deletePersonByName(String string){
        executor.execute(() -> personDao.deletePersonByName(string));
    }

    public void deleteJob(Job job){
        executor.execute(() -> personDao.deleteJob(job));
    }

    public void updatePerson(Person person){
        executor.execute(() -> personDao.update(person));
    }

    public void updateJob(Job job){
        executor.execute(() -> personDao.update(job));
    }

    public LiveData<Job> getJobById(int jobId){
        return  personDao.getJobById(jobId);
    }

    public LiveData<Person> getPersonById(int personId){
        return personDao.getPersonById(personId);
    }

    public LiveData<List<PersonWithJob>> getPersonByName(String name){
        return personDao.getPersonByName(name);
    }

    public static void hideSoftKey(Context context, View view){
        InputMethodManager manager =
                (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null){
            manager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
