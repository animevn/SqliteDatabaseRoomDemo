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

    @Update
    void update(Person person);

    @Query("select * from person_table order by name asc")
    LiveData<List<Person>> getAllPerson();

    @Insert
    void insertJob(Job job);

    @Update
    void update(Job job);

    @Delete
    void deleteJob(Job job);

    @Query("delete from job_table")
    void deleteAllJob();

    @Query("select * from job_table order by job_name asc")
    LiveData<List<Job>> getAllJob();

}
