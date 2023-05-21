package com.asifahmed.mainpersonalfinanceapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.jar.Attributes;

@Entity
public class Account {

    @PrimaryKey(autoGenerate = true)
    public int AccountID;

    @ColumnInfo(name = "AccountName")
    public String AccountName;

    @ColumnInfo(name = "AccountDescription")
    public String AccountDescription;

    @ColumnInfo(name = "AccountBankInfo")
    public String AccountBankInfo;

    @ColumnInfo(name = "Remarks")
    public String Remarks;

    public Account(String AccountName, String AccountDescription, String AccountBankInfo, String Remarks) {
        this.AccountName = AccountName;
        this.AccountDescription = AccountDescription;
        this.AccountBankInfo = AccountBankInfo;
        this.Remarks = Remarks;
    }
}
