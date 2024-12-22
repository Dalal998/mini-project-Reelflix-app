package com.example.rellfix;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MoviesInfo extends AppCompatActivity {
    TextView mTitle1, mRating1, mYear1, mDescription1;
    TextView mTitle2, mRating2, mYear2, mDescription2;
    TextView mTitle3, mRating3, mYear3, mDescription3;
    Button backb, exitb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movies_info);

        mTitle1 = findViewById(R.id.tv4);
        mRating1 = findViewById(R.id.tv5);
        mYear1 = findViewById(R.id.tv6);
        mDescription1 = findViewById(R.id.tv7);

        mTitle2 = findViewById(R.id.tv8);
        mRating2 = findViewById(R.id.tv9);
        mYear2 = findViewById(R.id.tv14);
        mDescription2 = findViewById(R.id.tv10);

        mTitle3 = findViewById(R.id.tv11);
        mRating3 = findViewById(R.id.tv12);
        mYear3 = findViewById(R.id.tv15);
        mDescription3 = findViewById(R.id.tv13);

        backb = findViewById(R.id.b2);
        exitb = findViewById(R.id.b3);

        mTitle1.setText("The Maze Runner");
        mRating1.setText("Rating: 10");
        mYear1.setText("Year: 2014");
        mDescription1.setText("follows Thomas as he wakes up in a mysterious maze with no memory and must work with others to escape while facing dangerous obstacles.");

        mTitle2.setText("Home Alone");
        mRating2.setText("Rating: 9.5");
        mYear2.setText("Year: 1990");
        mDescription2.setText("about a young boy who defends his home from burglars after being accidentally left behind by his family.");

        mTitle3.setText("Spider-Man: No Way Home");
        mRating3.setText("Rating: 8.5");
        mYear3.setText("Year: 2021");
        mDescription3.setText("follows Peter Parker,who gains superpowers after being bitten by a radioactive spider.");


        backb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        exitb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MoviesInfo.this, "Exiting the app", Toast.LENGTH_SHORT).show();
                finishAffinity();
            }
        });


    }
}