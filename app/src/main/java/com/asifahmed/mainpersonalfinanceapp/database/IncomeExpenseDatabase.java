package com.asifahmed.mainpersonalfinanceapp.database;

import android.content.Context;

import androidx.core.app.NavUtils;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.asifahmed.mainpersonalfinanceapp.dao.IncomeExpensesDao;
import com.asifahmed.mainpersonalfinanceapp.entity.IncomeExpenses;

@Database(entities = IncomeExpenses.class, version = 1, exportSchema = false)
public abstract class IncomeExpenseDatabase extends RoomDatabase {

    public abstract IncomeExpensesDao incomeExpensesDao();
    public static String dbName = "IncomeExpenseDB";
    private static IncomeExpenseDatabase INSTANCE;

    public static IncomeExpenseDatabase getCategoryDatabaseInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, IncomeExpenseDatabase.class, dbName).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
