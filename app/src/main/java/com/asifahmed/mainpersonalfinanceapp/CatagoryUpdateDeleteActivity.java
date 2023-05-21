package com.asifahmed.mainpersonalfinanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.asifahmed.mainpersonalfinanceapp.database.CategoryDatabase;
import com.asifahmed.mainpersonalfinanceapp.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CatagoryUpdateDeleteActivity extends AppCompatActivity {

    public int categoryPosition;
    public int dbCategoryID;
    public Category dbCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory_update_delete);

        Intent intent = getIntent();
        categoryPosition = intent.getIntExtra("CategoryPosition", 0);
        dbCategory = getIndividualCategory(categoryPosition);
        dbCategoryID = dbCategory.CategoryID;

        if (dbCategory != null) {
            loadValue(dbCategory);
        }
    }

    public void loadValue(Category category) {
        EditText txtCategoryName = (EditText) findViewById(R.id.cetegoryName);
        EditText txtCategoryDescription = (EditText) findViewById(R.id.categoryDescription);
        EditText txtCategoryRemarks = (EditText) findViewById(R.id.remarks);
        RadioGroup rdoCategoryType = (RadioGroup) findViewById(R.id.category_type_group);

        txtCategoryName.setText(category.CategoryName);
        txtCategoryDescription.setText(category.CategoryDescription);
        txtCategoryRemarks.setText(category.Remarks);

        if (category.CategoryType == 1)
            rdoCategoryType.check(R.id.radioIncome);
        else if (category.CategoryType == 2)
            rdoCategoryType.check(R.id.radioExpense);
    }

    public Category getIndividualCategory(int categoryPosition) {
        List<Category> categories = new ArrayList<Category>();
        CategoryDatabase categoryDatabase = CategoryDatabase.getCategoryDatabaseInstance(getApplicationContext());
        categories = categoryDatabase.categoryDAO().getAllCategory();

        if (categories != null && categories.size() > 0) {
                dbCategory = categories.get(categoryPosition);
        }

        return dbCategory;
    }

    public void btnDeleteClicked(View view) {
        try {
            CategoryDatabase categoryDatabase = CategoryDatabase.getCategoryDatabaseInstance(getApplicationContext());
            categoryDatabase.categoryDAO().deleteCategory(dbCategory);
            Toast.makeText(getApplicationContext(), "Category Deleted.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw e;
        }
        finish();
    }

    public void btnUpdateClicked(View view) {

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

            dbCategory.CategoryName = categoryName;
            dbCategory.CategoryDescription = categoryDescription;
            dbCategory.Remarks = categoryRemarks;
            dbCategory.CategoryType = categoryType;

            categoryDatabase.categoryDAO().updateCategory(dbCategory);
            Toast.makeText(getApplicationContext(), "Category Updated.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw e;
        }

        finish();
    }
}