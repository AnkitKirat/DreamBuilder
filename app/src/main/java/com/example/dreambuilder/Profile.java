package com.example.dreambuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

public class Profile extends AppCompatActivity {
    EditText mFullName,mPassword,mPhone,madds;
    Button mUpdateBtn;
    Cursor cursor;
    String name,adds,phone,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences sp = getSharedPreferences("my",MODE_PRIVATE);
        String email= sp.getString("email",null);

        DbHandler db=new DbHandler(Profile.this);
        cursor=db.getUser(email);
        cursor.moveToFirst();
        name = cursor.getString(1);
        adds = cursor.getString(2);
        phone = cursor.getString(3);
        pass = cursor.getString(4);



        mFullName = findViewById(R.id.name);
        madds = findViewById(R.id.adds);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mUpdateBtn=findViewById(R.id.update);


        mFullName.setText(name);
        madds.setText(adds);
        mPhone.setText(phone);
        mPassword.setText(pass);


        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= mFullName.getText().toString().trim();
                String adds= madds.getText().toString().trim();
                String phone= mPhone.getText().toString().trim();
                String password= mPassword.getText().toString().trim();


                if(TextUtils.isEmpty(name))
                {
                    mFullName.setError("Name is Required.");
                    return;
                }

                if(TextUtils.isEmpty(adds))
                {
                    madds.setError("Address is Required.");
                    return;
                }
                if(TextUtils.isEmpty(phone))
                {
                    mPhone.setError("Phone is Required.");
                    return;
                }
                if(phone.length() !=10)
                {
                    mPhone.setError("Phone Number Must be 10 Digit");
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


                long result =  db.updateUser(email,name,adds,phone,password);
                if(result != -1){
                    Toast.makeText(Profile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Profile.this, Profile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Profile.this, "Profile Updation Failed", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}