package com.asifahmed.mainpersonalfinanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.asifahmed.mainpersonalfinanceapp.database.AccountDatabase;
import com.asifahmed.mainpersonalfinanceapp.database.CategoryDatabase;
import com.asifahmed.mainpersonalfinanceapp.database.IncomeExpenseDatabase;
import com.asifahmed.mainpersonalfinanceapp.entity.Account;
import com.asifahmed.mainpersonalfinanceapp.entity.Category;
import com.asifahmed.mainpersonalfinanceapp.entity.Income;
import com.asifahmed.mainpersonalfinanceapp.entity.IncomeExpenses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class IncomeAddActivity extends AppCompatActivity {

    EditText txtIncomeEntryDate;
    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_add);

        txtIncomeEntryDate = (EditText) findViewById(R.id.incomeEntryDate);

        txtIncomeEntryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar myCalendar = Calendar.getInstance();
                int day = myCalendar.get(Calendar.DAY_OF_MONTH);
                int month = myCalendar.get(Calendar.MONTH);
                int year = myCalendar.get(Calendar.YEAR);

                //Date picker dialog
                picker = new DatePickerDialog(IncomeAddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txtIncomeEntryDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);

                picker.show();
            }
        });

        this.loadAccounts();
        this.loadCategories();
    }

    private void loadAccounts() {
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

            Spinner spinAccounts = (Spinner) findViewById(R.id.accounts);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accountNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinAccounts.setAdapter(adapter);

        } catch (Exception e) {
            throw e;
        }
    }

    private void loadCategories() {
        try {
            List<Category> categories = new ArrayList<Category>();
            List<String> categoryNames = new ArrayList<>();

            CategoryDatabase catDatabase = CategoryDatabase.getCategoryDatabaseInstance(getApplicationContext());
            categories = catDatabase.categoryDAO().getAllCategory();

            if (categories != null && categories.size() > 0) {
                for (int i = 0; i < categories.size(); i++) {
                    Category currentCat = categories.get(i);
                    if(currentCat.CategoryType == 1) {
                        categoryNames.add(currentCat.CategoryName);
                    }
                }
            }

            Spinner spinAccounts = (Spinner) findViewById(R.id.categories);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categoryNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinAccounts.setAdapter(adapter);

        } catch (Exception e) {
            throw e;
        }
    }

    public void btnSaveIncomeClicked(View view) {

        double amountDbl = 0;
        String amount = "", accName = "", catName = "", entryDate = "", description = "";

        EditText txtAmount = (EditText) findViewById(R.id.incomeAmount);
        Spinner spinnerAccount = (Spinner) findViewById(R.id.accounts);
        Spinner spinnerCategory = (Spinner) findViewById(R.id.categories);
        EditText txtEntryDate = (EditText) findViewById(R.id.incomeEntryDate);
        EditText txtDesciption = (EditText) findViewById(R.id.description);

        amount = txtAmount.getText().toString();
        accName = spinnerAccount.getSelectedItem().toString();
        catName = spinnerCategory.getSelectedItem().toString();
        entryDate = txtEntryDate.getText().toString();
        description = txtDesciption.getText().toString();

        amountDbl = Double.parseDouble(amount);

        try {
            IncomeExpenseDatabase ieDatabase = IncomeExpenseDatabase.getCategoryDatabaseInstance(getApplicationContext());
            IncomeExpenses incExp = new IncomeExpenses(amountDbl, entryDate, accName, catName, description, 1);
            ieDatabase.incomeExpensesDao().insert(incExp);
            Toast.makeText(getApplicationContext(), "Income Saved.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            throw e;
        }

        finish();
    }

}