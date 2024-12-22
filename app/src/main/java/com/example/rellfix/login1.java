package com.example.rellfix;

import android.app.Activity;
import android.content.Intent;
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

public class login1 extends AppCompatActivity {
    EditText email,password;
    Button login;
    TextView tv;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login1);

        databaseHelper = new DatabaseHelper(this);
        email = findViewById(R.id.ed6);
        password = findViewById(R.id.ed7);
        login = findViewById(R.id.b4);
        tv = findViewById(R.id.textView2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String logemail = email.getText().toString();
                String logpassword = password.getText().toString();

                if (logemail.isEmpty() || logpassword.isEmpty()) {
                    Toast.makeText(login1.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkCredentials = databaseHelper.checkEmailPassword (logemail, logpassword);


                if (checkCredentials) {
                    Toast.makeText(login1.this, "Login Successfully!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), Menumovie.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(login1.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login1.this, signup.class);
                startActivity(intent);
            }
        });
    }
}