package com.haanhgs.sqlitedatabaseroomdemo.model;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class, Job.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB instance;

    public static RoomDB init(final Context context){
        if (instance == null){
            synchronized (RoomDB.class){
                if (instance == null){
                    Context appContext = context.getApplicationContext();
                    instance = Room.databaseBuilder(appContext, RoomDB.class, "person_db")
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return instance;
    }

}
