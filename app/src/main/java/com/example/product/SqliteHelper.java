package com.example.product;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.product.Company.Company_details;
import com.example.product.Company.Update_company;
import com.example.product.Company.View_all_companies;
import com.example.product.Product.View_all_products;

import java.util.ArrayList;

public class SqliteHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String database_name = "Company.db";
    private final static int version = 1;

    public SqliteHelper(@Nullable Context context) {
        super(context, database_name, null, version);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE company(_id INTEGER PRIMARY KEY AUTOINCREMENT, company_name TEXT,party_name TEXT, sector INTEGER);";
        String query1 = "CREATE TABLE products(product_id INTEGER PRIMARY KEY AUTOINCREMENT, product_name TEXT,product_company TEXT,party_name TEXT, mrp TEXT,quantity TEXT);";

        db.execSQL(query);
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS company");
        db.execSQL("DROP TABLE IF EXISTS products");
    }


    public void insert_data(String company_name, String party_name, int sector) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("company_name", company_name);
        contentValues.put("party_name", party_name);
        contentValues.put("sector", sector);

        long result = db.insert("company", null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Company Registered", Toast.LENGTH_SHORT).show();
        }
    }

    public void product_data(String product_name, String product_company, String party_name, String mrp, String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("product_name", product_name);
        contentValues.put("product_company", product_company);
        contentValues.put("party_name", party_name);
        contentValues.put("mrp", mrp);
        contentValues.put("quantity", quantity);
        long result = db.insert("products", null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Failed to insert data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Product Added", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readalldata() {
        String query = "SELECT * FROM company";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readallproducts() {
        String query = "SELECT * FROM products";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public Cursor readallproductsbycompany(String company_name) {
        String comp = company_name;
            String query = "SELECT * FROM products WHERE product_company = '"+comp+"'";
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = null;
            if(db !=null)
            {
                cursor = db.rawQuery(query,null);
            }
            return cursor;
        }

    public ArrayList<String> getcomp() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        String query = "SELECT * FROM company";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String cname = cursor.getString(cursor.getColumnIndex("company_name"));
                list.add(cname);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return list;
    }

    public void updatecompanydetails(String id, String compname, String partyname, String sector) {

        String idd = String.valueOf(id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("company_name", compname);
        values.put("party_name", partyname);
        values.put("sector", sector);
        long result = db.update("company", values, "_id = ?", new String[]{idd});
        if (result == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data updated Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    public void updateproductdetails(String id,String productname,String compname,String partyname , String mrp,String quantity)
    {

        String idd = String.valueOf(id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_name",productname);
        values.put("product_company", compname);
        values.put("party_name", partyname);
        values.put("mrp",mrp);
        values.put("quantity",quantity);
        long result = db.update("products", values, "product_id = ?", new String[]{idd});
        if (result == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data updated Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, View_all_products.class);
            context.startActivity(intent);

        }
    }
    public void deletecompany(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("company", "_id = ?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete company", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Company Deleted Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, View_all_companies.class);
            context.startActivity(intent);
        }

    }

    public void deleteproduct(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("products", "product_id = ?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete company", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Company Deleted Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, View_all_products.class);
            context.startActivity(intent);
        }
    }
}
