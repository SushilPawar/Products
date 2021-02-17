package com.example.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.product.Company.View_all_companies;
import com.example.product.Company.company_add_company;
import com.example.product.Product.Addproduct;
import com.example.product.Product.View_all_products;

public class MainActivity extends AppCompatActivity{
    CardView companies,allparty,products,addcompany,addproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        companies = findViewById(R.id.company_card);
        addcompany = findViewById(R.id.add_company_card);
        addproduct = findViewById(R.id.add_product_card);
        products = findViewById(R.id.product_card);
        companies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, View_all_companies.class);
                startActivity(intent);
            }
        });
        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, View_all_products.class);
                startActivity(intent);
            }
        });
        addcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, company_add_company.class);
                startActivity(intent);
            }
        });

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Addproduct.class);
                startActivity(intent);
            }
        });

    }
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}