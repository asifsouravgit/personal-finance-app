package com.asifahmed.mainpersonalfinanceapp.database;

import android.content.Context;

import androidx.core.app.NavUtils;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.asifahmed.mainpersonalfinanceapp.dao.AccountDao;
import com.asifahmed.mainpersonalfinanceapp.entity.Account;

@Database(entities = Account.class, version = 1, exportSchema = false)
public abstract class AccountDatabase extends RoomDatabase {

    public abstract AccountDao accountDAO();
    public static String dbName = "AccountDB";
    private static AccountDatabase INSTANCE;

    public static AccountDatabase getCategoryDatabaseInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AccountDatabase.class, dbName).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
