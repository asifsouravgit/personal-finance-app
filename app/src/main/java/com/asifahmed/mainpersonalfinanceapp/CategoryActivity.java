package com.asifahmed.mainpersonalfinanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.asifahmed.mainpersonalfinanceapp.database.CategoryDatabase;
import com.asifahmed.mainpersonalfinanceapp.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                int categoryPositionInList = position;
                Intent intent = new Intent(getApplicationContext(), CatagoryUpdateDeleteActivity.class);
                intent.putExtra("CategoryPosition", categoryPositionInList);
                startActivity(intent);
            }
        };

        this.loadCategoryList();

        ListView listView = (ListView) findViewById(R.id.categories);
        listView.setOnItemClickListener(itemClickListener);
    }

    private void loadCategoryList() {
        try {
            List<Category> categories = new ArrayList<Category>();
            List<String> categoryNames = new ArrayList<>();

            CategoryDatabase categoryDatabase = CategoryDatabase.getCategoryDatabaseInstance(getApplicationContext());
            categories = categoryDatabase.categoryDAO().getAllCategory();

            if (categories != null && categories.size() > 0) {
                for (int i = 0; i < categories.size(); i++) {
                    Category currentCategory = categories.get(i);
                    categoryNames.add(currentCategory.CategoryName);
                }
            }

            ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categoryNames);
            ListView listDrinks = (ListView) findViewById(R.id.categories);
            listDrinks.setAdapter(listAdapter);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.loadCategoryList();
    }

    public void btnAddCategoryClicked(View view) {
        Intent intent = new Intent(this, CategoryAddActivity.class);
        startActivity(intent);
    }
}