package com.asifahmed.mainpersonalfinanceapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.util.jar.Attributes;

@Entity
public class Income {

    @PrimaryKey(autoGenerate = true)
    public int IncomeID;

    @ColumnInfo(name = "Amount")
    public double Amount;

    @ColumnInfo(name = "EntryDateInText")
    public String EntryDateInText;

    @ColumnInfo(name = "AccountName")
    public String AccountName;

    @ColumnInfo(name = "CategoryName")
    public String CategoryName;

    @ColumnInfo(name = "Description")
    public String Description;

    public Income(Double Amount, String EntryDateInText, String AccountName, String CategoryName, String Description) {
        this.Amount = Amount;
        this.EntryDateInText = EntryDateInText;
        this.AccountName = AccountName;
        this.CategoryName = CategoryName;
        this.Description = Description;
    }
}
