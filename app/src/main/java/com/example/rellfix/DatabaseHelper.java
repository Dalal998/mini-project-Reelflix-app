package com.example.rellfix;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "signup.db";

    // Constructor to initialize the database
    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        // Create table with correct name
        MyDatabase.execSQL("CREATE TABLE allusers(email TEXT PRIMARY KEY, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        // Drop the old table if it exists and create a new one
        MyDatabase.execSQL("DROP TABLE IF EXISTS allusers");
        onCreate(MyDatabase);
    }

    // Method to insert data into the database
    public Boolean insertData(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("allusers", null, contentValues);

        // Check if the insertion was successful
        return result != -1;
    }

    // Method to check if an email exists in the database
    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM allusers WHERE email = ?", new String[]{email});

        boolean exists = cursor.getCount() > 0;
        cursor.close(); // Always close the cursor after use
        return exists;
    }

    // Method to check if email and password match
    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM allusers WHERE email = ? AND password = ?", new String[]{email, password});

        boolean validCredentials = cursor.getCount() > 0;
        cursor.close(); // Always close the cursor after use
        return validCredentials;
    }
}



