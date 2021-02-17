package com.example.product.Company;

import androidx.annotation.Nullable;
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
import com.example.product.MainActivity;
import com.example.product.Models.company_model;
import com.example.product.R;
import com.example.product.SqliteHelper;

import java.util.ArrayList;

public class View_all_companies extends AppCompatActivity {

    SqliteHelper db;
    RecyclerView rview;
    ArrayList<company_model> arrayList;
    company_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_companies);

        rview = findViewById(R.id.vacrview);
        db = new SqliteHelper(View_all_companies.this);

        adapter = new company_adapter(View_all_companies.this,this,displaydata());
        rview.setAdapter(adapter);
        rview.setLayoutManager(new LinearLayoutManager(View_all_companies.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            recreate();
        }
    }

    private ArrayList<company_model> displaydata() {

        arrayList=new ArrayList<>();
        Cursor cursor = db.readalldata();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this,"No DATA FOUND",Toast.LENGTH_SHORT);
        }
        else {
            cursor.moveToFirst();
            do {
                company_model company = new company_model();
                company.setId(cursor.getString(0));
                company.setName(cursor.getString(1));
                company.setParty(cursor.getString(2));
                company.setSector(cursor.getString(3));
                arrayList.add(company);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

    public void onBackPressed()
    {
        Intent intent = new Intent(View_all_companies.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        return super.onCreateOptionsMenu(menu);
    }
}