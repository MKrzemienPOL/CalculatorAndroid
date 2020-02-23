package com.example.zaliczenie;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "results")
public class Result {
    @PrimaryKey
    Long id;
    String operations;
    String result;

    Result(String operations, String result){
        this.operations = operations;
        this.result = result;
    }

    public String getOperations() {
        return operations;
    }

    public String getResult() {
        return result;
    }
}
