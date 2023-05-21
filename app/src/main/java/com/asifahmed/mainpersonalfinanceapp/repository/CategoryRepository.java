package com.asifahmed.mainpersonalfinanceapp.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Dao;
import androidx.room.Room;
import com.asifahmed.mainpersonalfinanceapp.database.CategoryDatabase;

public class CategoryRepository {

    private String dbName = "CategoryDB";
    private CategoryDatabase categoryDatabase;
    Context context;

    public CategoryRepository(Context context) {
        this.context = context;
        categoryDatabase = Room.databaseBuilder(context, CategoryDatabase.class, dbName).build();
        Toast.makeText(context, "CategoryDB is ready.", Toast.LENGTH_SHORT).show();
    }

}
