package com.example.dreambuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class landingpage extends AppCompatActivity {

    CardView login,register,about,admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        login = findViewById(R.id.cardLogin);
        register = findViewById(R.id.cardRegister);
        about = findViewById(R.id.cardAboutus);
        admin = findViewById(R.id.cardAdminLogin);


        SharedPreferences sp = getSharedPreferences("my",MODE_PRIVATE);
        String email= sp.getString("email",null);
        if(email != null){
            Intent intent = new Intent(landingpage.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        login.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Logup.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }
        });

        admin.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminLogin.class));
            }
        });
        about.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(), About.class));
            }
        });
    }

}