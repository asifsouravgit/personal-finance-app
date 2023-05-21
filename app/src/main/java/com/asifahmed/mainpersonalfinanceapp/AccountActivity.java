package com.asifahmed.mainpersonalfinanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.asifahmed.mainpersonalfinanceapp.database.AccountDatabase;
import com.asifahmed.mainpersonalfinanceapp.database.CategoryDatabase;
import com.asifahmed.mainpersonalfinanceapp.entity.Account;
import com.asifahmed.mainpersonalfinanceapp.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                int accountPositionInList = position;
                Intent intent = new Intent(getApplicationContext(), AccountUpdateDelete.class);
                intent.putExtra("AccountPosition", accountPositionInList);
                startActivity(intent);
            }
        };

        this.loadList();

        ListView listView = (ListView) findViewById(R.id.accounts);
        listView.setOnItemClickListener(itemClickListener);
    }

    private void loadList() {
        try {
            List<Account> accounts = new ArrayList<Account>();
            List<String> accountNames = new ArrayList<>();

            AccountDatabase accDatabase = AccountDatabase.getCategoryDatabaseInstance(getApplicationContext());
            accounts = accDatabase.accountDAO().getAllAccount();

            if (accounts != null && accounts.size() > 0) {
                for (int i = 0; i < accounts.size(); i++) {
                    Account currentAcc = accounts.get(i);
                    accountNames.add(currentAcc.AccountName);
                }
            }

            ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accountNames);
            ListView listAccounts = (ListView) findViewById(R.id.accounts);
            listAccounts.setAdapter(listAdapter);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.loadList();
    }

    public void btnAddAccountClicked(View view) {
        Intent intent = new Intent(this, AccountAdd.class);
        startActivity(intent);
    }
}