package com.haanhgs.sqlitedatabaseroomdemo.model;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Person.class, Job.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB instance;
    public abstract PersonDao personDao();

    private static class InitDatabaseAsync extends AsyncTask<Void, Void, Void> {
        private final PersonDao personDao;
        private final String[]strings = {"alpha", "beta", "gamma"};
        private final int[]ages = {40, 30, 20};
        private final int[]jobs = {1, 2, 3};
        private final String[]jobNames = {"jobless", "full time", "freelance"};

        InitDatabaseAsync(RoomDB roomDB) {
            personDao = roomDB.personDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (personDao.getAnyPerson().length == 0){
                for (int i = 0; i < strings.length; i++) {
                    personDao.insertPerson(new Person(0, strings[i], jobs[i], ages[i]));
                }
            }
            if (personDao.getAnyJob().length == 0){
                for (String string:jobNames){
                    personDao.insertJob(new Job(0, string));
                }
            }
            return null;
        }
    }

    private static final RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new InitDatabaseAsync(instance).execute();
        }
    };

    public static RoomDB init(final Context context){
        if (instance == null){
            synchronized (RoomDB.class){
                if (instance == null){
                    Context appContext = context.getApplicationContext();
                    instance = Room.databaseBuilder(appContext, RoomDB.class, "person_db")
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return instance;
    }

}
