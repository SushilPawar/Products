package com.example.product.Product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.product.Adapters.company_adapter;
import com.example.product.Adapters.product_adapter;
import com.example.product.Company.View_all_companies;
import com.example.product.MainActivity;
import com.example.product.Models.company_model;
import com.example.product.Models.product_model;
import com.example.product.R;
import com.example.product.SqliteHelper;

import java.util.ArrayList;

public class View_all_products extends AppCompatActivity {
    RecyclerView productrview;
    SqliteHelper db;
        ArrayList<product_model> arrayList;
        product_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_products);
        productrview = findViewById(R.id.productrview);
        db = new SqliteHelper(View_all_products.this);
        adapter = new product_adapter(View_all_products.this,alldata());
        productrview.setAdapter(adapter);
        productrview.setLayoutManager(new LinearLayoutManager(View_all_products.this));
    }

    private ArrayList<product_model> alldata() {

        arrayList=new ArrayList<>();
        Cursor cursor = db.readallproducts();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this,"No DATA FOUND",Toast.LENGTH_SHORT);
        }
        else {
            cursor.moveToFirst();
            do {
                product_model company = new product_model();
                company.setId(cursor.getString(0));
                company.setProduct_name(cursor.getString(1));
                company.setCompany_name(cursor.getString(2));
                company.setParty_name(cursor.getString(3));
                company.setMrp(cursor.getString(4));
                company.setQuantity(cursor.getString(5));
                arrayList.add(company);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }
    public void onBackPressed()
    {
        Intent intent = new Intent(View_all_products.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.actionsearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreatePanelMenu(featureId, menu);
    }
}