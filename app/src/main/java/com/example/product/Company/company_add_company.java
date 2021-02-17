package com.example.product.Company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.product.MainActivity;
import com.example.product.R;
import com.example.product.SqliteHelper;

public class company_add_company extends AppCompatActivity {
    TextView compname,partyname,sector;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_add_company);

        compname = findViewById(R.id.subcompanyname);
        partyname = findViewById(R.id.subdealername);
        sector = findViewById(R.id.subsector);
        submit = findViewById(R.id.subregister);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqliteHelper mydb = new SqliteHelper(company_add_company.this);
                mydb.insert_data(compname.getText().toString().trim(),partyname.getText().toString().trim(),Integer.valueOf(sector.getText().toString().trim()));
                compname.setText("");
                partyname.setText("");
                sector.setText("");
                Intent intent = new Intent(company_add_company.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}