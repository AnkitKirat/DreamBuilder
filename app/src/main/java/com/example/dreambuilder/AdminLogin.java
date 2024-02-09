package com.example.dreambuilder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;



public class AdminLogin extends AppCompatActivity {


    EditText mEmail,mPassword;
    Button mLoginBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        DbHandler db = new DbHandler(AdminLogin.this);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);

        mLoginBtn = findViewById(R.id.login);



        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password= mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    mPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6)
                {
                    mPassword.setError("Password Must be >=6 Characters");
                    return;
                }



                String result =  db.getAdmin(email,password);
                if(result != "fail"){
                    Toast.makeText(AdminLogin.this, "Admin Logged In Successfully", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getSharedPreferences("admin",MODE_PRIVATE);
                    SharedPreferences.Editor ed = sp.edit();
                    ed.clear();
                    ed.putString("email",result);
                    ed.apply();
                    Intent intent = new Intent(AdminLogin.this, Admin.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AdminLogin.this, "Wrong Credentials ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}