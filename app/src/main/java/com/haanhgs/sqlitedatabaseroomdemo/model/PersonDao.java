package com.haanhgs.sqlitedatabaseroomdemo.model;

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

    @Delete
    void deletePerson(Person person);

    @Query("delete from person_table")
    void deleteAllPerson();

    @Query("delete from person_table where name = :name")
    void deletePersonByName(String name);

    @Update
    void update(Person person);

    @Query("select * from person_table where person_id = :personId limit 1")
    LiveData<Person> findPersonById(int personId);

    @Query("select * from person_table order by person_id asc")
    LiveData<List<Person>> getAllPerson();

    @Query("select * from person_table limit 1")
    Person[] getAnyPerson();

    @Query("select * from person_table where name like :name order by name")
    LiveData<List<Person>> findPersonByName(String name);

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
    LiveData<Job> findJobById(int jobId);


    @Query("select * from job_table order by job_id asc")
    LiveData<List<Job>> getAllJob();

}
