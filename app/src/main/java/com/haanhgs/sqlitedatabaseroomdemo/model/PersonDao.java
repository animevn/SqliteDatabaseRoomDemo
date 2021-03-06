package com.haanhgs.sqlitedatabaseroomdemo.model;

import com.haanhgs.sqlitedatabaseroomdemo.model.tables.Job;
import com.haanhgs.sqlitedatabaseroomdemo.model.tables.Person;
import com.haanhgs.sqlitedatabaseroomdemo.model.tables.PersonWithJob;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PersonDao {

    @Insert
    void insertPerson(Person person);

    @Update
    void update(Person person);

    @Query("delete from person_table")
    void deleteAllPerson();

    @Query("delete from person_table where name = :name")
    void deletePersonByName(String name);

    @Query("select * from person_table where person_id = :personId")
    LiveData<Person> getPersonById(int personId);

    @Query("select person_table.person_id, person_table.name, person_table.age, " +
            "job_table.job_id, job_table.job_name from person_table left join job_table " +
            "on person_table.job_id = job_table.job_id")
    LiveData<List<PersonWithJob>> getAllPerson();

    @Delete
    void deletePerson(Person person);

    @Query("delete from person_table where person_id = :personId")
    void deletePerson(int personId);

    @Query("select * from person_table limit 1")
    Person[] getAnyPerson();

    @Query("select person_table.person_id, person_table.name, person_table.age, " +
            "job_table.job_id, job_table.job_name from person_table left join job_table " +
            "on person_table.job_id = job_table.job_id where name = :name")
    LiveData<List<PersonWithJob>> getPersonByName(String name);

    @Insert
    void insertJob(Job job);

    @Update
    void update(Job job);

    @Delete
    void deleteJob(Job job);

    @Query("delete from job_table")
    void deleteAllJob();

    @Query("select * from job_table limit 1")
    Job[] getAnyJob();

    @Query("select * from job_table where job_id = :jobId limit 1")
    LiveData<Job> getJobById(int jobId);

    @Query("select * from job_table order by job_id asc")
    LiveData<List<Job>> getAllJob();

}
