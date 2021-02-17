package com.example.product.Company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.product.R;
import com.example.product.SqliteHelper;

public class Update_company extends AppCompatActivity {

    EditText updatecompany,updatedealer,updatesector;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_company);

        updatecompany = findViewById(R.id.updatecompanyname);
        updatedealer = findViewById(R.id.updatedealer);
        updatesector =  findViewById(R.id.updatesector);
        update = findViewById(R.id.updatebutton);
        String id = getIntent().getStringExtra("id");
        String compname = getIntent().getStringExtra("company_name");
        String partyname = getIntent().getStringExtra("party_name");
        String sector = getIntent().getStringExtra("sector");

        updatecompany.setText(compname);
        updatedealer.setText(partyname);
        updatesector.setText(sector);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqliteHelper db = new SqliteHelper(Update_company.this);
                db.updatecompanydetails(id,updatecompany.getText().toString(),updatedealer.getText().toString(),
                        updatesector.getText().toString());
                Intent intent = new Intent(Update_company.this, View_all_companies.class);
                startActivity(intent);
                finish();

            }
        });
    }
}