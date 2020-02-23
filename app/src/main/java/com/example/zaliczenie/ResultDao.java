package com.example.zaliczenie;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ResultDao {
    @Insert
    void insert(Result result);

    @Query("SELECT * from results")
    List<Result> getResults();

    @Query("DELETE from results where id > 0")
    int clearTable();
}
