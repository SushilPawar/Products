package com.example.product.Company;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.product.Adapters.product_adapter;
import com.example.product.MainActivity;
import com.example.product.Models.product_model;
import com.example.product.Product.View_all_products;
import com.example.product.R;
import com.example.product.SqliteHelper;

import java.util.ArrayList;

public class Company_details extends AppCompatActivity {
    Button update,delete;
    public String compname,id;
    RecyclerView rview;
    SqliteHelper db;
    ArrayList<product_model> arrayList;
    product_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        update = findViewById(R.id.updatecomp);
        delete = findViewById(R.id.deletecomp);
        rview = findViewById(R.id.companydetailsrview);


        db = new SqliteHelper(Company_details.this);
        adapter = new product_adapter(Company_details.this,allcomp());
        rview.setAdapter(adapter);
        rview.setLayoutManager(new LinearLayoutManager(Company_details.this));



         compname = getIntent().getStringExtra("company_name");
        String partyname = getIntent().getStringExtra("party_name");
        String sector = getIntent().getStringExtra("sector");
         id = getIntent().getStringExtra("id");


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Company_details.this,Update_company.class);
                intent.putExtra("company_name",compname);
                intent.putExtra("party_name",partyname);
                intent.putExtra("sector",sector);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertdialouge();
            }
        });

    }
    public void alertdialouge()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + compname + " ?");
        builder.setMessage("Deleting " + compname + " will not delete products and to access those products in here your product company name must be same as name given while registering company.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SqliteHelper db = new SqliteHelper(Company_details.this);
                db.deletecompany(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    private ArrayList<product_model> allcomp() {
        String bd = getIntent().getStringExtra("company_name");
        arrayList=new ArrayList<>();
        Cursor cursor = db.readallproductsbycompany(bd);
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
}