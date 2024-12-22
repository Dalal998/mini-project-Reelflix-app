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

public class signup extends AppCompatActivity {

    EditText email, password, name;
    Button signup;
    TextView tv1;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        databaseHelper = new DatabaseHelper(this);
        name=(EditText) findViewById(R.id.ed3);
        email=(EditText) findViewById(R.id.ed4);
        password=(EditText) findViewById(R.id.ed5);
        signup = (Button) findViewById(R.id.b3);
        tv1=(TextView) findViewById(R.id.textView);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameInput = name.getText().toString();
                String emailInput = email.getText().toString();
                String passwordInput = password.getText().toString();

                if (nameInput.isEmpty() || emailInput.isEmpty() || passwordInput.isEmpty()) {
                    Toast.makeText(signup.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkUserEmail = databaseHelper.checkEmail(emailInput);

                if (!checkUserEmail) {
                    Boolean insert = databaseHelper.insertData(emailInput, passwordInput);

                    if (insert) {
                        Toast.makeText(signup.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), login1.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(signup.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(signup.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent intent = new Intent(signup.this, login1.class);
                startActivity(intent);
            }
        });
    }
}