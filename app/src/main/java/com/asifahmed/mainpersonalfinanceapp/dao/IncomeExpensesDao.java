package com.asifahmed.mainpersonalfinanceapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.asifahmed.mainpersonalfinanceapp.entity.Income;
import com.asifahmed.mainpersonalfinanceapp.entity.IncomeExpenses;

import java.util.List;

@Dao
public interface IncomeExpensesDao {

    @Query("SELECT * FROM IncomeExpenses")
    List<IncomeExpenses> getAll();

    @Insert
    void insert(IncomeExpenses incomeExpenses);

    @Update
    void update(IncomeExpenses incomeExpenses);

    @Delete
    void delete(IncomeExpenses incomeExpenses);

    @Query("DELETE FROM IncomeExpenses")
    void deleteAll();

}
