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
    void insert(Person person);

    @Delete
    void delete(Person person);

    @Query("delete from person_table")
    void deleteAll();

    @Query("update person_table set name = :name, age = :age where id = :id")
    void update(@NonNull String name, int age, int id);

    @Query("select * from person_table order by name asc")
    LiveData<List<Person>> getAllSortByName();

    @Query("select * from person_table order by age asc")
    LiveData<List<Person>> getAllSortByAge();

    @Query("select * from person_table order by Id asc")
    LiveData<List<Person>> getAllSortById();
}
