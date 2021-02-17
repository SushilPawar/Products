package com.example.product.Product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.product.MainActivity;
import com.example.product.R;
import com.example.product.SqliteHelper;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_dropdown_item;

public class Addproduct extends AppCompatActivity {
    SqliteHelper db;
    Spinner compname;
    ArrayAdapter<String> adapter;
    EditText productname,party,mrp,quantity;
    Button addproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

        productname = findViewById(R.id.productnanme);
        party = findViewById(R.id.productparty);
        mrp = findViewById(R.id.productmrp);
        quantity = findViewById(R.id.productquantity);
        compname = findViewById(R.id.compname);
        addproduct = findViewById(R.id.addproduct);


        db = new SqliteHelper(Addproduct.this);
        ArrayList<String> listpro = db.getcomp();
        adapter =new ArrayAdapter<String>(this, simple_spinner_dropdown_item,listpro);
        compname.setAdapter(adapter);
        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqliteHelper database=new SqliteHelper(Addproduct.this);

                database.product_data(productname.getText().toString(),compname.getSelectedItem().toString(),party.getText().toString(),mrp.getText().toString(),quantity.getText().toString());
                productname.setText("");
                party.setText("");
                mrp.setText("");
                quantity.setText("");
                Intent intent = new Intent(Addproduct.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

}