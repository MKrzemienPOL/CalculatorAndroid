package com.example.zaliczenie;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Result.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ResultDao resultDao();
}
