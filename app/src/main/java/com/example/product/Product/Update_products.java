package com.example.product.Product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.product.R;
import com.example.product.SqliteHelper;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_dropdown_item;

public class Update_products extends AppCompatActivity {
    EditText productup,quantityup,mrpup,partyup;
    Button productupbutton;
    Spinner companyup;
    ArrayAdapter<String> adapter;
    SqliteHelper db ;
    String id,product,party,mrp,quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_products);
        intializing();
        db = new SqliteHelper(Update_products.this);
        ArrayList<String> procomp = db.getcomp();
        adapter =new ArrayAdapter<String>(this, simple_spinner_dropdown_item,procomp);
        companyup.setAdapter(adapter);
        id = getIntent().getStringExtra("id");
        product = getIntent().getStringExtra("product");
        party = getIntent().getStringExtra("party");
        mrp = getIntent().getStringExtra("mrp");
        quantity= getIntent().getStringExtra("quantity");

        productup.setText(product);
        partyup.setText(party);
        mrpup.setText(mrp);
        quantityup.setText(quantity);

        productupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqliteHelper db = new SqliteHelper(Update_products.this);
                db.updateproductdetails(id,productup.getText().toString(),companyup.getSelectedItem().toString(),partyup.getText().toString(),mrpup.getText().toString(),quantityup.getText().toString());

            }
        });


    }


    private void intializing() {
        productup = findViewById(R.id.productup);
        quantityup = findViewById(R.id.quantityup);
        mrpup = findViewById(R.id.mrpup);
        partyup = findViewById(R.id.partyup);
        productupbutton = findViewById(R.id.buttonup);
        companyup = findViewById(R.id.companyup);


    }
}