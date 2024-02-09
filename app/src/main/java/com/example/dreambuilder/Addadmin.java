package com.example.dreambuilder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
public class Addadmin extends AppCompatActivity {


    EditText mEmail,mPassword;
    Button mAdd;
    Button project,admin,queris,logout,appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addadmin);
        SharedPreferences sp = getSharedPreferences("admin",MODE_PRIVATE);
        String email= sp.getString("email",null);
        if(email == null){
            Intent intent = new Intent(Addadmin.this, landingpage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        DbHandler db= new DbHandler(Addadmin.this);

        mEmail = findViewById(R.id.email);

        mPassword = findViewById(R.id.password);

        mAdd=findViewById(R.id.add);




        project = findViewById(R.id.project);
        admin = findViewById(R.id.admin);
        appointment = findViewById(R.id.appointment);
        queris = findViewById(R.id.queries);
        logout = findViewById(R.id.logout);

        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Addadmin.this, Admin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Addadmin.this, Addadmin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Addadmin.this, ViewAppointment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        queris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Addadmin.this, UserQueries.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sp.edit();
                ed.clear();
                ed.apply();
                Intent intent = new Intent(Addadmin.this, landingpage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        mAdd.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v)
            {

                String email = mEmail.getText().toString().trim();

                String password= mPassword.getText().toString().trim();


                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                } else {
                    mEmail.setError("Enter Valid Email.");
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


                long result =  db.addAdmin(email,password);
                if(result != -1){
                    Toast.makeText(Addadmin.this, "Admin Registered Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Addadmin.this, "User Registered Failed", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(Addadmin.this, Addadmin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }
}