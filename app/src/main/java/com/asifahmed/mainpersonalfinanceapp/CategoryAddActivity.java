package com.asifahmed.mainpersonalfinanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.asifahmed.mainpersonalfinanceapp.database.CategoryDatabase;
import com.asifahmed.mainpersonalfinanceapp.entity.Category;

public class CategoryAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add);
    }

    public void btnSaveClicked(View view) {

        int categoryType = 0;
        String categoryName = "", categoryDescription = "", categoryRemarks = "";

        EditText txtCategoryName = (EditText) findViewById(R.id.cetegoryName);
        EditText txtCategoryDescription = (EditText) findViewById(R.id.categoryDescription);
        EditText txtCategoryRemarks = (EditText) findViewById(R.id.remarks);

        categoryName = txtCategoryName.getText().toString();
        categoryDescription = txtCategoryDescription.getText().toString();
        categoryRemarks = txtCategoryRemarks.getText().toString();

        RadioGroup rdoCategoryType = (RadioGroup) findViewById(R.id.category_type_group);
        int selectedID = rdoCategoryType.getCheckedRadioButtonId();

        switch (selectedID) {
            case R.id.radioIncome:
                categoryType = 1;
                break;
            case R.id.radioExpense:
                categoryType = 2;
                break;
        }

        try {
            CategoryDatabase categoryDatabase = CategoryDatabase.getCategoryDatabaseInstance(getApplicationContext());
            Category category = new Category(categoryName, categoryDescription, categoryType, categoryRemarks);
            categoryDatabase.categoryDAO().insertCategory(category);
            Toast.makeText(getApplicationContext(), "Category Saved.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw e;
        }

        finish();
    }
}