package com.asifahmed.mainpersonalfinanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.asifahmed.mainpersonalfinanceapp.database.CategoryDatabase;
import com.asifahmed.mainpersonalfinanceapp.database.IncomeExpenseDatabase;
import com.asifahmed.mainpersonalfinanceapp.entity.Category;
import com.asifahmed.mainpersonalfinanceapp.entity.IncomeExpenses;

import java.util.ArrayList;
import java.util.List;

public class LedgerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ledger);
    }

    private void loadList() {
        try {
            List<IncomeExpenses> incExpenses = new ArrayList<IncomeExpenses>();
            List<String> infos = new ArrayList<>();

            IncomeExpenseDatabase ieDatabase = IncomeExpenseDatabase.getCategoryDatabaseInstance(getApplicationContext());
            incExpenses = ieDatabase.incomeExpensesDao().getAll();

            if (incExpenses != null && incExpenses.size() > 0) {
                for (int i = 0; i < incExpenses.size(); i++) {
                    IncomeExpenses cIncExp = incExpenses.get(i);
                    String info =  "Transaction Date : " + cIncExp.EntryDateInText + " \n Amount : " + Double.toString(cIncExp.Amount) + " \n Account Information : " + cIncExp.AccountName + " \n Category : " + cIncExp.CategoryName + " \n Description : " + cIncExp.Description;
                    infos.add(info);
                }
            }

            ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, infos);
            ListView listIncExp = (ListView) findViewById(R.id.incomesExpenses);
            listIncExp.setAdapter(listAdapter);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.loadList();
    }
}