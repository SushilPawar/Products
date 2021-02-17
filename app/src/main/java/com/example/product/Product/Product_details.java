package com.example.product.Product;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.product.Company.Company_details;
import com.example.product.R;
import com.example.product.SqliteHelper;

public class Product_details extends AppCompatActivity {
    Button update,delete;
    TextView pro,pa,co,mr,quan;
    String id,product,party,company,mrp,quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        init();
        update = findViewById(R.id.updateproduct);
        delete = findViewById(R.id.deleteproduct);

        id = getIntent().getStringExtra("id");
        product = getIntent().getStringExtra("product");
        party = getIntent().getStringExtra("party");
        company = getIntent().getStringExtra("company");
        mrp = getIntent().getStringExtra("mrp");
        quantity= getIntent().getStringExtra("quantity");

        pro.setText(product);
        co.setText(company);
        pa.setText(party);
        mr.setText(mrp);
        quan.setText(quantity);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Product_details.this,Update_products.class);
                intent.putExtra("id",id);
                intent.putExtra("product",product);
                intent.putExtra("party",party);
                intent.putExtra("quantity",quantity);
                intent.putExtra("mrp",mrp);
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

    private void init() {
        pro = findViewById(R.id.detailproduct);
        co = findViewById(R.id.detailcompany);
        pa = findViewById(R.id.detailparty);
        mr = findViewById(R.id.detailmrp);
        quan = findViewById(R.id.detailquantity);

    }

    public void alertdialouge()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + product + " ?");
        builder.setMessage("Are you sure you want to delete " + product + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SqliteHelper db = new SqliteHelper(Product_details.this);
                db.deleteproduct(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}