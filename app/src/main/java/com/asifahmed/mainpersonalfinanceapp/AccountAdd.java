package com.asifahmed.mainpersonalfinanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.asifahmed.mainpersonalfinanceapp.database.AccountDatabase;
import com.asifahmed.mainpersonalfinanceapp.database.CategoryDatabase;
import com.asifahmed.mainpersonalfinanceapp.entity.Account;
import com.asifahmed.mainpersonalfinanceapp.entity.Category;

public class AccountAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_add);
    }

    public void btnSaveAccountClicked(View view) {

        String accName = "", accDescription = "", accBankInfo = "", accRemarks = "";

        EditText txtAccName = (EditText) findViewById(R.id.accountName);
        EditText txtAccDescription = (EditText) findViewById(R.id.accountDescription);
        EditText txtAccBankInfo = (EditText) findViewById(R.id.bankInfo);
        EditText txtAccRemarks = (EditText) findViewById(R.id.remarks);

        accName = txtAccName.getText().toString();
        accDescription = txtAccDescription.getText().toString();
        accBankInfo = txtAccBankInfo.getText().toString();
        accRemarks = txtAccBankInfo.getText().toString();

        try {
            AccountDatabase accDatabase = AccountDatabase.getCategoryDatabaseInstance(getApplicationContext());
            Account account = new Account(accName, accDescription, accBankInfo, accRemarks);
            accDatabase.accountDAO().insertAccount(account);
            Toast.makeText(getApplicationContext(), "Account Saved.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw e;
        }

        finish();
    }
}

