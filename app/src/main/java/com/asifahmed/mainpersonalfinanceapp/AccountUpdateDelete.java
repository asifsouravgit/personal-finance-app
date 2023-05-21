package com.asifahmed.mainpersonalfinanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.asifahmed.mainpersonalfinanceapp.database.AccountDatabase;
import com.asifahmed.mainpersonalfinanceapp.database.CategoryDatabase;
import com.asifahmed.mainpersonalfinanceapp.entity.Account;
import com.asifahmed.mainpersonalfinanceapp.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class AccountUpdateDelete extends AppCompatActivity {

    public int accPosition;
    public int dbAccID;
    public Account dbAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_update_delete);

        Intent intent = getIntent();
        accPosition = intent.getIntExtra("AccountPosition", 0);
        dbAccount = getIndividualAccount(accPosition);
        dbAccID = dbAccount.AccountID;

        if (dbAccount != null) {
            loadValue(dbAccount);
        }
    }

    public void loadValue(Account acc) {
        EditText txtAccName = (EditText) findViewById(R.id.accountName);
        EditText txtAccDescription = (EditText) findViewById(R.id.accountDescription);
        EditText txtAccBankInfo = (EditText) findViewById(R.id.bankInfo);
        EditText txtAccRemarks = (EditText) findViewById(R.id.remarks);

        txtAccName.setText(acc.AccountName);
        txtAccDescription.setText(acc.AccountDescription);
        txtAccBankInfo.setText(acc.AccountBankInfo);
        txtAccRemarks.setText(acc.Remarks);
    }

    public Account getIndividualAccount(int accPosition) {

        List<Account> accounts = new ArrayList<Account>();
        AccountDatabase accDatabase = AccountDatabase.getCategoryDatabaseInstance(getApplicationContext());
        accounts = accDatabase.accountDAO().getAllAccount();

        if (accounts != null && accounts.size() > 0) {
            dbAccount = accounts.get(accPosition);
        }

        return dbAccount;
    }

    public void btnDeleteClicked(View view) {
        try {
            AccountDatabase accDatabase = AccountDatabase.getCategoryDatabaseInstance(getApplicationContext());
            accDatabase.accountDAO().deleteAccount(dbAccount);
            Toast.makeText(getApplicationContext(), "Account Deleted.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw e;
        }
        finish();
    }

    public void btnUpdateClicked(View view) {

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

            dbAccount.AccountName = accName;
            dbAccount.AccountDescription = accDescription;
            dbAccount.AccountBankInfo = accBankInfo;
            dbAccount.Remarks = accRemarks;

            accDatabase.accountDAO().updateAccount(dbAccount);
            Toast.makeText(getApplicationContext(), "Account Updated.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw e;
        }

        finish();

    }
}