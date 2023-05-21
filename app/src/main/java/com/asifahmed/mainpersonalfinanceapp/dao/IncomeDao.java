package com.asifahmed.mainpersonalfinanceapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.asifahmed.mainpersonalfinanceapp.entity.Income;
import java.util.List;

@Dao
public interface IncomeDao {

    @Query("SELECT * FROM Income")
    List<Income> getAllIncome();

    @Insert
    void insertIncome(Income income);

    @Update
    void updateCategory(Income income);

    @Delete
    void deleteCategory(Income income);

    @Query("DELETE FROM Income")
    void deleteAll();

}
