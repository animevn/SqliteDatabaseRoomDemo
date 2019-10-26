package com.haanhgs.sqlitedatabaseroomdemo.model;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PersonDao {

    @Insert
    void insertPerson(Person person);

    @Delete
    void deletePerson(Person person);

    @Query("delete from person_table")
    void deleteAllPerson();

    @Query("update person_table set name = :name, job = :job, age = :age where PersonId = " +
            ":personId")
    void update(@NonNull String name, Job job, int age, int personId);

    @Query("select * from person_table order by name asc")
    LiveData<List<Person>> getAllPersonSortByName();

    @Query("select * from person_table order by age asc")
    LiveData<List<Person>> getAllPersonSortByAge();

    @Query("select * from person_table order by PersonId asc")
    LiveData<List<Person>> getAllPersonSortById();

    @Insert
    void insertJob(Job job);

    @Delete
    void deleteJob(Job job);

    @Query("update job_table set jobName = :jobName where jobId = :jobId")
    void update(String jobName, int jobId);

    @Query("select * from job_table order by jobId asc")
    LiveData<List<Job>> getAllJob();

}
