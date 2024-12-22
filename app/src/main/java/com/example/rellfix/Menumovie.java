package com.example.rellfix;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menumovie extends AppCompatActivity {
    DatabaseHelper2 databaseHelper2;
    EditText id, title, rating;
    Button badd, bupdate, bdelete, bview, bcloseapp,bcaculate ,bmovie;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menumovie);

        databaseHelper2 = new DatabaseHelper2(this);
        id = findViewById(R.id.ed1);
        title = findViewById(R.id.ed2);
        rating = findViewById(R.id.ed3);

        badd = findViewById(R.id.b1);
        bupdate = findViewById(R.id.b2);
        bdelete = findViewById(R.id.b3);
        bview = findViewById(R.id.b4);
        bcloseapp = findViewById(R.id.b5);
        bcaculate=findViewById(R.id.button2);
        bmovie= findViewById(R.id.button);

        bmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menumovie.this, MoviesInfo.class);
                startActivity(intent);
            }
        });


        addMovie();
        updateMovie();
        deleteMovie();
        viewMovie();
        closeMovie();
        calculateAverageRating();
    }

    public void addMovie() {
        badd.setOnClickListener(v -> {
            boolean insert = databaseHelper2.insertData(title.getText().toString(), rating.getText().toString());
            if (insert) Toast.makeText(Menumovie.this, "Data Inserted", Toast.LENGTH_LONG).show();
            else Toast.makeText(Menumovie.this, "Data not inserted", Toast.LENGTH_LONG).show();
        });
    }

    public void updateMovie() {
        bupdate.setOnClickListener(v -> {
            boolean update = databaseHelper2.updateData(id.getText().toString(), title.getText().toString(), rating.getText().toString());
            if (update) Toast.makeText(Menumovie.this, "Data updated", Toast.LENGTH_LONG).show();
            else Toast.makeText(Menumovie.this, "Data not updated", Toast.LENGTH_LONG).show();
        });
    }

    public void deleteMovie() {
        bdelete.setOnClickListener(v -> {
            int del = databaseHelper2.deleteData(id.getText().toString());
            if (del > 0) Toast.makeText(Menumovie.this, "Data deleted", Toast.LENGTH_LONG).show();
            else Toast.makeText(Menumovie.this, "Data not deleted", Toast.LENGTH_LONG).show();
        });
    }

    public void viewMovie() {
        bview.setOnClickListener(v -> {
            Cursor r = databaseHelper2.getAllData();
            if (r.getCount() == 0) {
                showMessage("Error","No movies found in the database.");
                r.close();
                return;
            }

            StringBuffer b = new StringBuffer();
            while (r.moveToNext()) {
                b.append("ID:" + r.getString(0) + "\n");
                b.append("Title:" + r.getString(1) + "\n");
                b.append("Rating:" + r.getString(2) + "\n");
            }

            showMessage("Movies Details", b.toString());
            r.close();
        });
    }

    public void calculateAverageRating() {
        bcaculate.setOnClickListener(v -> {
            Cursor cursor = databaseHelper2.getAllData();
            if (cursor.getCount() == 0) {
                Toast.makeText(Menumovie.this, "No movies found", Toast.LENGTH_SHORT).show();
                return;
            }

            int totalMovies = 0;
            float totalRating = 0;

            while (cursor.moveToNext()) {
                try {
                    float ratingValue = Float.parseFloat(cursor.getString(2));
                    totalRating += ratingValue;
                    totalMovies++;
                }
                catch (NumberFormatException e) {

                    Toast.makeText(Menumovie.this, "Invalid rating format", Toast.LENGTH_SHORT).show();
                }
            }

            if (totalMovies > 0) {
                float averageRating = totalRating / totalMovies;
                Toast.makeText(Menumovie.this, "Average Rating: " + averageRating, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Menumovie.this, "No valid ratings found", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        });
    }

    public void closeMovie() {
        bcloseapp.setOnClickListener(v -> {
            id.setText("");
            title.setText("");
            rating.setText("");
        });
    }

    public void showMessage(String title, String mes) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setCancelable(true);
        ad.setTitle(title);
        ad.setMessage(mes);
        ad.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
