package com.asifahmed.mainpersonalfinanceapp.database;

import android.content.Context;

import androidx.core.app.NavUtils;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.asifahmed.mainpersonalfinanceapp.dao.CategoryDao;
import com.asifahmed.mainpersonalfinanceapp.entity.Category;

@Database(entities = Category.class, version = 1, exportSchema = false)
public abstract class CategoryDatabase extends RoomDatabase {

    public abstract CategoryDao categoryDAO();
    public static String dbName = "CategoryDB";
    private static CategoryDatabase INSTANCE;

    public static CategoryDatabase getCategoryDatabaseInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, CategoryDatabase.class, dbName).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}

