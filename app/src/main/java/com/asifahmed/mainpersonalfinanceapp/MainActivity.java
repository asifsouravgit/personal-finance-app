package com.asifahmed.mainpersonalfinanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.asifahmed.mainpersonalfinanceapp.database.AccountDatabase;
import com.asifahmed.mainpersonalfinanceapp.database.CategoryDatabase;
import com.asifahmed.mainpersonalfinanceapp.database.IncomeExpenseDatabase;
import com.asifahmed.mainpersonalfinanceapp.entity.Account;
import com.asifahmed.mainpersonalfinanceapp.entity.Category;
import com.asifahmed.mainpersonalfinanceapp.entity.IncomeExpenses;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Account dbAccount;
    List<IncomeExpenses> incomeExpensesList;

    TextView txvCurrentBalance;
    TextView txvCurrentMonthIncome;
    TextView txvCurrentMonthExpenses;
    TextView txvCurrentBalanceAfter;
    TextView txvCurrentMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvCurrentMonth = (TextView) findViewById(R.id.currentMonth);
        Calendar currentCal = Calendar.getInstance();
        String month = new SimpleDateFormat("MMMM").format(currentCal.getTime());
        String year = new SimpleDateFormat("YYYY").format(currentCal.getTime());
        txvCurrentMonth.setText(month + ", " + year);

        txvCurrentBalance = (TextView) findViewById(R.id.currentBalance);
        txvCurrentMonthIncome = (TextView) findViewById(R.id.currentMonthIncome);
        txvCurrentMonthExpenses = (TextView) findViewById(R.id.currentMonthExpense);
        txvCurrentBalanceAfter = (TextView) findViewById(R.id.currentBalanceAfter);

        this.loadList();
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

    private void loadList() {
        try {
            IncomeExpenseDatabase ieDatabase = IncomeExpenseDatabase.getCategoryDatabaseInstance(getApplicationContext());
            incomeExpensesList = ieDatabase.incomeExpensesDao().getAll();

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

            spinAccounts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    dbAccount = getIndividualAccount(position);
                    List<IncomeExpenses> incomeExpensesListByAccount = new ArrayList<>();

                    if (dbAccount != null) {
                        if (incomeExpensesList != null && incomeExpensesList.size() > 0) {
                            for (int x = 0; x < incomeExpensesList.size(); x++) {
                                IncomeExpenses incExp = incomeExpensesList.get(x);
                                if (incExp.AccountName.equals(dbAccount.AccountName)) {
                                    incomeExpensesListByAccount.add(incExp);
                                }
                            }
                        }
                    }

                    double openingBalance = 0;
                    double totalIncome = 0;
                    double totalExpense = 0;
                    double closingBalance = 0;

                    if (incomeExpensesListByAccount != null && incomeExpensesListByAccount.size() > 0) {
                        for (int x = 0; x < incomeExpensesListByAccount.size(); x++) {

                            IncomeExpenses incExp = incomeExpensesListByAccount.get(x);
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                            Calendar cal = Calendar.getInstance();

                            Calendar myCal = Calendar.getInstance();
                            myCal.set(Calendar.YEAR, cal.YEAR);
                            myCal.set(Calendar.MONTH, cal.MONTH);
                            myCal.set(Calendar.DAY_OF_MONTH, 1);
                            Date currentSystemDate = myCal.getTime();

                            Date incExpEntryDate = new Date();
                            try {
                                incExpEntryDate = format.parse(incExp.EntryDateInText);
                            } catch (ParseException pex) {
                                pex.printStackTrace();
                            }

                            if (incExpEntryDate.compareTo(currentSystemDate) < 0) {
                                if(incExp.Type == 1)
                                    openingBalance = openingBalance + incExp.Amount;
                                else
                                    openingBalance = openingBalance - incExp.Amount;
                            }

                            if (incExpEntryDate.compareTo(currentSystemDate) > 0) {
                                if(incExp.Type == 1)
                                    totalIncome = totalIncome + incExp.Amount;
                                if(incExp.Type == 2)
                                    totalExpense = totalExpense + incExp.Amount;
                            }
                        }
                    }

                    closingBalance = openingBalance + totalIncome - totalExpense;

                    txvCurrentBalance.setText(Double.toString(openingBalance));
                    txvCurrentMonthIncome.setText(Double.toString(totalIncome));
                    txvCurrentMonthExpenses.setText(Double.toString(totalExpense));
                    txvCurrentBalanceAfter.setText(Double.toString(closingBalance));

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {

                }
            });

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.loadList();
    }

    public void btnCategoryClicked(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

    public void btnAccountClicked(View view) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    public void btnAddIncomeClicked(View view) {
        Intent intent = new Intent(this, IncomeAddActivity.class);
        startActivity(intent);
    }

    public void btnAddExpenseClicked(View view) {
        Intent intent = new Intent(this, ExpenseAddActivity.class);
        startActivity(intent);
    }

    public void btnLedgerClicked(View view) {
        Intent intent = new Intent(this, LedgerActivity.class);
        startActivity(intent);
    }
}