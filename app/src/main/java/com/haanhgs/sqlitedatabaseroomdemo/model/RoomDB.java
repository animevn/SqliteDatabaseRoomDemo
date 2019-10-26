package com.haanhgs.sqlitedatabaseroomdemo.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class}, version = 1, exportSchema = true)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB instance;

}
