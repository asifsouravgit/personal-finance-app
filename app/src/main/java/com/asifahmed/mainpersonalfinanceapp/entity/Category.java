package com.asifahmed.mainpersonalfinanceapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.jar.Attributes;

@Entity
public class Category {

    @PrimaryKey(autoGenerate = true)
    public int CategoryID;

    @ColumnInfo(name = "CategoryName")
    public String CategoryName;

    @ColumnInfo(name = "CategoryDescription")
    public String CategoryDescription;

    @ColumnInfo(name = "CategoryType")
    public int CategoryType;

    @ColumnInfo(name = "Remarks")
    public String Remarks;

    public Category(String CategoryName, String CategoryDescription, int CategoryType, String Remarks) {
        this.CategoryName = CategoryName;
        this.CategoryDescription = CategoryDescription;
        this.CategoryType = CategoryType;
        this.Remarks = Remarks;
    }
}
